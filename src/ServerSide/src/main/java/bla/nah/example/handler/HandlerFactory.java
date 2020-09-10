package bla.nah.example.handler;

import bla.nah.example.config.APIPath;
import bla.nah.example.da.BaseTransactionDA;
import com.google.common.collect.ImmutableMap;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import lombok.Builder;


@Builder
public class HandlerFactory {
  private BaseHandler echoHandler;
  private BaseHandler exampleHandler;
  private BaseHandler loginHandler;
  private BaseHandler registerHandler;
  private BaseHandler conversationListHandler;
  private BaseHandler chatListHandler;
  private JWTAuthHandler jwtAuthHandler;
  private UserListHandler userListHandler;

  public void initialize(Router router) {

    router.route("/api/protected/*").handler(jwtAuthHandler::handle);

    ImmutableMap<String, BaseHandler> postHandler =
        ImmutableMap.<String, BaseHandler>builder()
          .put(APIPath.ECHO, echoHandler)
          .put(APIPath.EXAMPLE, exampleHandler)
          .put(APIPath.LOGIN, loginHandler)
          .put(APIPath.REGISTER, registerHandler)
          .put(APIPath.LIST_CHAT,chatListHandler)
          .build();

    postHandler
        .entrySet()
        .forEach(
            entry ->
                router
                  .route()
                  .method(HttpMethod.POST)
                  .path(entry.getKey())
                  .handler(entry.getValue()::handle));

    ImmutableMap<String, BaseHandler> getHandler =
      ImmutableMap.<String, BaseHandler>builder()
        .put(APIPath.LIST_CONVERSATION, conversationListHandler)
        .put(APIPath.LIST_USER, userListHandler)
        .build();

    getHandler
        .entrySet()
        .forEach(
            entry ->
                router
                  .route()
                  .method(HttpMethod.GET)
                  .path(entry.getKey())
                  .handler(entry.getValue()::handle));
  }
}
