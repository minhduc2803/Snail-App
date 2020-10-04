package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import vn.zalopay.ducnm8.cache.RedisCache;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideRedisCacheFactory implements Factory<RedisCache> {
  private final ServiceModule module;

  public ServiceModule_ProvideRedisCacheFactory(ServiceModule module) {
    this.module = module;
  }

  @Override
  public RedisCache get() {
    return provideRedisCache(module);
  }

  public static ServiceModule_ProvideRedisCacheFactory create(ServiceModule module) {
    return new ServiceModule_ProvideRedisCacheFactory(module);
  }

  public static RedisCache provideRedisCache(ServiceModule instance) {
    return Preconditions.checkNotNull(instance.provideRedisCache(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
