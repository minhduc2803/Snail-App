package bla.nah.example;

import bla.nah.example.config.FileConfigLoader;
import bla.nah.example.config.ServiceConfig;
import bla.nah.example.dagger.DaggerServiceComponent;
import bla.nah.example.dagger.ServiceComponent;
import bla.nah.example.dagger.ServiceModule;
import bla.nah.example.server.RestfulAPI;
import bla.nah.example.server.WebSocketServer;
import bla.nah.example.utils.Tracker;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.VertxOptions;

public class Runner {

  public static void main(String[] args) {

    try {
      ServiceConfig serviceConfig =
          FileConfigLoader.loadFromEnv("service.conf", ServiceConfig.class);

      Tracker.initialize("example");

      ServiceModule module = ServiceModule.builder().serviceConfig(serviceConfig).build();
      ServiceComponent component = DaggerServiceComponent.builder().serviceModule(module).build();

      VertxOptions vertxOptions = new VertxOptions();
      DeploymentOptions deploymentOptions = new DeploymentOptions();
      deploymentOptions.setInstances(vertxOptions.getEventLoopPoolSize());
      RestfulAPI restfulAPI = component.getRestfulAPI();
      WebSocketServer webSocketServer = component.getWebSocketServer();

      component
          .getVertx()
          .deployVerticle(
              () ->
                  new AbstractVerticle() {

                    @Override
                    public void start() {
                      restfulAPI.start();
                      webSocketServer.start();
                    }

                    @Override
                    public void stop() throws Exception {
                      restfulAPI.close();
                      webSocketServer.close();
                    }
                  },
              new DeploymentOptions().setInstances(8));
    } catch (Exception e) {
      System.exit(1);
    }
  }
}
