package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.vertx.core.Vertx;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.handler.WSHandler;
import vn.zalopay.ducnm8.server.WebSocketServer;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideWebSocketServerFactory implements Factory<WebSocketServer> {
  private final ServiceModule module;

  private final Provider<WSHandler> wsHandlerProvider;

  private final Provider<Vertx> vertxProvider;

  public ServiceModule_ProvideWebSocketServerFactory(ServiceModule module,
      Provider<WSHandler> wsHandlerProvider, Provider<Vertx> vertxProvider) {
    this.module = module;
    this.wsHandlerProvider = wsHandlerProvider;
    this.vertxProvider = vertxProvider;
  }

  @Override
  public WebSocketServer get() {
    return provideWebSocketServer(module, wsHandlerProvider.get(), vertxProvider.get());
  }

  public static ServiceModule_ProvideWebSocketServerFactory create(ServiceModule module,
      Provider<WSHandler> wsHandlerProvider, Provider<Vertx> vertxProvider) {
    return new ServiceModule_ProvideWebSocketServerFactory(module, wsHandlerProvider, vertxProvider);
  }

  public static WebSocketServer provideWebSocketServer(ServiceModule instance, WSHandler wsHandler,
      Vertx vertx) {
    return Preconditions.checkNotNull(instance.provideWebSocketServer(wsHandler, vertx), "Cannot return null from a non-@Nullable @Provides method");
  }
}
