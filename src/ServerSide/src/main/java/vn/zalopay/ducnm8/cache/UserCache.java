package vn.zalopay.ducnm8.cache;

import vn.zalopay.ducnm8.model.Account;
import io.vertx.core.Future;

public interface UserCache {
  Future<Account> set(Account account);
}
