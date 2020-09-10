package bla.nah.example.handler;

import bla.nah.example.cache.UserCache;
import bla.nah.example.da.Transaction;
import bla.nah.example.da.TransactionProvider;
import bla.nah.example.da.UserDA;
import bla.nah.example.entity.request.BaseRequest;
import bla.nah.example.entity.request.LoginRequest;
import bla.nah.example.entity.response.BaseResponse;
import bla.nah.example.entity.response.LoginResponse;
import bla.nah.example.model.User;
import bla.nah.example.utils.JWTUtils;
import bla.nah.example.utils.JsonProtoUtils;
import bla.nah.example.utils.Tracker;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Future;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.auth.jwt.JWTAuth;
import org.mindrot.jbcrypt.BCrypt;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;

@Log4j2
public class UserListHandler extends BaseHandler{

    private static final String METRIC = "UserListHandler";
    private final UserCache userCache;
    private final UserDA userDA;
    private final TransactionProvider transactionProvider;
    private final JWTAuth jwtAuth;

    public UserListHandler(
            UserDA userDA, UserCache userCache, TransactionProvider transactionProvider, JWTAuth jwtAuth) {
        this.userCache = userCache;
        this.userDA = userDA;
        this.transactionProvider = transactionProvider;
        this.jwtAuth = jwtAuth;
    }

    @Override
    public Future<BaseResponse> handle(BaseRequest baseRequest) {

        Tracker.TrackerBuilder tracker =
                Tracker.builder().metricName(METRIC).startTime(System.currentTimeMillis());
        Future<BaseResponse> future = Future.future();

        BaseResponse.BaseResponseBuilder response = BaseResponse.builder();
        try {

            log.info("");

            String token = baseRequest.getHeaders().get(HttpHeaders.AUTHORIZATION).substring("Bearer ".length()).trim();



            if(token == null){
                response.message("JWT token is missing")
                        .status(HttpResponseStatus.UNAUTHORIZED.code());
                future.complete(response.build());
                return future;
            }

            Future<Integer> UserIDAuth = JWTUtils.authenticate(jwtAuth, token);

            UserIDAuth.setHandler(UserIDRes -> {
                if(UserIDRes.succeeded()){

                    userDA.selectUserList(UserIDRes.result())
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

                }else{
                    response.message("JWT token is invalid")
                            .status(HttpResponseStatus.UNAUTHORIZED.code());
                    future.complete(response.build());
                }


            });
        } catch (Exception e) {
            response.message("Server has an error")
                    .status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
            future.complete(response.build());
        }
        return future;
    }
}
