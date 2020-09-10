package bla.nah.example.dagger;

import bla.nah.example.cache.UserCache;
import bla.nah.example.da.TransactionProvider;
import bla.nah.example.da.UserDA;
import bla.nah.example.handler.LoginHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.vertx.ext.auth.jwt.JWTAuth;
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
public final class ServiceModule_ProvideLoginHandlerFactory implements Factory<LoginHandler> {
  private final ServiceModule module;

  private final Provider<UserDA> userDAProvider;

  private final Provider<TransactionProvider> transactionProvider;

  private final Provider<UserCache> userCacheProvider;

  private final Provider<JWTAuth> jwtAuthProvider;

  public ServiceModule_ProvideLoginHandlerFactory(ServiceModule module,
      Provider<UserDA> userDAProvider, Provider<TransactionProvider> transactionProvider,
      Provider<UserCache> userCacheProvider, Provider<JWTAuth> jwtAuthProvider) {
    this.module = module;
    this.userDAProvider = userDAProvider;
    this.transactionProvider = transactionProvider;
    this.userCacheProvider = userCacheProvider;
    this.jwtAuthProvider = jwtAuthProvider;
  }

  @Override
  public LoginHandler get() {
    return provideLoginHandler(module, userDAProvider.get(), transactionProvider.get(), userCacheProvider.get(), jwtAuthProvider.get());
  }

  public static ServiceModule_ProvideLoginHandlerFactory create(ServiceModule module,
      Provider<UserDA> userDAProvider, Provider<TransactionProvider> transactionProvider,
      Provider<UserCache> userCacheProvider, Provider<JWTAuth> jwtAuthProvider) {
    return new ServiceModule_ProvideLoginHandlerFactory(module, userDAProvider, transactionProvider, userCacheProvider, jwtAuthProvider);
  }

  public static LoginHandler provideLoginHandler(ServiceModule instance, UserDA userDA,
      TransactionProvider transactionProvider, UserCache userCache, JWTAuth jwtAuth) {
    return Preconditions.checkNotNull(instance.provideLoginHandler(userDA, transactionProvider, userCache, jwtAuth), "Cannot return null from a non-@Nullable @Provides method");
  }
}
