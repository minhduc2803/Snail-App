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
import vn.zalopay.ducnm8.model.Notification;
import vn.zalopay.ducnm8.model.Transfer;
import vn.zalopay.ducnm8.model.TransferHistory;
import lombok.extern.log4j.Log4j2;

import java.time.Instant;

@Log4j2
public class TransferHandler {
    private final TransferDA transferDA;
    private final AccountDA accountDA;
    private final TransferHistoryDA transferHistoryDA;
    private final NotificationDA notificationDA;
    private final TransactionProvider transactionProvider;

    long sender;
    long receiver;
    long amount;
    String message;
    String password;
    long transferTime = Instant.now().getEpochSecond();

    String errorString = "";

    public TransferHandler(
            TransferDA transferDA,
            AccountDA accountDA,
            TransferHistoryDA transferHistoryDA,
            NotificationDA notificationDA,
            TransactionProvider transactionProvider) {
        this.transferDA = transferDA;
        this.accountDA = accountDA;
        this.transferHistoryDA = transferHistoryDA;
        this.notificationDA = notificationDA;
        this.transactionProvider = transactionProvider;
    }

    public void transfer(TransferRequest transferRequest, Future<TransferResponse> responseFuture) {

        sender = transferRequest.getSenderId();
        receiver = transferRequest.getReceiverId();
        amount = transferRequest.getAmount();
        message = transferRequest.getMessage();
        password = transferRequest.getPassword();

        Transaction transaction = transactionProvider.newTransaction();

        checkPassword()
                .compose(next -> transaction.begin())
                .compose(next -> isEnoughMoney())
                .compose(next -> transaction.execute(accountDA.plusBalanceByAmount(sender, -amount, transferTime)))
                .compose(next -> transaction.execute(accountDA.plusBalanceByAmount(receiver, amount, transferTime)))
                .compose(next -> transaction.execute(transferDA.insert(createTransferCertificate())))
                .compose(transfer -> transaction.execute(transferHistoryDA.insert(createTransferHistory(true, transfer.getId()))))
                .compose(transferHistory -> transaction.execute(transferHistoryDA.insert(createTransferHistory(false, transferHistory.getTransferId()))))
                .compose(next -> transaction.execute(notificationDA.insert(createNotification())))
                .setHandler(u -> {

                    TransferResponse response = null;

                    if (u.succeeded()) {
                        transaction
                                .commit()
                                .compose(next -> transaction.close());
                        response = createTransferResponse(true);
                        log.info("GRPC: transfer succeed");
                    } else {
                        errorString = u.cause().getMessage();
                        transaction
                                .rollback()
                                .compose(next -> transaction.close());
                        response = createTransferResponse(false);
                        log.error("GRPC: transfer failed");
                    }

                    responseFuture.complete(response);
                });
    }

    private Future<Boolean> checkPassword() {
        Future<Boolean> future = Future.future();

        accountDA.selectUserById(sender)
                .setHandler(rs -> {
                    if (rs.succeeded()) {
                        if (BCrypt.checkpw(password, rs.result().getPassword()))
                            future.complete(true);
                        else {
                            future.fail("Wrong password");
                            log.warn("grpc checkPassword failed ~ Wrong password id: {}",sender );
                        }
                    } else {
                        String errorString = String.format("grpc checkPassword failed ~  Cannot get password in database of account id: %s. The account may not exist", sender);
                        future.fail(errorString);
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
                        else
                            future.fail("Not enough money");
                    } else {
                        future.fail("Cannot get a balance");
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
        return isSender ?
                TransferHistory.builder()
                        .transferId(transfer_id)
                        .userId(sender)
                        .partnerId(receiver)
                        .transferType(1)
                        .build()
                :
                TransferHistory.builder()

                        .transferId(transfer_id)
                        .userId(receiver)
                        .partnerId(sender)
                        .transferType(2)
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
        TransferResponse.Data.IsSuccessful successful = TransferResponse.Data.IsSuccessful.TRUE;
        String errorMessage = "grpc: transfer succeeded";
        Code code = Code.SUCCESS;
        if (!isSuccessful) {
            successful = TransferResponse.Data.IsSuccessful.FALSE;
            errorMessage = "grpc: transfer failed";
            code = Code.INTERNAL_SERVER_ERROR;
        }
        TransferResponse.Data data = TransferResponse.Data
                .newBuilder()
                .setIsSuccessful(successful)
                .build();
        Error error = Error
                .newBuilder()
                .setCode(code)
                .setMessage(errorMessage)
                .build();
        return TransferResponse
                .newBuilder()
                .setData(data)
                .setError(error)
                .build();
    }
}
