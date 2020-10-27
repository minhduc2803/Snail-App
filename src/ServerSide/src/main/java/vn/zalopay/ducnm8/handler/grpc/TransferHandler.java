package vn.zalopay.ducnm8.handler.grpc;

import fintech.*;
import io.vertx.core.Future;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.da.interact.AccountDA;
import vn.zalopay.ducnm8.da.interact.TransferDA;
import vn.zalopay.ducnm8.da.interact.TransferHistoryDA;
import vn.zalopay.ducnm8.handler.WSHandler;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TransferHandler {

  private final TransferDA transferDA;
  private final AccountDA accountDA;
  private final TransferHistoryDA transferHistoryDA;
  private final TransactionProvider transactionProvider;
  private final WSHandler wsHandler;

  public TransferHandler(
      TransferDA transferDA,
      AccountDA accountDA,
      TransferHistoryDA transferHistoryDA,
      TransactionProvider transactionProvider,
      WSHandler wsHandler) {
    this.transferDA = transferDA;
    this.accountDA = accountDA;
    this.transferHistoryDA = transferHistoryDA;
    this.transactionProvider = transactionProvider;
    this.wsHandler = wsHandler;
  }

  public void transfer(TransferRequest transferRequest, Future<TransferResponse> responseFuture) {

    _TransferHandler _transferHandler = new _TransferHandler(transferDA, accountDA, transferHistoryDA, transactionProvider, wsHandler);

    _transferHandler.transfer(transferRequest, responseFuture);
  }

}
