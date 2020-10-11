package vn.zalopay.ducnm8.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.jwt.JWTOptions;
import io.jsonwebtoken.Jwts;

import lombok.extern.log4j.Log4j2;

import java.time.Instant;
import java.util.Date;

@Log4j2
public class JWTUtils {
    public static Future<Long> authenticate(JWTAuth jwtAuth, String token) {
        Future<Long> future = Future.future();
        jwtAuth.authenticate(new JsonObject().put("jwt", token), event -> {
            if (event.succeeded()) {
                long UserID = event.result().principal().getLong("UserID");
                future.complete(UserID);
            } else {
                future.fail(event.cause());
            }
        });
        return future;
    }

    public static String buildJWTToken(long account_id){
        log.info("Create a new JWT token for id {}",account_id);
        return Jwts.builder()
                .claim("id",account_id)
                .setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
                .signWith(
                    SignatureAlgorithm.HS256,
                    TextCodec.BASE64.decode("2002"))
                .compact();
//        return jwtAuth.generateToken(
//                new JsonObject()
//                        .put("UserID", account_id),
//                new JWTOptions()
//                        .setExpiresInSeconds(3600));
    }
}