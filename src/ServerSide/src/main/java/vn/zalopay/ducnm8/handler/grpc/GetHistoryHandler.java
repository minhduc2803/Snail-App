package vn.zalopay.ducnm8.handler.grpc;

import fintech.*;
import fintech.Error;
import io.vertx.core.Future;
import vn.zalopay.ducnm8.da.interact.TransferHistoryDA;
import vn.zalopay.ducnm8.model.TransferHistory;
import vn.zalopay.ducnm8.utils.JWTUtils;
import lombok.extern.log4j.Log4j2;
import vn.zalopay.ducnm8.utils.Tracker;

import java.util.ArrayList;

@Log4j2
public class GetHistoryHandler {

  private static final String METRIC = "GetHistoryHandler";

  private final TransferHistoryDA transferHistoryDA;

  public GetHistoryHandler(TransferHistoryDA transferHistoryDA) {
    this.transferHistoryDA = transferHistoryDA;
  }

  public void getHistory(HistoryRequest historyRequest, Future<HistoryResponse> responseFuture) {

    Tracker.TrackerBuilder tracker =
        Tracker.builder().metricName(METRIC).startTime(System.currentTimeMillis());

    long id = JWTUtils.CLIENT_ID_CONTEXT_KEY.get();
    long offset = historyRequest.getOffset();

    log.info("GRPC get transfer history request senderId = {}", id);
    transferHistoryDA.getTransferHistoryByAccountId(id, offset)
        .setHandler(rs -> {
          HistoryResponse response = null;

          if (rs.succeeded()) {
            ArrayList<TransferHistory> history = rs.result();
            response = createSuccessHistory(history);
            log.info("GRPC: get transfer history succeed, senderId = {}", id);
          } else {
            response = createFailedHistory();
            log.error("GRPC: get transfer history failed, senderId = {}",id);
          }

          tracker.step("getHistory").code("SUCCESS").build().record();
          responseFuture.complete(response);
        });
  }

  private HistoryResponse createSuccessHistory(ArrayList<TransferHistory> history) {

    ArrayList<HistoryItem> historyItems = new ArrayList<HistoryItem>();
    for (TransferHistory item : history) {
      HistoryItem historyItem =
          HistoryItem
              .newBuilder()
              .setTransferType(item.getTransferType() == 0 ? HistoryItem.TransferType.SEND : HistoryItem.TransferType.RECEIVE)
              .setPartnerId(item.getPartnerId())
              .setAmount(item.getAmount())
              .setMessage(item.getMessage())
              .setBalance(item.getBalance())
              .setTransferTime(item.getTransferTime())
              .setUsername(item.getUsername())
              .setFullName(item.getFullName())
              .build();

      historyItems.add(historyItem);
    }
    HistoryResponse.Data data = HistoryResponse.Data
        .newBuilder()
        .addAllHistoryItems(historyItems)
        .build();
    Code code = Code.SUCCESS;
    fintech.Error error = Error
        .newBuilder()
        .setCode(code)
        .setMessage("grpc: get transfer history successfully")
        .build();
    return HistoryResponse
        .newBuilder()
        .setData(data)
        .setError(error)
        .build();
  }

  private HistoryResponse createFailedHistory() {
    Code code = Code.INTERNAL_SERVER_ERROR;
    fintech.Error error = Error
        .newBuilder()
        .setCode(code)
        .setMessage("grpc: get transfer history failed")
        .build();
    return HistoryResponse
        .newBuilder()
        .setError(error)
        .build();
  }
}
