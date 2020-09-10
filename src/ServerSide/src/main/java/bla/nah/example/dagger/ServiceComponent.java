package bla.nah.example.dagger;

import bla.nah.example.server.RestfulAPI;
import bla.nah.example.server.WebSocketServer;
import dagger.Component;
import io.vertx.core.Vertx;

import javax.inject.Singleton;

@Singleton
@Component(modules = {ServiceModule.class})
public interface ServiceComponent {
  RestfulAPI getRestfulAPI();

  WebSocketServer getWebSocketServer();

  Vertx getVertx();
}
