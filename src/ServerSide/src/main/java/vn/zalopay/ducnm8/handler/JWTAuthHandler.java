package vn.zalopay.ducnm8.handler;

import io.vertx.core.Future;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.web.RoutingContext;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;

@Builder
@Log4j2
public class JWTAuthHandler {

    private final JWTAuth authProvider;

    public void handle(RoutingContext rc) {
        log.info("");
        HttpServerRequest request = rc.request();
        HttpServerResponse response = rc.response();

        String token =
                request.headers().get(HttpHeaders.AUTHORIZATION).substring("Bearer ".length()).trim();
        authProvider.authenticate(
            new JsonObject().put("jwt", token),
            res -> {
                if(res.succeeded()) {
                    log.info("Authorization successful");
                    rc.next();

                }
                else{
                    Future.future().setHandler(handler -> {
                        log.info("Authorization failed");
                        response.setStatusCode(401).end("Failed");
                    });
                }
            });
    }

}