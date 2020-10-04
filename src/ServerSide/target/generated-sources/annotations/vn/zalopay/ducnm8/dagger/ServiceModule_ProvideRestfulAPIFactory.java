package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.vertx.core.Vertx;
import io.vertx.ext.auth.jwt.JWTAuth;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.handler.HandlerFactory;
import vn.zalopay.ducnm8.server.RestfulAPI;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideRestfulAPIFactory implements Factory<RestfulAPI> {
  private final ServiceModule module;

  private final Provider<HandlerFactory> handlerFactoryProvider;

  private final Provider<Vertx> vertxProvider;

  private final Provider<JWTAuth> authProvider;

  public ServiceModule_ProvideRestfulAPIFactory(ServiceModule module,
      Provider<HandlerFactory> handlerFactoryProvider, Provider<Vertx> vertxProvider,
      Provider<JWTAuth> authProvider) {
    this.module = module;
    this.handlerFactoryProvider = handlerFactoryProvider;
    this.vertxProvider = vertxProvider;
    this.authProvider = authProvider;
  }

  @Override
  public RestfulAPI get() {
    return provideRestfulAPI(module, handlerFactoryProvider.get(), vertxProvider.get(), authProvider.get());
  }

  public static ServiceModule_ProvideRestfulAPIFactory create(ServiceModule module,
      Provider<HandlerFactory> handlerFactoryProvider, Provider<Vertx> vertxProvider,
      Provider<JWTAuth> authProvider) {
    return new ServiceModule_ProvideRestfulAPIFactory(module, handlerFactoryProvider, vertxProvider, authProvider);
  }

  public static RestfulAPI provideRestfulAPI(ServiceModule instance, HandlerFactory handlerFactory,
      Vertx vertx, JWTAuth authProvider) {
    return Preconditions.checkNotNull(instance.provideRestfulAPI(handlerFactory, vertx, authProvider), "Cannot return null from a non-@Nullable @Provides method");
  }
}
