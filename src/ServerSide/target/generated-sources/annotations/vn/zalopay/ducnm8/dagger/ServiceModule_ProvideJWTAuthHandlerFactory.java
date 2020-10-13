package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
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

  public ServiceModule_ProvideJWTAuthHandlerFactory(ServiceModule module) {
    this.module = module;
  }

  @Override
  public JWTAuthHandler get() {
    return provideJWTAuthHandler(module);
  }

  public static ServiceModule_ProvideJWTAuthHandlerFactory create(ServiceModule module) {
    return new ServiceModule_ProvideJWTAuthHandlerFactory(module);
  }

  public static JWTAuthHandler provideJWTAuthHandler(ServiceModule instance) {
    return Preconditions.checkNotNull(instance.provideJWTAuthHandler(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
