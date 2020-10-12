package vn.zalopay.ducnm8.handler.grpc;

import hello.HelloReply;
import hello.HelloRequest;

public class GetHistoryHandler {
    public HelloReply getHistory(HelloRequest helloRequest){
        String message = "getHistory service say Hello to "+helloRequest.getName();


        return HelloReply.newBuilder().setMessage(message).build();
    }
}
