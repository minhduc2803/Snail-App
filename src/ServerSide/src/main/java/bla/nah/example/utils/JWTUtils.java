package bla.nah.example.utils;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.jwt.JWTOptions;


public class JWTUtils {
    public static Future<Integer> authenticate(JWTAuth jwtAuth, String token) {
        Future<Integer> future = Future.future();
        jwtAuth.authenticate(new JsonObject().put("jwt", token), event -> {
            if (event.succeeded()) {
                int UserID = event.result().principal().getInteger("UserID");
                future.complete(UserID);
            } else {
                future.fail(event.cause());
            }
        });
        return future;
    }

    public static String buildJWTToken(JWTAuth jwtAuth, int UserID){
        String token = jwtAuth.generateToken(
                new JsonObject()
                        .put("UserID", UserID),
                new JWTOptions()
                        .setExpiresInSeconds(3600));
        return token;
    }
}