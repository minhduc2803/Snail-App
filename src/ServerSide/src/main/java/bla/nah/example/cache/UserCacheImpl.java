package bla.nah.example.cache;

import bla.nah.example.model.User;
import bla.nah.example.utils.AsyncHandler;
import io.vertx.core.Future;
import lombok.Builder;
import org.redisson.api.RMap;

@Builder
public class UserCacheImpl implements UserCache {
  private final RedisCache redisCache;
  private final AsyncHandler asyncHandler;

  @Override
  public Future<User> set(User user) {
    Future future = Future.future();
    asyncHandler.run(
        () -> {
          try {
            RMap<Object, Object> userMap =
                redisCache.getRedissonClient().getMap(CacheKey.getUserKey(String.valueOf(user.getUserID())));
            userMap.put("username", user.getUsername());
            future.complete(user);
          } catch (Exception e) {
            future.fail(e);
          }
        });
    return future;
  }
}
