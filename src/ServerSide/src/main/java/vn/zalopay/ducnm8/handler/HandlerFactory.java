package vn.zalopay.ducnm8.handler;

import vn.zalopay.ducnm8.config.APIPath;
import com.google.common.collect.ImmutableMap;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import lombok.Builder;


@Builder
public class HandlerFactory {
  private final BaseHandler loginHandler;
  private final BaseHandler registerHandler;
  private final BaseHandler chatListHandler;
  private final JWTAuthHandler jwtAuthHandler;
  private final UserListHandler userListHandler;

  public void initialize(Router router) {

    router.route("/api/protected/*").handler(jwtAuthHandler::handle);

    ImmutableMap<String, BaseHandler> postHandler =
        ImmutableMap.<String, BaseHandler>builder()
            .put(APIPath.LOGIN, loginHandler)
            .put(APIPath.REGISTER, registerHandler)
            .put(APIPath.LIST_CHAT, chatListHandler)
            .build();

    postHandler
        .forEach((key, value) -> router
            .route()
            .method(HttpMethod.POST)
            .path(key)
            .handler(value::handle));

    ImmutableMap<String, BaseHandler> getHandler =
        ImmutableMap.<String, BaseHandler>builder()
            .put(APIPath.LIST_USER, userListHandler)
            .build();

    getHandler
        .forEach((key, value) -> router
            .route()
            .method(HttpMethod.GET)
            .path(key)
            .handler(value::handle));
  }
}
