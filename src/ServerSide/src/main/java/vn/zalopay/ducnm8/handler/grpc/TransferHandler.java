package vn.zalopay.ducnm8.handler.grpc;

import vn.zalopay.ducnm8.grpc.HelloReply;
import vn.zalopay.ducnm8.grpc.HelloRequest;

public class TransferHandler {
    public HelloReply transfer(HelloRequest helloRequest){
        String message = "transfer service say Hello to "+helloRequest.getName();


        return HelloReply.newBuilder().setMessage(message).build();
    }
}
