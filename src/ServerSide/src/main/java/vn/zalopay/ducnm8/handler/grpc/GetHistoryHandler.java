package vn.zalopay.ducnm8.handler.grpc;

import vn.zalopay.ducnm8.grpc.HelloReply;
import vn.zalopay.ducnm8.grpc.HelloRequest;

public class GetHistoryHandler {
    public HelloReply getHistory(HelloRequest helloRequest){
        String message = "getHistory service say Hello to "+helloRequest.getName();


        return HelloReply.newBuilder().setMessage(message).build();
    }
}
