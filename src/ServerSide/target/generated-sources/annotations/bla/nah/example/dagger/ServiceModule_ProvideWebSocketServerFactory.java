package bla.nah.example.dagger;

import bla.nah.example.handler.WSHandler;
import bla.nah.example.server.WebSocketServer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.vertx.core.Vertx;
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
public final class ServiceModule_ProvideWebSocketServerFactory implements Factory<WebSocketServer> {
  private final ServiceModule module;

  private final Provider<WSHandler> wsHandlerProvider;

  private final Provider<Vertx> vertxProvider;

  private final Provider<JWTAuth> jwtAuthProvider;

  public ServiceModule_ProvideWebSocketServerFactory(ServiceModule module,
      Provider<WSHandler> wsHandlerProvider, Provider<Vertx> vertxProvider,
      Provider<JWTAuth> jwtAuthProvider) {
    this.module = module;
    this.wsHandlerProvider = wsHandlerProvider;
    this.vertxProvider = vertxProvider;
    this.jwtAuthProvider = jwtAuthProvider;
  }

  @Override
  public WebSocketServer get() {
    return provideWebSocketServer(module, wsHandlerProvider.get(), vertxProvider.get(), jwtAuthProvider.get());
  }

  public static ServiceModule_ProvideWebSocketServerFactory create(ServiceModule module,
      Provider<WSHandler> wsHandlerProvider, Provider<Vertx> vertxProvider,
      Provider<JWTAuth> jwtAuthProvider) {
    return new ServiceModule_ProvideWebSocketServerFactory(module, wsHandlerProvider, vertxProvider, jwtAuthProvider);
  }

  public static WebSocketServer provideWebSocketServer(ServiceModule instance, WSHandler wsHandler,
      Vertx vertx, JWTAuth jwtAuth) {
    return Preconditions.checkNotNull(instance.provideWebSocketServer(wsHandler, vertx, jwtAuth), "Cannot return null from a non-@Nullable @Provides method");
  }
}
