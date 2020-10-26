package vn.zalopay.ducnm8.handler.grpc;

import fintech.BalanceRequest;
import fintech.BalanceResponse;
import fintech.Error;
import fintech.Code;
import io.vertx.core.Future;
import lombok.extern.log4j.Log4j2;
import vn.zalopay.ducnm8.da.interact.AccountDA;
import vn.zalopay.ducnm8.model.Balance;
import vn.zalopay.ducnm8.utils.JWTUtils;
import vn.zalopay.ducnm8.utils.Tracker;

@Log4j2
public class GetBalanceHandler {

  private static final String METRIC = "GetBalanceHandler";

  private static final Code code = Code.INTERNAL_SERVER_ERROR;
  private static final Error error = Error
      .newBuilder()
      .setCode(code)
      .setMessage("grpc: get balance failed")
      .build();
  private final BalanceResponse failedResponse = BalanceResponse
      .newBuilder()
      .setError(error)
      .build();
  private static final BalanceResponse.Builder balanceBuilder = BalanceResponse
      .newBuilder();
  private static final BalanceResponse.Data.Builder dataBuilder = BalanceResponse.Data
      .newBuilder();

  private final AccountDA accountDA;

  public GetBalanceHandler(AccountDA accountDA) {
    this.accountDA = accountDA;
  }

  public void getBalance(BalanceRequest request, Future<BalanceResponse> balanceResponseFuture) {

    Tracker.TrackerBuilder tracker =
        Tracker.builder().metricName(METRIC).startTime(System.currentTimeMillis());

    long id = JWTUtils.CLIENT_ID_CONTEXT_KEY.get();
    log.info("GRPC get balance request from: {}", id);
    accountDA.selectBalanceById(id)
        .setHandler(rs -> {
          BalanceResponse response = null;

          if (rs.succeeded()) {
            Balance balance = rs.result();
            dataBuilder
                .setBalance(balance.getBalance())
                .setLastTimeUpdateBalance(balance.getLastTimeUpdate())
                .build();
            response = balanceBuilder
                .setData(dataBuilder)
                .build();


            log.info("GRPC: getBalance succeed");
          } else {

            response = failedResponse;
            log.error("GRPC: getBalance failed");
          }

          balanceResponseFuture.complete(response);
          tracker.step("getBalance").code("SUCCESS").build().record();

        });

  }
}
