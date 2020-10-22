package vn.zalopay.ducnm8.grpc;

import fintech.*;
import hello.HelloReply;
import hello.HelloRequest;
import io.vertx.core.Future;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;
import vn.zalopay.ducnm8.handler.grpc.GetBalanceHandler;
import vn.zalopay.ducnm8.handler.grpc.GetHistoryHandler;
import vn.zalopay.ducnm8.handler.grpc.GetNotificationHandler;
import vn.zalopay.ducnm8.handler.grpc.TransferHandler;
import vn.zalopay.ducnm8.model.Transfer;


@Log4j2
public class FintechServiceImpl extends FintechServiceGrpc.FintechServiceVertxImplBase {

  private final GetBalanceHandler getBalanceHandler;
  private final GetHistoryHandler getHistoryHandler;
  private final TransferHandler transferHandler;
  private final GetNotificationHandler getNotificationHandler;

  @Builder
  public FintechServiceImpl(GetBalanceHandler getBalanceHandler,
                            GetHistoryHandler getHistoryHandler,
                            TransferHandler transferHandler,
                            GetNotificationHandler getNotificationHandler) {
    this.getBalanceHandler = getBalanceHandler;
    this.getHistoryHandler = getHistoryHandler;
    this.transferHandler = transferHandler;
    this.getNotificationHandler = getNotificationHandler;
  }

  @Override
  public void getBalance(BalanceRequest request, Future<BalanceResponse> response) {
    getBalanceHandler.getBalance(request, response);
  }

  @Override
  public void getHistory(HistoryRequest request, Future<HistoryResponse> response) {
    getHistoryHandler.getHistory(request, response);
  }

  @Override
  public void transfer(TransferRequest request, Future<TransferResponse> response) {
    transferHandler.transfer(request, response);
  }

  @Override
  public void getNotification(NotificationRequest request, Future<NotificationResponse> response) {
    getNotificationHandler.getNotification(request, response);
  }
}
