package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.cache.UserCache;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.da.interact.AccountDA;
import vn.zalopay.ducnm8.handler.UserListHandler;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideUserListHandlerFactory implements Factory<UserListHandler> {
  private final ServiceModule module;

  private final Provider<AccountDA> accountDAProvider;

  private final Provider<TransactionProvider> transactionProvider;

  private final Provider<UserCache> userCacheProvider;

  public ServiceModule_ProvideUserListHandlerFactory(ServiceModule module,
      Provider<AccountDA> accountDAProvider, Provider<TransactionProvider> transactionProvider,
      Provider<UserCache> userCacheProvider) {
    this.module = module;
    this.accountDAProvider = accountDAProvider;
    this.transactionProvider = transactionProvider;
    this.userCacheProvider = userCacheProvider;
  }

  @Override
  public UserListHandler get() {
    return provideUserListHandler(module, accountDAProvider.get(), transactionProvider.get(), userCacheProvider.get());
  }

  public static ServiceModule_ProvideUserListHandlerFactory create(ServiceModule module,
      Provider<AccountDA> accountDAProvider, Provider<TransactionProvider> transactionProvider,
      Provider<UserCache> userCacheProvider) {
    return new ServiceModule_ProvideUserListHandlerFactory(module, accountDAProvider, transactionProvider, userCacheProvider);
  }

  public static UserListHandler provideUserListHandler(ServiceModule instance, AccountDA accountDA,
      TransactionProvider transactionProvider, UserCache userCache) {
    return Preconditions.checkNotNull(instance.provideUserListHandler(accountDA, transactionProvider, userCache), "Cannot return null from a non-@Nullable @Provides method");
  }
}
