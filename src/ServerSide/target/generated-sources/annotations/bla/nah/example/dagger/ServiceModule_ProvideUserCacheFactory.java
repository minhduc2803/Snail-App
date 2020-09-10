package bla.nah.example.dagger;

import bla.nah.example.cache.RedisCache;
import bla.nah.example.cache.UserCache;
import bla.nah.example.utils.AsyncHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideUserCacheFactory implements Factory<UserCache> {
  private final ServiceModule module;

  private final Provider<RedisCache> redisCacheProvider;

  private final Provider<AsyncHandler> asyncHandlerProvider;

  public ServiceModule_ProvideUserCacheFactory(ServiceModule module,
      Provider<RedisCache> redisCacheProvider, Provider<AsyncHandler> asyncHandlerProvider) {
    this.module = module;
    this.redisCacheProvider = redisCacheProvider;
    this.asyncHandlerProvider = asyncHandlerProvider;
  }

  @Override
  public UserCache get() {
    return provideUserCache(module, redisCacheProvider.get(), asyncHandlerProvider.get());
  }

  public static ServiceModule_ProvideUserCacheFactory create(ServiceModule module,
      Provider<RedisCache> redisCacheProvider, Provider<AsyncHandler> asyncHandlerProvider) {
    return new ServiceModule_ProvideUserCacheFactory(module, redisCacheProvider, asyncHandlerProvider);
  }

  public static UserCache provideUserCache(ServiceModule instance, RedisCache redisCache,
      AsyncHandler asyncHandler) {
    return Preconditions.checkNotNull(instance.provideUserCache(redisCache, asyncHandler), "Cannot return null from a non-@Nullable @Provides method");
  }
}
