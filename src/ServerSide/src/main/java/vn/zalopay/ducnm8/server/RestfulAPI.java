package vn.zalopay.ducnm8.server;

import vn.zalopay.ducnm8.handler.HandlerFactory;
import vn.zalopay.ducnm8.utils.ExceptionUtil;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RestfulAPI {
  private final Vertx vertx;
  private final int port;
  private HttpServer httpServer;
  private final HandlerFactory handlerFactory;

  @Builder
  public RestfulAPI(Vertx vertx, int port, HandlerFactory handlerFactory) {
    this.vertx = vertx;
    this.port = port;
    this.handlerFactory = handlerFactory;
  }

  public void start() {
    log.info("Starting RestfulAPI Server ...");

    Router router = RouterFactory.route(vertx, handlerFactory);

    Future<Void> future = Future.future();
    httpServer =
        vertx
            .createHttpServer()
            .requestHandler(router)
            .exceptionHandler(
                e -> log.error("Handle request exception {}", ExceptionUtil.getDetail(e)))
            .listen(
                port,
                ar -> {
                  if (ar.succeeded()) {
                    log.info("API Server start successfully !, port:{}", port);
                    future.complete();
                  } else {
                    log.error("API Server start fail. Reason: {}", ar.cause().getMessage());
                    future.fail(ar.cause());
                  }
                });
  }

  public void close() {
    httpServer.close();
  }
}
