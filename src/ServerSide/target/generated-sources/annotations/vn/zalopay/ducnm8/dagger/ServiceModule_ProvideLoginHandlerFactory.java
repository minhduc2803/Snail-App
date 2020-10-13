package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.cache.UserCache;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.da.interact.AccountDA;
import vn.zalopay.ducnm8.handler.LoginHandler;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideLoginHandlerFactory implements Factory<LoginHandler> {
  private final ServiceModule module;

  private final Provider<AccountDA> accountDAProvider;

  private final Provider<TransactionProvider> transactionProvider;

  private final Provider<UserCache> userCacheProvider;

  public ServiceModule_ProvideLoginHandlerFactory(ServiceModule module,
      Provider<AccountDA> accountDAProvider, Provider<TransactionProvider> transactionProvider,
      Provider<UserCache> userCacheProvider) {
    this.module = module;
    this.accountDAProvider = accountDAProvider;
    this.transactionProvider = transactionProvider;
    this.userCacheProvider = userCacheProvider;
  }

  @Override
  public LoginHandler get() {
    return provideLoginHandler(module, accountDAProvider.get(), transactionProvider.get(), userCacheProvider.get());
  }

  public static ServiceModule_ProvideLoginHandlerFactory create(ServiceModule module,
      Provider<AccountDA> accountDAProvider, Provider<TransactionProvider> transactionProvider,
      Provider<UserCache> userCacheProvider) {
    return new ServiceModule_ProvideLoginHandlerFactory(module, accountDAProvider, transactionProvider, userCacheProvider);
  }

  public static LoginHandler provideLoginHandler(ServiceModule instance, AccountDA accountDA,
      TransactionProvider transactionProvider, UserCache userCache) {
    return Preconditions.checkNotNull(instance.provideLoginHandler(accountDA, transactionProvider, userCache), "Cannot return null from a non-@Nullable @Provides method");
  }
}
