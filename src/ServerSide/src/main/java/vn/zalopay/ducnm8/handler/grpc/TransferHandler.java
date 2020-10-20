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
import vn.zalopay.ducnm8.utils.Tracker;

import java.time.Instant;

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
    Account senderAccountAfter = null;
    Account receiverAccountAfter = null;
    TransferHistory historyForSenderDatabase = null;
    TransferHistory historyForReceiverClient = null;
    String errorMessageForClient = "Giao dịch thành công";
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

        sender = transferRequest.getSenderId();
        receiver = transferRequest.getReceiverId();
        amount = transferRequest.getAmount();
        message = transferRequest.getMessage();
        password = transferRequest.getPassword();
        transferTime = Instant.now().getEpochSecond();

        Transaction transaction = transactionProvider.newTransaction();

        checkPassword()
          .compose(next -> transaction.begin())
          .compose(next -> isEnoughMoney())
          .compose(next -> transaction.execute(accountDA.plusBalanceByAmount(sender, -amount, transferTime)))
          .compose(next -> getAccount(transaction, true))
          .compose(next -> transaction.execute(accountDA.plusBalanceByAmount(receiver, amount, transferTime)))
          .compose(next -> getAccount(transaction, false))
          .compose(next -> transaction.execute(transferDA.insert(createTransferCertificate())))
          .compose(transfer -> transaction.execute(transferHistoryDA.insert(createTransferHistory(true, transfer.getId()))))
          .compose(transferHistory -> transaction.execute(transferHistoryDA.insert(createTransferHistory(false, transferHistory.getTransferId()))))
          .compose(next -> transaction.execute(notificationDA.insert(createNotification())))
          .setHandler(rs -> {

              TransferResponse response = null;

              if (rs.succeeded()) {
                  transaction
                    .commit()
                    .compose(next -> transaction.close());
                  response = createTransferResponse(true);

                  wsHandler.sendTransferHistory(historyForReceiverClient, receiver);

                  log.info("GRPC: transfer succeed");
              } else {
                  errorString = rs.cause().getMessage();
                  transaction
                    .rollback()
                    .compose(next -> transaction.close());

                  response = createTransferResponse(false);

                  log.error("GRPC: transfer failed ~ cause {}", errorString);
              }

              tracker.step("transfer").code("SUCCESS").build().record();
              responseFuture.complete(response);
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
                      errorMessageForClient = "Mật khẩu không đúng !!!";
                      errorCodeForClient = Code.INCORRECT_PASSWORD;
                      log.warn("grpc checkPassword failed ~ Wrong password id: {}", sender);
                  }
              } else {
                  String errorString = String.format("grpc checkPassword failed ~  Cannot get password in database of account id: %s. The account may not exist", sender);
                  future.fail(errorString);
                  errorMessageForClient = "Internal Server Error !!!";
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
                      errorMessageForClient = "Số dư không đủ để thực hiện giao dịch !";
                      errorCodeForClient = Code.NOT_ENOUGH_MONEY;

                      log.info("grpc transfer isEnoughMoney failed ~ Not enough money");
                  }
              } else {
                  future.fail("Cannot get a balance");
                  errorMessageForClient = "INTERNAL SERVER ERROR !!!";
                  errorCodeForClient = Code.INTERNAL_SERVER_ERROR;
                  log.error("grpc transfer inEnoughMoney failed ~ Cannot get a balance");
              }
          });
        return future;
    }

    private Future<Void> getAccount(Transaction transaction, boolean isSender) {
        Future<Void> future = Future.future();
        transaction.execute(accountDA.selectAccountInsideTransaction(isSender ? sender : receiver))
          .setHandler(rs -> {
              if (rs.succeeded()) {
                  if (isSender) {
                      senderAccountAfter = rs.result();
                      log.info("balance = {}", senderAccountAfter.getBalance());
                  } else
                      receiverAccountAfter = rs.result();

                  future.complete();

              } else {
                  future.fail("grpc transfer: Cannot getAccount inside transaction");
                  log.error("grpc transfer: cannot getAccount inside transaction");
              }
          });
        return future;
    }

    private Transfer createTransferCertificate() {
        return Transfer.builder()
          .senderId(sender)
          .receiverId(receiver)
          .amount(amount)
          .message(message)
          .transferTime(transferTime)
          .build();
    }

    private TransferHistory createTransferHistory(boolean isSender, long transfer_id) {
        if (isSender) {
            historyForSenderDatabase = TransferHistory.builder()
              .transferId(transfer_id)
              .userId(sender)
              .partnerId(receiver)
              .transferType(0)
              .balance(senderAccountAfter.getBalance())
              .build();

            return historyForSenderDatabase;
        } else {
            historyForReceiverClient = TransferHistory.builder()
              .transferId(transfer_id)
              .userId(receiver)
              .partnerId(sender)
              .transferType(1)
              .balance(receiverAccountAfter.getBalance())
              .amount(amount)
              .message(message)
              .transferTime(transferTime)
              .username(senderAccountAfter.getUsername())
              .fullName(senderAccountAfter.getFullName())
              .build();
            return historyForReceiverClient;
        }

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
                errorMessageForClient = "INTERNAL SERVER ERROR";
                errorCodeForClient = Code.INTERNAL_SERVER_ERROR;
            }
        } else {
            HistoryItem historyItem = HistoryItem.newBuilder()
              .setPartnerId(sender)
              .setTransferType(HistoryItem.TransferType.SEND)
              .setAmount(amount)
              .setMessage(message)
              .setBalance(senderAccountAfter.getBalance())
              .setTransferTime(transferTime)
              .setUsername(receiverAccountAfter.getUsername())
              .setFullName(receiverAccountAfter.getFullName())
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
