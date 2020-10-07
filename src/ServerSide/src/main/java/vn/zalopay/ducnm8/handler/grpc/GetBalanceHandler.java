package vn.zalopay.ducnm8.handler.grpc;

import vn.zalopay.ducnm8.grpc.HelloReply;
import vn.zalopay.ducnm8.grpc.HelloRequest;

public class GetBalanceHandler {
    public HelloReply getBalance(HelloRequest helloRequest){
        String message = "getBalance service say Hello to "+helloRequest.getName();

        return HelloReply.newBuilder().setMessage(message).build();
    }
}
