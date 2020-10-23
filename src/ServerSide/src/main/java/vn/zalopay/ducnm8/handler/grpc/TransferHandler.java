package vn.zalopay.ducnm8.handler.grpc;

import fintech.*;
import fintech.Error;
import io.vertx.core.Future;
import org.mindrot.jbcrypt.BCrypt;
import vn.zalopay.ducnm8.da.Transaction;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.da.interact.AccountDA;
import vn.zalopay.ducnm8.da.interact.NotificationDA;
import vn.zalopay.ducnm8.da.interact.TransferDA;
import vn.zalopay.ducnm8.da.interact.TransferHistoryDA;
import vn.zalopay.ducnm8.handler.WSHandler;
import vn.zalopay.ducnm8.model.*;
import lombok.extern.log4j.Log4j2;
import vn.zalopay.ducnm8.utils.JWTUtils;
import vn.zalopay.ducnm8.utils.Tracker;

import java.time.Instant;
import java.util.ArrayList;

@Log4j2
public class TransferHandler {

  private final String METRIC = "TransferHandler";
  private final TransferDA transferDA;
  private final AccountDA accountDA;
  private final TransferHistoryDA transferHistoryDA;
  private final NotificationDA notificationDA;
  private final TransactionProvider transactionProvider;
  private final WSHandler wsHandler;

  long sender;
  long receiver;
  long amount;
  String message;
  String password;
  long transferTime = Instant.now().getEpochSecond();

  String errorString = "";
  Account senderAccount = null;
  Account receiverAccount = null;
  long transferId;
  TransferHistory historyForSenderDatabase = null;
  TransferHistory historyForReceiverClient = null;
  String errorMessageForClient = "SUCCEED";
  Code errorCodeForClient = Code.SUCCESS;

  public TransferHandler(
      TransferDA transferDA,
      AccountDA accountDA,
      TransferHistoryDA transferHistoryDA,
      NotificationDA notificationDA,
      TransactionProvider transactionProvider,
      WSHandler wsHandler) {
    this.transferDA = transferDA;
    this.accountDA = accountDA;
    this.transferHistoryDA = transferHistoryDA;
    this.notificationDA = notificationDA;
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

    Transaction transaction = transactionProvider.newTransaction();

    checkPassword()
        .compose(next -> transaction.begin())
        .compose(next -> getAccounts(transaction))
        .compose(next -> isEnoughMoney())
        .compose(next -> transaction.execute(accountDA.plusBalanceByAmount(sender, -amount, transferTime)))
        .compose(next -> transaction.execute(accountDA.plusBalanceByAmount(receiver, amount, transferTime)))
        .compose(next -> getAccounts(transaction))
        .compose(next -> insertTransferCertificate(transaction))
        .compose(next -> transaction.commit())
        .setHandler(transferAsync -> {

          TransferResponse response = null;
          if (transferAsync.succeeded()) {
            transaction.close();

            response = createTransferResponse(true);

            responseFuture.complete(response);
            tracker.step("transfer").code("SUCCESS").build().record();
            wsHandler.sendTransferHistory(historyForReceiverClient, receiver);

            transferHistoryDA.insert(historyForSenderDatabase);
            transferHistoryDA.insert(historyForReceiverClient);
            notificationDA.insert(createNotification());

            log.info("GRPC: transfer succeed");

          } else {
            transaction
                .rollback()
                .compose(next -> transaction.close());
            response = createTransferResponse(false);

            responseFuture.complete(response);
            tracker.step("transfer").code("SUCCESS").build().record();

            log.error("GRPC: transfer failed ~ cause {}", transferAsync.cause().getMessage());
          }
        });

  }

  private Future<Void> checkPassword() {
    Future<Void> future = Future.future();

    accountDA.selectAccountById(sender)
        .setHandler(rs -> {
          if (rs.succeeded()) {
            if (BCrypt.checkpw(password, rs.result().getPassword()))
              future.complete();
            else {
              future.fail("Wrong password");
              errorMessageForClient = "WRONG_PASSWORD";
              errorCodeForClient = Code.INCORRECT_PASSWORD;
              log.warn("grpc checkPassword failed ~ Wrong password id: {}", sender);
            }
          } else {
            String errorString = String.format("grpc checkPassword failed ~  Cannot get password in database of account id: %s. The account may not exist", sender);
            future.fail(errorString);
            errorMessageForClient = "INTERNAL_SERVER_ERROR";
            errorCodeForClient = Code.INTERNAL_SERVER_ERROR;
            log.error(errorString);
          }
        });
    return future;
  }


  private Future<Void> isEnoughMoney() {
    Future<Void> future = Future.future();
    accountDA.selectBalanceById(sender)
        .setHandler(balance -> {
          if (balance.succeeded()) {
            if (balance.result().getBalance() >= amount)
              future.complete();
            else {
              future.fail("Not enough money");
              errorMessageForClient = "NOT_ENOUGH_MONEY";
              errorCodeForClient = Code.NOT_ENOUGH_MONEY;

              log.info("grpc transfer isEnoughMoney failed ~ Not enough money");
            }
          } else {
            future.fail("Cannot get a balance");
            errorMessageForClient = "INTERNAL_SERVER_ERROR";
            errorCodeForClient = Code.INTERNAL_SERVER_ERROR;
            log.error("grpc transfer inEnoughMoney failed ~ Cannot get a balance");
          }
        });
    return future;
  }

  private Future<Void> getAccounts(Transaction transaction) {
    Future<Void> future = Future.future();
    transaction.execute(accountDA.selectForUpdateTwoAccount(sender, receiver))
        .setHandler(rs -> {
          if (rs.succeeded() && rs.result().size() == 2) {
            ArrayList<Account> listAccount = rs.result();
            senderAccount = listAccount.get(0);
            receiverAccount = listAccount.get(1);
            if (sender != senderAccount.getId()) {
              senderAccount = listAccount.get(1);
              receiverAccount = listAccount.get(0);
            }

            future.complete();

          } else {
            future.fail("grpc transfer: Cannot getAccount inside transaction");
            log.error("grpc transfer: cannot getAccount inside transaction");
          }
        });
    return future;
  }

  private Future<Void> insertTransferCertificate(Transaction transaction) {
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
            future.fail("grpc transfer: Cannot insert transfer inside transaction");
            log.error("grpc transfer: cannot insert transfer  inside transaction");
          }
        });
    return future;
  }

  private void createTransferHistory() {

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
  }

  private Notification createNotification() {
    return Notification.builder()
        .notificationType(1)
        .userId(receiver)
        .partnerId(sender)
        .amount(amount)
        .message(message)
        .seen(false)
        .build();
  }

  private TransferResponse createTransferResponse(boolean isSuccessful) {

    TransferResponse.Data data = null;
    if (!isSuccessful) {
      if (errorCodeForClient == Code.SUCCESS) {
        errorMessageForClient = "INTERNAL_SERVER_ERROR";
        errorCodeForClient = Code.INTERNAL_SERVER_ERROR;
      }
    } else {
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
