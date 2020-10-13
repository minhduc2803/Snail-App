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

@Log4j2
public class GetBalanceHandler {

    private final AccountDA accountDA;

    public GetBalanceHandler(AccountDA accountDA) {
        this.accountDA = accountDA;
    }

    public void getBalance(BalanceRequest request, Future<BalanceResponse> balanceResponseFuture) {

        long id = JWTUtils.CLIENT_ID_CONTEXT_KEY.get();
        log.info("GRPC get balance request from: {}",id);
        accountDA.selectBalanceById(id)
            .setHandler(rs -> {
                BalanceResponse response = null;

                if(rs.succeeded()){
                    Balance balance = rs.result();
                    BalanceResponse.Data data = BalanceResponse.Data
                            .newBuilder()
                            .setBalance(balance.getBalance())
                            .setLastTimeUpdateBalance(balance.getLastTimeUpdate())
                            .build();
                    response = BalanceResponse
                            .newBuilder()
                            .setData(data)
                            .build();

                }else{
                    Code code = Code.INTERNAL_SERVER_ERROR;
                    Error error = Error
                            .newBuilder()
                            .setCode(code)
                            .setMessage("grpc: get balance failed")
                            .build();
                    response = BalanceResponse
                            .newBuilder()
                            .setError(error)
                            .build();
                }
                balanceResponseFuture.complete(response);
            });

    }
}
