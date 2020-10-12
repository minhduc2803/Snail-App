package vn.zalopay.ducnm8.grpc;

import fintech.*;
import hello.HelloReply;
import hello.HelloRequest;
import io.vertx.core.Future;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;
import vn.zalopay.ducnm8.handler.grpc.GetBalanceHandler;
import vn.zalopay.ducnm8.handler.grpc.GetHistoryHandler;
import vn.zalopay.ducnm8.handler.grpc.GetNotificationHandler;
import vn.zalopay.ducnm8.handler.grpc.TransferHandler;


@Log4j2
@Builder
public class FintechServiceImpl extends FintechServiceGrpc.FintechServiceVertxImplBase{

    public FintechServiceImpl(){

    }

    @Override
    public void getBalance(BalanceRequest request, Future<BalanceResponse> response) {
        super.getBalance(request, response);
    }

    @Override
    public void getHistory(HistoryRequest request, Future<HistoryResponse> response) {
        super.getHistory(request, response);
    }

    @Override
    public void transfer(TransferRequest request, Future<TransferResponse> response) {
        super.transfer(request, response);
    }

    @Override
    public void getNotification(NotificationRequest request, Future<NotificationResponse> response) {
        super.getNotification(request, response);
    }
}
