package bla.nah.example.dagger;

import bla.nah.example.cache.UserCache;
import bla.nah.example.da.TransactionProvider;
import bla.nah.example.da.UserDA;
import bla.nah.example.handler.UserListHandler;
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
public final class ServiceModule_ProvideUserListHandlerFactory implements Factory<UserListHandler> {
  private final ServiceModule module;

  private final Provider<UserDA> userDAProvider;

  private final Provider<TransactionProvider> transactionProvider;

  private final Provider<UserCache> userCacheProvider;

  private final Provider<JWTAuth> jwtAuthProvider;

  public ServiceModule_ProvideUserListHandlerFactory(ServiceModule module,
      Provider<UserDA> userDAProvider, Provider<TransactionProvider> transactionProvider,
      Provider<UserCache> userCacheProvider, Provider<JWTAuth> jwtAuthProvider) {
    this.module = module;
    this.userDAProvider = userDAProvider;
    this.transactionProvider = transactionProvider;
    this.userCacheProvider = userCacheProvider;
    this.jwtAuthProvider = jwtAuthProvider;
  }

  @Override
  public UserListHandler get() {
    return provideUserListHandler(module, userDAProvider.get(), transactionProvider.get(), userCacheProvider.get(), jwtAuthProvider.get());
  }

  public static ServiceModule_ProvideUserListHandlerFactory create(ServiceModule module,
      Provider<UserDA> userDAProvider, Provider<TransactionProvider> transactionProvider,
      Provider<UserCache> userCacheProvider, Provider<JWTAuth> jwtAuthProvider) {
    return new ServiceModule_ProvideUserListHandlerFactory(module, userDAProvider, transactionProvider, userCacheProvider, jwtAuthProvider);
  }

  public static UserListHandler provideUserListHandler(ServiceModule instance, UserDA userDA,
      TransactionProvider transactionProvider, UserCache userCache, JWTAuth jwtAuth) {
    return Preconditions.checkNotNull(instance.provideUserListHandler(userDA, transactionProvider, userCache, jwtAuth), "Cannot return null from a non-@Nullable @Provides method");
  }
}
