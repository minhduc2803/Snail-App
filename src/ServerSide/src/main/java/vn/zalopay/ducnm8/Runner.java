package vn.zalopay.ducnm8;

import vn.zalopay.ducnm8.config.FileConfigLoader;
import vn.zalopay.ducnm8.config.ServiceConfig;
import vn.zalopay.ducnm8.dagger.DaggerServiceComponent;
import vn.zalopay.ducnm8.dagger.ServiceComponent;
import vn.zalopay.ducnm8.dagger.ServiceModule;
import vn.zalopay.ducnm8.server.GRPCServer;
import vn.zalopay.ducnm8.server.RestfulAPI;
import vn.zalopay.ducnm8.server.WebSocketServer;
import vn.zalopay.ducnm8.utils.Tracker;
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

      GRPCServer grpcServer = component.getGRPCServer();

      component
          .getVertx()
          .deployVerticle(
              () ->
                  new AbstractVerticle() {

                    @Override
                    public void start() {
                      restfulAPI.start();
                      webSocketServer.start();
                      grpcServer.start();
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
