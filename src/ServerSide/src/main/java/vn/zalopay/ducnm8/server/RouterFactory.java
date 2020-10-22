package vn.zalopay.ducnm8.server;

import vn.zalopay.ducnm8.handler.HandlerFactory;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

import java.util.HashSet;
import java.util.Set;

public class RouterFactory {
  private RouterFactory() {
  }

  public static Router route(Vertx vertx, HandlerFactory handlerFactory) {
    Router router = Router.router(vertx);
    setWelcomePage(router);
    setHeaderAllowances(router);

    router.route().handler(BodyHandler.create());

    handlerFactory.initialize(router);
    return router;
  }

  private static void setWelcomePage(Router router) {
    router
        .route("/")
        .handler(
            routingContext -> {
              HttpServerResponse httpServerResponse = routingContext.response();
              httpServerResponse.putHeader("content-type", "text/html").end("<h1>Hello world</h1>");
            });
  }

  private static void setHeaderAllowances(Router router) {
    Set<String> allowedHeaders = new HashSet<>();
    allowedHeaders.add("x-requested-with");
    allowedHeaders.add("Access-Control-Allow-Origin");
    allowedHeaders.add("origin");
    allowedHeaders.add("accept");
    allowedHeaders.add("Content-Type");
    allowedHeaders.add("Authorization");

    Set<HttpMethod> allowedMethods = new HashSet<>();
    allowedMethods.add(HttpMethod.GET);
    allowedMethods.add(HttpMethod.POST);
    allowedMethods.add(HttpMethod.OPTIONS);
    allowedMethods.add(HttpMethod.DELETE);
    allowedMethods.add(HttpMethod.PATCH);
    allowedMethods.add(HttpMethod.PUT);

    router
        .route("/*")
        .handler(
            CorsHandler.create("*").allowedHeaders(allowedHeaders).allowedMethods(allowedMethods))
        .handler(BodyHandler.create());
  }
}
