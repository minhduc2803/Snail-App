package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.vertx.core.Vertx;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
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
public final class ServiceModule_ProvideAuthProviderFactory implements Factory<JWTAuth> {
  private final ServiceModule module;

  private final Provider<Vertx> vertxProvider;

  private final Provider<JWTAuthOptions> authConfigProvider;

  public ServiceModule_ProvideAuthProviderFactory(ServiceModule module,
      Provider<Vertx> vertxProvider, Provider<JWTAuthOptions> authConfigProvider) {
    this.module = module;
    this.vertxProvider = vertxProvider;
    this.authConfigProvider = authConfigProvider;
  }

  @Override
  public JWTAuth get() {
    return provideAuthProvider(module, vertxProvider.get(), authConfigProvider.get());
  }

  public static ServiceModule_ProvideAuthProviderFactory create(ServiceModule module,
      Provider<Vertx> vertxProvider, Provider<JWTAuthOptions> authConfigProvider) {
    return new ServiceModule_ProvideAuthProviderFactory(module, vertxProvider, authConfigProvider);
  }

  public static JWTAuth provideAuthProvider(ServiceModule instance, Vertx vertx,
      JWTAuthOptions authConfig) {
    return Preconditions.checkNotNull(instance.provideAuthProvider(vertx, authConfig), "Cannot return null from a non-@Nullable @Provides method");
  }
}
