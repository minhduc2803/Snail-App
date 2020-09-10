package bla.nah.example.dagger;

import bla.nah.example.cache.UserCache;
import bla.nah.example.da.TransactionProvider;
import bla.nah.example.da.UserDA;
import bla.nah.example.handler.RegisterHandler;
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
public final class ServiceModule_ProvideRegisterHandlerFactory implements Factory<RegisterHandler> {
  private final ServiceModule module;

  private final Provider<UserDA> userDAProvider;

  private final Provider<TransactionProvider> transactionProvider;

  private final Provider<UserCache> userCacheProvider;

  private final Provider<JWTAuth> jwtAuthProvider;

  public ServiceModule_ProvideRegisterHandlerFactory(ServiceModule module,
      Provider<UserDA> userDAProvider, Provider<TransactionProvider> transactionProvider,
      Provider<UserCache> userCacheProvider, Provider<JWTAuth> jwtAuthProvider) {
    this.module = module;
    this.userDAProvider = userDAProvider;
    this.transactionProvider = transactionProvider;
    this.userCacheProvider = userCacheProvider;
    this.jwtAuthProvider = jwtAuthProvider;
  }

  @Override
  public RegisterHandler get() {
    return provideRegisterHandler(module, userDAProvider.get(), transactionProvider.get(), userCacheProvider.get(), jwtAuthProvider.get());
  }

  public static ServiceModule_ProvideRegisterHandlerFactory create(ServiceModule module,
      Provider<UserDA> userDAProvider, Provider<TransactionProvider> transactionProvider,
      Provider<UserCache> userCacheProvider, Provider<JWTAuth> jwtAuthProvider) {
    return new ServiceModule_ProvideRegisterHandlerFactory(module, userDAProvider, transactionProvider, userCacheProvider, jwtAuthProvider);
  }

  public static RegisterHandler provideRegisterHandler(ServiceModule instance, UserDA userDA,
      TransactionProvider transactionProvider, UserCache userCache, JWTAuth jwtAuth) {
    return Preconditions.checkNotNull(instance.provideRegisterHandler(userDA, transactionProvider, userCache, jwtAuth), "Cannot return null from a non-@Nullable @Provides method");
  }
}
