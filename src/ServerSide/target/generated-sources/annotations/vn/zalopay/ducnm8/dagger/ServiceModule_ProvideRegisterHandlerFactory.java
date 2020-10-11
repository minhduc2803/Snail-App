package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.vertx.ext.auth.jwt.JWTAuth;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.cache.UserCache;
import vn.zalopay.ducnm8.da.AccountDA;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.handler.RegisterHandler;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideRegisterHandlerFactory implements Factory<RegisterHandler> {
  private final ServiceModule module;

  private final Provider<AccountDA> accountDAProvider;

  private final Provider<TransactionProvider> transactionProvider;

  private final Provider<UserCache> userCacheProvider;

  private final Provider<JWTAuth> jwtAuthProvider;

  public ServiceModule_ProvideRegisterHandlerFactory(ServiceModule module,
      Provider<AccountDA> accountDAProvider, Provider<TransactionProvider> transactionProvider,
      Provider<UserCache> userCacheProvider, Provider<JWTAuth> jwtAuthProvider) {
    this.module = module;
    this.accountDAProvider = accountDAProvider;
    this.transactionProvider = transactionProvider;
    this.userCacheProvider = userCacheProvider;
    this.jwtAuthProvider = jwtAuthProvider;
  }

  @Override
  public RegisterHandler get() {
    return provideRegisterHandler(module, accountDAProvider.get(), transactionProvider.get(), userCacheProvider.get(), jwtAuthProvider.get());
  }

  public static ServiceModule_ProvideRegisterHandlerFactory create(ServiceModule module,
      Provider<AccountDA> accountDAProvider, Provider<TransactionProvider> transactionProvider,
      Provider<UserCache> userCacheProvider, Provider<JWTAuth> jwtAuthProvider) {
    return new ServiceModule_ProvideRegisterHandlerFactory(module, accountDAProvider, transactionProvider, userCacheProvider, jwtAuthProvider);
  }

  public static RegisterHandler provideRegisterHandler(ServiceModule instance, AccountDA accountDA,
      TransactionProvider transactionProvider, UserCache userCache, JWTAuth jwtAuth) {
    return Preconditions.checkNotNull(instance.provideRegisterHandler(accountDA, transactionProvider, userCache, jwtAuth), "Cannot return null from a non-@Nullable @Provides method");
  }
}
