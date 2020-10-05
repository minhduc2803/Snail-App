package vn.zalopay.ducnm8.dagger;

import vn.zalopay.ducnm8.server.GRPCServer;
import vn.zalopay.ducnm8.server.RestfulAPI;
import vn.zalopay.ducnm8.server.WebSocketServer;
import dagger.Component;
import io.vertx.core.Vertx;

import javax.inject.Singleton;

@Singleton
@Component(modules = {ServiceModule.class})
public interface ServiceComponent {
  RestfulAPI getRestfulAPI();

  WebSocketServer getWebSocketServer();

  Vertx getVertx();

  GRPCServer getGRPCServer();
}
