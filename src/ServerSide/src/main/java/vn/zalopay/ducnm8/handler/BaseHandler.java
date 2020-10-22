package vn.zalopay.ducnm8.handler;

import vn.zalopay.ducnm8.entity.request.BaseRequest;
import vn.zalopay.ducnm8.entity.response.BaseResponse;
import vn.zalopay.ducnm8.utils.ExceptionUtil;
import vn.zalopay.ducnm8.utils.JsonProtoUtils;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class BaseHandler {

  public void handle(RoutingContext rc) {
    HttpServerRequest request = rc.request();
    HttpServerResponse response = rc.response();
    String requestPath = request.path();

    BaseRequest baseRequest =
        BaseRequest.builder()
            .requestPath(requestPath)
            .postData(rc.getBodyAsString())
            .params(request.params())
            .headers(request.headers())
            .build();

    handle(baseRequest)
        .setHandler(
            rs -> {
              if (rs.succeeded()) {

                response
                    .setStatusCode(rs.result().getStatus())
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .putHeader("Access-Control-Allow-Origin", "*")
                    .putHeader("Access-Control-Allow-Credentials", "true")
                    .putHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT")
                    .putHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers")
                    .end(JsonProtoUtils.printGson(rs.result()));
              } else {
                log.error(
                    "Handle request exception request={}",
                    JsonProtoUtils.printGson(baseRequest),
                    ExceptionUtil.getDetail(rs.cause()));
                response.setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).end();
              }
            });
  }

  public abstract Future<BaseResponse> handle(BaseRequest baseRequest);
}
