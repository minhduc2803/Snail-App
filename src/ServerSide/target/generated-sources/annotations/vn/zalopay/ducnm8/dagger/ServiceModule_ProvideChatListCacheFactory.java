package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.cache.ChatListCache;
import vn.zalopay.ducnm8.cache.RedisCache;
import vn.zalopay.ducnm8.utils.AsyncHandler;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideChatListCacheFactory implements Factory<ChatListCache> {
  private final ServiceModule module;

  private final Provider<RedisCache> redisCacheProvider;

  private final Provider<AsyncHandler> asyncHandlerProvider;

  public ServiceModule_ProvideChatListCacheFactory(ServiceModule module,
      Provider<RedisCache> redisCacheProvider, Provider<AsyncHandler> asyncHandlerProvider) {
    this.module = module;
    this.redisCacheProvider = redisCacheProvider;
    this.asyncHandlerProvider = asyncHandlerProvider;
  }

  @Override
  public ChatListCache get() {
    return provideChatListCache(module, redisCacheProvider.get(), asyncHandlerProvider.get());
  }

  public static ServiceModule_ProvideChatListCacheFactory create(ServiceModule module,
      Provider<RedisCache> redisCacheProvider, Provider<AsyncHandler> asyncHandlerProvider) {
    return new ServiceModule_ProvideChatListCacheFactory(module, redisCacheProvider, asyncHandlerProvider);
  }

  public static ChatListCache provideChatListCache(ServiceModule instance, RedisCache redisCache,
      AsyncHandler asyncHandler) {
    return Preconditions.checkNotNull(instance.provideChatListCache(redisCache, asyncHandler), "Cannot return null from a non-@Nullable @Provides method");
  }
}
