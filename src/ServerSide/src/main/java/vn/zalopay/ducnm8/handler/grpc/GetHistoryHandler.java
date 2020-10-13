package vn.zalopay.ducnm8.handler.grpc;

import fintech.HistoryRequest;
import fintech.HistoryResponse;
import io.vertx.core.Future;

public class GetHistoryHandler {
    public void getHistory(HistoryRequest helloRequest, Future<HistoryResponse> responseFuture){
        responseFuture.complete();
    }
}
