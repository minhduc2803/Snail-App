package vn.zalopay.ducnm8.cache;

import vn.zalopay.ducnm8.model.User;
import io.vertx.core.Future;

public interface UserCache {
  Future<User> set(User user);
}
