package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.vertx.ext.auth.jwt.JWTAuth;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.handler.JWTAuthHandler;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideJWTAuthHandlerFactory implements Factory<JWTAuthHandler> {
  private final ServiceModule module;

  private final Provider<JWTAuth> authProvider;

  public ServiceModule_ProvideJWTAuthHandlerFactory(ServiceModule module,
      Provider<JWTAuth> authProvider) {
    this.module = module;
    this.authProvider = authProvider;
  }

  @Override
  public JWTAuthHandler get() {
    return provideJWTAuthHandler(module, authProvider.get());
  }

  public static ServiceModule_ProvideJWTAuthHandlerFactory create(ServiceModule module,
      Provider<JWTAuth> authProvider) {
    return new ServiceModule_ProvideJWTAuthHandlerFactory(module, authProvider);
  }

  public static JWTAuthHandler provideJWTAuthHandler(ServiceModule instance, JWTAuth authProvider) {
    return Preconditions.checkNotNull(instance.provideJWTAuthHandler(authProvider), "Cannot return null from a non-@Nullable @Provides method");
  }
}
