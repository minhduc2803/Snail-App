package vn.zalopay.ducnm8.handler;

import vn.zalopay.ducnm8.cache.UserCache;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.da.interact.AccountDA;
import vn.zalopay.ducnm8.entity.request.BaseRequest;
import vn.zalopay.ducnm8.entity.response.BaseResponse;
import vn.zalopay.ducnm8.utils.JWTUtils;
import vn.zalopay.ducnm8.utils.Tracker;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Future;
import io.vertx.core.http.HttpHeaders;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UserListHandler extends BaseHandler{

    private static final String METRIC = "UserListHandler";
    private final UserCache userCache;
    private final AccountDA accountDA;
    private final TransactionProvider transactionProvider;

    public UserListHandler(
            AccountDA accountDA, UserCache userCache, TransactionProvider transactionProvider) {
        this.userCache = userCache;
        this.accountDA = accountDA;
        this.transactionProvider = transactionProvider;
    }

    @Override
    public Future<BaseResponse> handle(BaseRequest baseRequest) {

        Tracker.TrackerBuilder tracker =
                Tracker.builder().metricName(METRIC).startTime(System.currentTimeMillis());
        Future<BaseResponse> future = Future.future();

        BaseResponse.BaseResponseBuilder response = BaseResponse.builder();
        try {

            log.info("get a user list");

            String token = baseRequest.getHeaders().get(HttpHeaders.AUTHORIZATION).substring("Bearer ".length()).trim();

            if(token == null){
                response.message("JWT token is missing")
                        .status(HttpResponseStatus.UNAUTHORIZED.code());
                future.complete(response.build());
                return future;
            }

            try {
                long id = JWTUtils.authenticate(token);
                accountDA.selectUserList(id)
                        .setHandler(userListRes -> {
                            if(userListRes.succeeded()){
                                response.data(userListRes.result())
                                        .status(HttpResponseStatus.OK.code());

                            }else{
                                response.message("Cannot get a user list")
                                        .status(HttpResponseStatus.BAD_REQUEST.code());
                            }
                            future.complete(response.build());
                        });
            }catch(Exception e){
                response.message("JWT token is invalid")
                        .status(HttpResponseStatus.UNAUTHORIZED.code());
                future.complete(response.build());
            }

        } catch (Exception e) {
            response.message("Server has an error")
                    .status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
            future.complete(response.build());
        }
        return future;
    }
}
