package bla.nah.example.cache;

import bla.nah.example.model.User;
import io.vertx.core.Future;

public interface UserCache {
  Future<User> set(User user);
}
