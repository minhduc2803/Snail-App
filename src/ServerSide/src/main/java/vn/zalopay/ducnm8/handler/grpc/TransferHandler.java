package vn.zalopay.ducnm8.handler.grpc;

import hello.HelloReply;
import hello.HelloRequest;

public class TransferHandler {
    public HelloReply transfer(HelloRequest helloRequest){
        String message = "transfer service say Hello to "+helloRequest.getName();


        return HelloReply.newBuilder().setMessage(message).build();
    }
}
