package vn.zalopay.ducnm8.grpc;

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
    public void getBalance(HelloRequest helloRequest, Future<HelloReply> helloReplyFuture){
        log.info("GRPC Request from: "+helloRequest.getName());
        GetBalanceHandler getBalanceHandler = new GetBalanceHandler();
        HelloReply helloReply = getBalanceHandler.getBalance(helloRequest);
        helloReplyFuture.complete(helloReply);
    }

    @Override
    public void getHistory(HelloRequest helloRequest, Future<HelloReply> helloReplyFuture){
        log.info("GRPC Request from: "+helloRequest.getName());
        GetHistoryHandler getHistoryHandler = new GetHistoryHandler();
        HelloReply helloReply = getHistoryHandler.getHistory(helloRequest);
        helloReplyFuture.complete(helloReply);
    }

    @Override
    public void transfer(HelloRequest helloRequest, Future<HelloReply> helloReplyFuture){
        log.info("GRPC Request from: "+helloRequest.getName());
        TransferHandler transferHandler = new TransferHandler();
        HelloReply helloReply = transferHandler.transfer(helloRequest);
        helloReplyFuture.complete(helloReply);
    }

    @Override
    public void getNotification(HelloRequest helloRequest, Future<HelloReply> helloReplyFuture){
        log.info("GRPC Request from: "+helloRequest.getName());
        GetNotificationHandler getNotificationHandler = new GetNotificationHandler();
        HelloReply helloReply = getNotificationHandler.getNotification(helloRequest);
        helloReplyFuture.complete(helloReply);
    }
}
