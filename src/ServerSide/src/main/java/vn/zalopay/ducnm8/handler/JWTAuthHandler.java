package vn.zalopay.ducnm8.handler;


import io.jsonwebtoken.JwtException;
import io.vertx.core.Future;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;
import vn.zalopay.ducnm8.utils.JWTUtils;

@Builder
@Log4j2
public class JWTAuthHandler {

    public void handle(RoutingContext rc) {
        log.info("Restful API authenticator");
        HttpServerRequest request = rc.request();
        HttpServerResponse response = rc.response();

        try {
            String token = request
              .headers()
              .get(HttpHeaders.AUTHORIZATION)
              .substring("Bearer ".length())
              .trim();

            Long id = JWTUtils.authenticate(token);

            log.info("Restful API authenticate successfully");
            rc.next();
        } catch (Exception e) {
            log.info("Authorization failed ~ cause: {}", e.getMessage());
            response.setStatusCode(401).end("Failed");
        }
    }

}