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
            response.message("Server has an error")
                    .status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
            future.complete(response.build());
        }
        return future;
    }
}
