package bla.nah.example.handler;

import bla.nah.example.cache.UserCache;
import bla.nah.example.da.Transaction;
import bla.nah.example.da.TransactionProvider;
import bla.nah.example.da.UserDA;
import bla.nah.example.entity.request.BaseRequest;
import bla.nah.example.entity.response.BaseResponse;
import bla.nah.example.model.User;
import bla.nah.example.utils.Tracker;
import io.vertx.core.Future;

public class ExampleHandler extends BaseHandler {
  private static final String METRIC = "ExampleHandler";
  private final UserCache userCache;
  private final UserDA userDA;
  private final TransactionProvider transactionProvider;

  public ExampleHandler(
      UserDA userDA, UserCache userCache, TransactionProvider transactionProvider) {
    this.userCache = userCache;
    this.userDA = userDA;
    this.transactionProvider = transactionProvider;
  }

  @Override
  public Future<BaseResponse> handle(BaseRequest baseRequest) {

    Tracker.TrackerBuilder tracker =
        Tracker.builder().metricName(METRIC).startTime(System.currentTimeMillis());
    Future<User> future = Future.future();
    User user = User.builder().Username("username").Fullname("Minh Duc").Password("123").build();
    Transaction transaction = transactionProvider.newTransaction();
    transaction
        .begin()
        .compose(next -> transaction.execute(userDA.insert(user)))
        .setHandler(
            rs -> {
              if (rs.succeeded()) {
                future.complete(rs.result());
              } else {
                future.complete(null);
              }
              transaction.close();
              tracker.step("handle").code("SUCCESS").build().record();
            });

    return future.compose(u -> Future.succeededFuture(BaseResponse.builder().data(u).build()));
  }
}
