package vn.zalopay.ducnm8.handler;

import vn.zalopay.ducnm8.cache.UserCache;
import vn.zalopay.ducnm8.da.Transaction;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.da.UserDA;
import vn.zalopay.ducnm8.entity.request.BaseRequest;
import vn.zalopay.ducnm8.entity.request.LoginRequest;
import vn.zalopay.ducnm8.entity.response.BaseResponse;
import vn.zalopay.ducnm8.entity.response.LoginResponse;
import vn.zalopay.ducnm8.model.User;
import vn.zalopay.ducnm8.utils.JWTUtils;
import vn.zalopay.ducnm8.utils.JsonProtoUtils;
import vn.zalopay.ducnm8.utils.Tracker;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Future;
import io.vertx.ext.auth.jwt.JWTAuth;
import org.mindrot.jbcrypt.BCrypt;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginHandler extends BaseHandler{

    private static final String METRIC = "LoginHandler";
    private final UserCache userCache;
    private final UserDA userDA;
    private final TransactionProvider transactionProvider;
    private final JWTAuth jwtAuth;

    public LoginHandler(
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
            LoginRequest request = JsonProtoUtils.parseGson(baseRequest.getPostData(), LoginRequest.class);

            log.info(" Username: "+request.getUsername());

            if(request.getUsername() == null || request.getPassword() == null){
                response.message("Lack of information")
                        .status(HttpResponseStatus.BAD_REQUEST.code());
                future.complete(response.build());
                return future;
            }

            Transaction transaction = transactionProvider.newTransaction();
            transaction
                .begin()
                .compose(next -> {
                    return userDA.selectUserByUsername(request.getUsername());
                })
                .setHandler(
                    rs -> {
                        if (rs.succeeded()) {
                            User user = rs.result();
                            if(user != null) {
                                if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {

                                    response.data(LoginResponse.builder()
                                            .token(JWTUtils.buildJWTToken(jwtAuth, user.getUserID()))
                                            .UserID(user.getUserID())
                                            .Fullname(user.getFullname())
                                            .build());
                                    response.status(HttpResponseStatus.OK.code());
                                } else {
                                    response.message("Wrong password")
                                            .status(HttpResponseStatus.BAD_REQUEST.code());
                                }
                            }else{
                                response.message("User does not exist")
                                        .status(HttpResponseStatus.BAD_REQUEST.code());
                            }

                        } else {
                            response.message("User is not exist")
                                    .status(HttpResponseStatus.BAD_REQUEST.code());
                        }
                        future.complete(response.build());
                        transaction.close();
                        tracker.step("handle").code("SUCCESS").build().record();
                    });

        } catch (Exception e) {
            log.error(e);
            response.message("Server has an error")
                    .status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
            future.complete(response.build());
        }
        return future;
    }
}
