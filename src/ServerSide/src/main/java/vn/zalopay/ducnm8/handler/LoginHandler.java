package vn.zalopay.ducnm8.handler;

import vn.zalopay.ducnm8.cache.UserCache;
import vn.zalopay.ducnm8.da.interact.AccountDA;
import vn.zalopay.ducnm8.da.TransactionProvider;
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
import org.mindrot.jbcrypt.BCrypt;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginHandler extends BaseHandler {

    private static final String METRIC = "LoginHandler";
    private final AccountDA accountDA;

    public LoginHandler(AccountDA accountDA, UserCache userCache, TransactionProvider transactionProvider) {
        this.accountDA = accountDA;
    }

    @Override
    public Future<BaseResponse> handle(BaseRequest baseRequest) {

        Future<BaseResponse> future = Future.future();

        BaseResponse.BaseResponseBuilder response = BaseResponse.builder();
        try {
            LoginRequest request = JsonProtoUtils.parseGson(baseRequest.getPostData(), LoginRequest.class);

            log.info("Login request from : " + request.getUsername());

            if (request.getUsername() == null || request.getPassword() == null) {

                response.message("Lack of information")
                  .status(HttpResponseStatus.BAD_REQUEST.code());
                future.complete(response.build());

                log.info("Login failed ~ Lack of information");
                return future;
            }
            accountDA.selectUserByUsername(request.getUsername())
              .setHandler(
                rs -> {
                    if (rs.succeeded()) {
                        User user = rs.result();
                        if (user != null) {
                            if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
                                String token = JWTUtils.buildJWTToken(user.getId());
                                log.info("token len: {}", token.length());
                                response.data(LoginResponse.builder()
                                  .token(token)
                                  .userId(user.getId())
                                  .fullName(user.getFullName())
                                  .build())
                                  .status(HttpResponseStatus.OK.code());
                                log.info("Login successfully");
                            } else {
                                response.message("Wrong password")
                                  .status(HttpResponseStatus.BAD_REQUEST.code());
                                log.info("Login failed from: {} ~ wrong password", user.getUsername());
                            }
                        } else {
                            response.message("User does not exist")
                              .status(HttpResponseStatus.BAD_REQUEST.code());
                            log.warn("Login failed from: {} ~ cannot find user with username", request.getUsername());
                        }

                    } else {
                        response.message("User does not exist")
                          .status(HttpResponseStatus.BAD_REQUEST.code());
                        log.warn("Login failed from: {} ~ SQL query did not succeed", request.getUsername());
                    }

                    future.complete(response.build());

                });
        } catch (Exception e) {
            log.error("Login failed ~ cause is: {}", e.getMessage());
            response.message("Server has an error")
              .status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
            future.complete(response.build());
        }
        return future;
    }
}
