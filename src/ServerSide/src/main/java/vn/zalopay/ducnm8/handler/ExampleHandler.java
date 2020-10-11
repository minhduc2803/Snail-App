package vn.zalopay.ducnm8.handler;

import vn.zalopay.ducnm8.cache.UserCache;
import vn.zalopay.ducnm8.da.Transaction;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.da.AccountDA;
import vn.zalopay.ducnm8.entity.request.BaseRequest;
import vn.zalopay.ducnm8.entity.response.BaseResponse;
import vn.zalopay.ducnm8.model.Account;
import vn.zalopay.ducnm8.utils.Tracker;
import io.vertx.core.Future;

public class ExampleHandler extends BaseHandler {
  private static final String METRIC = "ExampleHandler";
  private final UserCache userCache;
  private final AccountDA accountDA;
  private final TransactionProvider transactionProvider;

  public ExampleHandler(
          AccountDA accountDA, UserCache userCache, TransactionProvider transactionProvider) {
    this.userCache = userCache;
    this.accountDA = accountDA;
    this.transactionProvider = transactionProvider;
  }

  @Override
  public Future<BaseResponse> handle(BaseRequest baseRequest) {

    Tracker.TrackerBuilder tracker =
        Tracker.builder().metricName(METRIC).startTime(System.currentTimeMillis());
    Future<Account> future = Future.future();
    Account account = Account.builder().Username("username").Fullname("Minh Duc").Password("123").build();
    Transaction transaction = transactionProvider.newTransaction();
    transaction
        .begin()
        .compose(next -> transaction.execute(accountDA.insert(account)))
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
