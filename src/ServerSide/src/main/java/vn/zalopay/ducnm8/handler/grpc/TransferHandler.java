package vn.zalopay.ducnm8.handler.grpc;

import fintech.NotificationRequest;
import fintech.NotificationResponse;
import fintech.TransferRequest;
import fintech.TransferResponse;
import hello.HelloReply;
import hello.HelloRequest;
import io.vertx.core.Future;
import vn.zalopay.ducnm8.da.Transaction;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.da.interact.AccountDA;
import vn.zalopay.ducnm8.da.interact.NotificationDA;
import vn.zalopay.ducnm8.da.interact.TransferDA;
import vn.zalopay.ducnm8.da.interact.TransferHistoryDA;
import vn.zalopay.ducnm8.model.Transfer;

import java.time.Instant;

public class TransferHandler {
    private TransferDA transferDA;
    private AccountDA accountDA;
    private TransferHistoryDA transferHistoryDA;
    private NotificationDA notificationDA;
    private final TransactionProvider transactionProvider;

    long sender;
    long receiver;
    long amount;
    String message;

    public TransferHandler(
         TransferDA transferDA,
         AccountDA accountDA,
         TransferHistoryDA transferHistoryDA,
         NotificationDA notificationDA,
         TransactionProvider transactionProvider
    ) {
        this.transferDA = transferDA;
        this.accountDA = accountDA;
        this.transferHistoryDA = transferHistoryDA;
        this.notificationDA = notificationDA;
        this.transactionProvider = transactionProvider;
    }

    public void transfer(TransferRequest transferRequest, Future<TransferResponse> responseFuture){

        sender = transferRequest.getSenderId();
        receiver = transferRequest.getReceiverId();
        amount = transferRequest.getAmount();
        message = transferRequest.getMessage();

        Transaction transaction = transactionProvider.newTransaction();
        transaction
            .begin()
            .compose(next -> isEnoughMoney())
            .compose(next -> transaction.execute(accountDA.plusBalanceByAmount(sender, - amount)))
            .compose(next -> transaction.execute(accountDA.plusBalanceByAmount(receiver, amount)))
            .compose(next -> transaction.execute(transferDA.insert(createTransferCertificate())))
            .compose(next -> transaction.execute(transferHistoryDA.insert(sender)))
            .compose(next -> transaction.execute(transferHistoryDA.insert(receiver)))
            .compose(next -> transaction.execute(notificationDA.insert(receiver)))
            .setHandler(rs -> {

            });


        responseFuture.complete();
    }

    private Future<Void> isEnoughMoney(){
        Future<Void> future = Future.future();
        accountDA.selectBalanceById(sender)
                .setHandler(balance -> {
                    if(balance.succeeded()){
                        if(balance.result().getBalance() >= amount)
                            future.complete();
                        else
                            future.fail("Not enough money");
                    }else{
                        future.fail("Cannot get a balance");
                    }
                });
        return future;
    }

    private Transfer createTransferCertificate(){
        return Transfer.builder()
                .senderId(sender)
                .receiverId(receiver)
                .amount(amount)
                .message(message)
                .transferTime(Instant.now().getEpochSecond())
                .build();
    }
}
