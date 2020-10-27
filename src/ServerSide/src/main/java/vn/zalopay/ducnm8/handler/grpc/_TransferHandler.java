package vn.zalopay.ducnm8.handler.grpc;

import fintech.*;
import fintech.Error;
import io.vertx.core.Future;
import org.mindrot.jbcrypt.BCrypt;
import vn.zalopay.ducnm8.da.Transaction;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.da.interact.AccountDA;
import vn.zalopay.ducnm8.da.interact.TransferDA;
import vn.zalopay.ducnm8.da.interact.TransferHistoryDA;
import vn.zalopay.ducnm8.handler.WSHandler;
import vn.zalopay.ducnm8.model.*;
import lombok.extern.log4j.Log4j2;
import vn.zalopay.ducnm8.utils.JWTUtils;
import vn.zalopay.ducnm8.utils.Tracker;

import java.time.Instant;

@Log4j2
public class _TransferHandler {

  private final String METRIC = "TransferHandler";
  private final TransferDA transferDA;
  private final AccountDA accountDA;
  private final TransferHistoryDA transferHistoryDA;
  private final TransactionProvider transactionProvider;
  private final WSHandler wsHandler;

  private Transaction transaction;

  long sender;
  long receiver;
  long amount;
  String message;
  String password;
  long transferTime = Instant.now().getEpochSecond();

  Account senderAccount = null;
  Account receiverAccount = null;
  long transferId;
  TransferHistory historyForSenderDatabase = null;
  TransferHistory historyForReceiverClient = null;
  String errorMessageForClient = "INTERNAL_SERVER_ERROR";
  Code errorCodeForClient = Code.INTERNAL_SERVER_ERROR;

  public _TransferHandler(
      TransferDA transferDA,
      AccountDA accountDA,
      TransferHistoryDA transferHistoryDA,
      TransactionProvider transactionProvider,
      WSHandler wsHandler) {
    this.transferDA = transferDA;
    this.accountDA = accountDA;
    this.transferHistoryDA = transferHistoryDA;
    this.transactionProvider = transactionProvider;
    this.wsHandler = wsHandler;

  }

  public void transfer(TransferRequest transferRequest, Future<TransferResponse> responseFuture) {

    Tracker.TrackerBuilder tracker =
        Tracker.builder().metricName(METRIC).startTime(System.currentTimeMillis());

    sender = JWTUtils.CLIENT_ID_CONTEXT_KEY.get();
    receiver = transferRequest.getReceiverId();
    amount = transferRequest.getAmount();
    message = transferRequest.getMessage();
    password = transferRequest.getPassword();
    transferTime = Instant.now().getEpochSecond();

    long smallerId = sender < receiver ? sender : receiver;
    long biggerId = sender >= receiver ? sender : receiver;

    log.info("GRPC: transfer begin. senderId = {} receiverId = {}", sender, receiver);

    transaction = transactionProvider.newTransaction();

    checkRequest()
        .compose(next -> transaction.begin())
        .compose(next -> getAccountForUpdate(smallerId, smallerId == sender))
        .compose(next -> getAccountForUpdate(biggerId, biggerId == sender))
        .compose(next -> isEnoughMoney())
        .compose(next -> transaction.execute(accountDA.plusBalanceByAmount(sender, -amount, transferTime)))
        .compose(next -> transaction.execute(accountDA.plusBalanceByAmount(receiver, amount, transferTime)))
        .compose(next -> insertTransferCertificate())
        .compose(next -> createTransferHistory())
        .compose(next -> transaction.execute(transferHistoryDA.insertInsideTransaction(historyForSenderDatabase)))
        .compose(next -> transaction.execute(transferHistoryDA.insertInsideTransaction(historyForReceiverClient)))
        .compose(next -> transaction.commit())
        .setHandler(transferAsync -> {

          if (transferAsync.succeeded()) {
            transaction.close();
            TransferResponse response = createTransferResponse(true);

            responseFuture.complete(response);
            tracker.step("transfer").code("SUCCESS").build().record();
            wsHandler.sendTransferHistory(historyForReceiverClient, receiver);

            log.info("GRPC: transfer succeed senderId = {}, receiverId = {}", sender, receiver);

          } else {
            if (errorCodeForClient != Code.INCORRECT_PASSWORD && errorCodeForClient != Code.BAD_REQUEST) {
              transaction
                  .rollback()
                  .setHandler(rs -> transaction.close());
            }

            TransferResponse response = createTransferResponse(false);

            responseFuture.complete(response);
            tracker.step("transfer").code("SUCCESS").build().record();

            log.error("GRPC: transfer failed senderId = {} receiverId = {} amount = {}~ cause {}", sender, receiver, amount, transferAsync.cause().getMessage());
          }
        });
  }

  private Future<Void> checkRequest() {
    Future<Void> future = Future.future();

    if (sender == receiver) {
      errorMessageForClient = "BAD_REQUEST";
      errorCodeForClient = Code.BAD_REQUEST;

      log.info("grpc checkRequest failed ~ sender = receiver, amount = {}, sender = {} receiver = {}", amount, sender, receiver);

      future.fail("Bad request, receiver must be different to sender");
    } else if (amount <= 0) {
      errorMessageForClient = "BAD_REQUEST";
      errorCodeForClient = Code.BAD_REQUEST;

      log.info("grpc checkRequest failed ~ amount <= 0, amount = {}, sender = {} receiver = {}", amount, sender, receiver);

      future.fail("Bad request, amount must greater than 0");
    } else {
      accountDA.selectAccountById(sender)
          .setHandler(rs -> {
            if (rs.succeeded()) {
              if (BCrypt.checkpw(password, rs.result().getPassword())) {
                future.complete();
              } else {
                future.fail("Wrong password");
                errorMessageForClient = "WRONG_PASSWORD";
                errorCodeForClient = Code.INCORRECT_PASSWORD;
                log.warn("grpc checkPassword failed ~ Wrong password senderId = {} receiverId = {}", sender, receiver);
              }
            } else {
              String errorString = String.format("grpc checkPassword failed ~  Cannot get password in database of account id: %s. The account may not exist", sender);
              future.fail(errorString);
              errorMessageForClient = "INTERNAL_SERVER_ERROR";
              errorCodeForClient = Code.INTERNAL_SERVER_ERROR;
              log.error("grpc checkPassword failed ~  Cannot get password in database senderId = {} receiverId = {}", sender, receiver);
            }
          });
    }
    return future;
  }


  private Future<Void> isEnoughMoney() {
    Future<Void> future = Future.future();
    if (senderAccount.getBalance() >= 0)
      future.complete();
    else {
      future.fail("Not enough money");
      errorMessageForClient = "NOT_ENOUGH_MONEY";
      errorCodeForClient = Code.NOT_ENOUGH_MONEY;

      log.info("grpc transfer isEnoughMoney failed ~ Not enough money senderId = {} receiverId = {}", sender, receiver);
    }
    return future;
  }

  private Future<Void> getAccountForUpdate(long id, boolean isSenderId) {
    Future<Void> future = Future.future();

    transaction.execute(accountDA.selectAccountForUpdate(id))
        .setHandler(rs -> {
          if (rs.succeeded()) {
            if (isSenderId) {
              senderAccount = rs.result();
              senderAccount.setBalance(senderAccount.getBalance() - amount);
            } else {
              receiverAccount = rs.result();
              receiverAccount.setBalance(receiverAccount.getBalance() + amount);
            }
            future.complete();

          } else {
            future.fail("grpc transfer: Cannot getAccounts for update");
            log.error("grpc transfer: cannot getAccounts for update, id = {} senderId = {} receiverId ={}", id, sender, receiver);
          }
        });
    return future;
  }

  private Future<Void> insertTransferCertificate() {
    Future<Void> future = Future.future();
    Transfer transfer = Transfer.builder()
        .senderId(sender)
        .receiverId(receiver)
        .amount(amount)
        .message(message)
        .transferTime(transferTime)
        .build();
    transaction.execute(transferDA.insert(transfer))
        .setHandler(rs -> {
          if (rs.succeeded()) {
            transferId = rs.result().getId();
            future.complete();

          } else {
            future.fail("grpc transfer: Cannot insert transfer");
            log.error("grpc transfer: cannot insert transfer senderId = {} receiverId = {}", sender, receiver);
          }
        });
    return future;
  }

  private Future<Void> createTransferHistory() {

    historyForSenderDatabase = TransferHistory.builder()
        .transferId(transferId)
        .userId(sender)
        .partnerId(receiver)
        .transferType(0)
        .balance(senderAccount.getBalance())
        .build();

    historyForReceiverClient = TransferHistory.builder()
        .transferId(transferId)
        .userId(receiver)
        .partnerId(sender)
        .transferType(1)
        .balance(receiverAccount.getBalance())
        .amount(amount)
        .message(message)
        .transferTime(transferTime)
        .username(senderAccount.getUsername())
        .fullName(senderAccount.getFullName())
        .build();
    return Future.succeededFuture();
  }

  private TransferResponse createTransferResponse(boolean isSuccessful) {

    TransferResponse.Data data = null;
    if (isSuccessful) {
      errorCodeForClient = Code.SUCCESS;
      errorMessageForClient = "SUCCESS";
      HistoryItem historyItem = HistoryItem.newBuilder()
          .setPartnerId(sender)
          .setTransferType(HistoryItem.TransferType.SEND)
          .setAmount(amount)
          .setMessage(message)
          .setBalance(senderAccount.getBalance())
          .setTransferTime(transferTime)
          .setUsername(receiverAccount.getUsername())
          .setFullName(receiverAccount.getFullName())
          .build();
      data = TransferResponse.Data.newBuilder()
          .setHistoryItem(historyItem)
          .build();
    }

    Error error = Error
        .newBuilder()
        .setCode(errorCodeForClient)
        .setMessage(errorMessageForClient)
        .build();

    if (isSuccessful)
      return TransferResponse
          .newBuilder()
          .setData(data)
          .setError(error)
          .build();
    else
      return TransferResponse
          .newBuilder()
          .setError(error)
          .build();
  }
}
