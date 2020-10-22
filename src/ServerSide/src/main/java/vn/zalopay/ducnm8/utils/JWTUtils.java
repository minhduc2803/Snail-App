package vn.zalopay.ducnm8.utils;

import io.grpc.Context;
import io.grpc.Metadata;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

import io.jsonwebtoken.Jwts;

import lombok.extern.log4j.Log4j2;

import java.time.Instant;
import java.util.Date;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

@Log4j2
public class JWTUtils {
  public static final String JWT_SIGNING_KEY = "2002";
  public static final String BEARER_TYPE = "Bearer";

  public static final Metadata.Key<String> AUTHORIZATION_METADATA_KEY = Metadata.Key.of("Authorization", ASCII_STRING_MARSHALLER);
  public static final Context.Key<Long> CLIENT_ID_CONTEXT_KEY = Context.key("id");

  public static Long authenticate(String token) throws JwtException {
    String id = Jwts.parser()
        .setSigningKey(JWT_SIGNING_KEY)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
    return Long.valueOf(id);
  }

  public static String buildJWTToken(long account_id) {
    log.info("Create a new JWT token for id {}", account_id);
    return Jwts.builder()
        .setSubject(String.valueOf(account_id))
        .setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
        .signWith(
            SignatureAlgorithm.HS256,
            TextCodec.BASE64.decode(JWT_SIGNING_KEY))
        .compact();
  }
}