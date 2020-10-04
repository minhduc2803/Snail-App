package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.cache.UserCache;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.da.UserDA;
import vn.zalopay.ducnm8.handler.ExampleHandler;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideExampleHandlerFactory implements Factory<ExampleHandler> {
  private final ServiceModule module;

  private final Provider<UserDA> userDAProvider;

  private final Provider<TransactionProvider> transactionProvider;

  private final Provider<UserCache> userCacheProvider;

  public ServiceModule_ProvideExampleHandlerFactory(ServiceModule module,
      Provider<UserDA> userDAProvider, Provider<TransactionProvider> transactionProvider,
      Provider<UserCache> userCacheProvider) {
    this.module = module;
    this.userDAProvider = userDAProvider;
    this.transactionProvider = transactionProvider;
    this.userCacheProvider = userCacheProvider;
  }

  @Override
  public ExampleHandler get() {
    return provideExampleHandler(module, userDAProvider.get(), transactionProvider.get(), userCacheProvider.get());
  }

  public static ServiceModule_ProvideExampleHandlerFactory create(ServiceModule module,
      Provider<UserDA> userDAProvider, Provider<TransactionProvider> transactionProvider,
      Provider<UserCache> userCacheProvider) {
    return new ServiceModule_ProvideExampleHandlerFactory(module, userDAProvider, transactionProvider, userCacheProvider);
  }

  public static ExampleHandler provideExampleHandler(ServiceModule instance, UserDA userDA,
      TransactionProvider transactionProvider, UserCache userCache) {
    return Preconditions.checkNotNull(instance.provideExampleHandler(userDA, transactionProvider, userCache), "Cannot return null from a non-@Nullable @Provides method");
  }
}
