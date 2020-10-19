package vn.zalopay.ducnm8.cache;

import vn.zalopay.ducnm8.model.Account;
import vn.zalopay.ducnm8.utils.AsyncHandler;
import io.vertx.core.Future;
import lombok.Builder;
import org.redisson.api.RMap;

@Builder
public class UserCacheImpl implements UserCache {
    private final RedisCache redisCache;
    private final AsyncHandler asyncHandler;

    @Override
    public Future<Account> set(Account account) {
        Future future = Future.future();
        asyncHandler.run(
          () -> {
              try {
                  RMap<Object, Object> userMap =
                    redisCache.getRedissonClient().getMap(CacheKey.getUserKey(String.valueOf(account.getId())));
                  userMap.put("username", account.getUsername());
                  future.complete(account);
              } catch (Exception e) {
                  future.fail(e);
              }
          });
        return future;
    }
}
