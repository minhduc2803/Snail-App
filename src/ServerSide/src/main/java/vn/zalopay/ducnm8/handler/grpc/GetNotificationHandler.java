package vn.zalopay.ducnm8.handler.grpc;

import vn.zalopay.ducnm8.grpc.HelloReply;
import vn.zalopay.ducnm8.grpc.HelloRequest;

public class GetNotificationHandler {
    public HelloReply getNotification(HelloRequest helloRequest){
        String message = "getNotification service say Hello to "+helloRequest.getName();


        return HelloReply.newBuilder().setMessage(message).build();
    }
}