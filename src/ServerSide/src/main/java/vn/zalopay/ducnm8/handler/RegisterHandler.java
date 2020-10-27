package vn.zalopay.ducnm8.handler;

import vn.zalopay.ducnm8.cache.UserCache;
import vn.zalopay.ducnm8.da.Transaction;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.da.interact.AccountDA;
import vn.zalopay.ducnm8.entity.request.BaseRequest;
import vn.zalopay.ducnm8.entity.request.RegisterRequest;
import vn.zalopay.ducnm8.entity.response.BaseResponse;
import vn.zalopay.ducnm8.model.Account;
import vn.zalopay.ducnm8.utils.JsonProtoUtils;
import vn.zalopay.ducnm8.utils.Tracker;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Future;
import org.mindrot.jbcrypt.BCrypt;
import lombok.extern.log4j.Log4j2;

import java.time.Instant;

@Log4j2
public class RegisterHandler extends BaseHandler {
  private static final String METRIC = "RegisterHandler";
  private final UserCache userCache;
  private final AccountDA accountDA;
  private final TransactionProvider transactionProvider;

  public RegisterHandler(
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
    RegisterRequest registerRequest = JsonProtoUtils.parseGson(baseRequest.getPostData(), RegisterRequest.class);


    BaseResponse.BaseResponseBuilder response = BaseResponse.builder();


    if (registerRequest.getUsername() == null || registerRequest.getPassword() == null || registerRequest.getFullName() == null) {
      response.message("Lack of information")
          .status(HttpResponseStatus.BAD_REQUEST.code());

      future.complete(response.build());

      log.info("Register failed ~ Lack of information");
      return future;
    }

    log.info("Register begin, username = {}", registerRequest.getUsername());

    Account account = Account.builder()
        .username(registerRequest.getUsername())
        .fullName(registerRequest.getFullName())
        .password(BCrypt.hashpw(registerRequest.getPassword(), BCrypt.gensalt(4)))
        .balance(10000000)
        .lastTimeUpdateBalance(Instant.now().getEpochSecond())
        .numberNotification(0)
        .build();

    accountDA.insert(account)
        .setHandler(
            rs -> {

              if (rs.succeeded()) {
                response.status(HttpResponseStatus.OK.code());
                log.info("Register successful, username = {}", registerRequest.getUsername());
              } else {
                response.message("Username is not available")
                    .status(HttpResponseStatus.BAD_REQUEST.code());
                log.warn("Register fail ~ Cannot insert a user, username = {}", registerRequest.getUsername());
              }

              tracker.step("handle").code("SUCCESS").build().record();
              future.complete(response.build());
            });

    return future;
  }
}
