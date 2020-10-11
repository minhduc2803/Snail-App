package vn.zalopay.ducnm8.handler.grpc;

import vn.zalopay.ducnm8.grpc.HelloReply;
import vn.zalopay.ducnm8.grpc.HelloRequest;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class GetBalanceHandler {
    public HelloReply getBalance(HelloRequest helloRequest){
        String message = "getBalance service say Hello to "+helloRequest.getName();
        log.info("Balance request from {}",helloRequest.getName());
        return HelloReply.newBuilder().setMessage(message).build();
    }
}
