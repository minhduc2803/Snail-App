package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.vertx.core.Vertx;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.grpc.FintechServiceImpl;
import vn.zalopay.ducnm8.handler.grpc.InterceptorHandler;
import vn.zalopay.ducnm8.server.GRPCServer;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideGRPCServerFactory implements Factory<GRPCServer> {
  private final ServiceModule module;

  private final Provider<Vertx> vertxProvider;

  private final Provider<FintechServiceImpl> fintechServiceProvider;

  private final Provider<InterceptorHandler> interceptorHandlerProvider;

  public ServiceModule_ProvideGRPCServerFactory(ServiceModule module, Provider<Vertx> vertxProvider,
      Provider<FintechServiceImpl> fintechServiceProvider,
      Provider<InterceptorHandler> interceptorHandlerProvider) {
    this.module = module;
    this.vertxProvider = vertxProvider;
    this.fintechServiceProvider = fintechServiceProvider;
    this.interceptorHandlerProvider = interceptorHandlerProvider;
  }

  @Override
  public GRPCServer get() {
    return provideGRPCServer(module, vertxProvider.get(), fintechServiceProvider.get(), interceptorHandlerProvider.get());
  }

  public static ServiceModule_ProvideGRPCServerFactory create(ServiceModule module,
      Provider<Vertx> vertxProvider, Provider<FintechServiceImpl> fintechServiceProvider,
      Provider<InterceptorHandler> interceptorHandlerProvider) {
    return new ServiceModule_ProvideGRPCServerFactory(module, vertxProvider, fintechServiceProvider, interceptorHandlerProvider);
  }

  public static GRPCServer provideGRPCServer(ServiceModule instance, Vertx vertx,
      FintechServiceImpl fintechService, InterceptorHandler interceptorHandler) {
    return Preconditions.checkNotNull(instance.provideGRPCServer(vertx, fintechService, interceptorHandler), "Cannot return null from a non-@Nullable @Provides method");
  }
}
