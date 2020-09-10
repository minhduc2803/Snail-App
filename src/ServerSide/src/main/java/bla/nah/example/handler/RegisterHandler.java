package bla.nah.example.handler;

import bla.nah.example.cache.UserCache;
import bla.nah.example.da.Transaction;
import bla.nah.example.da.TransactionProvider;
import bla.nah.example.da.UserDA;
import bla.nah.example.entity.request.BaseRequest;
import bla.nah.example.entity.request.LoginRequest;
import bla.nah.example.entity.request.RegisterRequest;
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

import java.sql.SQLException;

@Log4j2
public class RegisterHandler extends BaseHandler {
    private static final String METRIC = "RegisterHandler";
    private final UserCache userCache;
    private final UserDA userDA;
    private final TransactionProvider transactionProvider;
    private final JWTAuth jwtAuth;

    public RegisterHandler(
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
        RegisterRequest registerRequest = JsonProtoUtils.parseGson(baseRequest.getPostData(), RegisterRequest.class);

        log.info(" Username: " + registerRequest.getUsername());

        BaseResponse.BaseResponseBuilder response = BaseResponse.builder();


        if (registerRequest.getUsername() == null || registerRequest.getPassword() == null || registerRequest.getFullname() == null) {
            response.message("Lack of information")
                    .status(HttpResponseStatus.BAD_REQUEST.code());

            future.complete(response.build());
            return future;
        }

        User user = User.builder()
                .Username(registerRequest.getUsername())
                .Fullname(registerRequest.getFullname())
                .Password(BCrypt.hashpw(registerRequest.getPassword(), BCrypt.gensalt(4)))
                .build();


        Transaction transaction = transactionProvider.newTransaction();

        transaction
                .begin()
                .compose(next -> transaction.execute(userDA.insert(user)))
                .setHandler(
                        rs -> {

                            if (rs.succeeded()) {
                                response.status(HttpResponseStatus.OK.code());
                                log.info("Register successful");
                            } else {
                                response.message("Username is not available")
                                        .status(HttpResponseStatus.BAD_REQUEST.code());
                            }
                            future.complete(response.build());
                            transaction.commit();
                            transaction.close();

                            tracker.step("handle").code("SUCCESS").build().record();
                        });

        return future;
    }
}
