package vn.zalopay.ducnm8.handler.grpc;

import fintech.HistoryRequest;
import fintech.HistoryResponse;
import fintech.NotificationRequest;
import fintech.NotificationResponse;
import hello.HelloReply;
import hello.HelloRequest;
import io.vertx.core.Future;

public class GetNotificationHandler {

    public void getNotification(NotificationRequest helloRequest, Future<NotificationResponse> responseFuture){
        responseFuture.complete();
    }
}
