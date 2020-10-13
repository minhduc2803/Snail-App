package vn.zalopay.ducnm8.handler.grpc;

import io.grpc.*;
import io.jsonwebtoken.*;
import io.vertx.core.Future;
import lombok.Builder;
import vn.zalopay.ducnm8.utils.JWTUtils;


import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;
import static io.vertx.ext.sync.Sync.awaitResult;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Builder
public class InterceptorHandler implements ServerInterceptor {
    public InterceptorHandler(){}
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall,
        Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {

        String value = metadata.get(JWTUtils.AUTHORIZATION_METADATA_KEY);

        Status status = Status.OK;
        if (value == null) {
            status = Status.UNAUTHENTICATED.withDescription("Authorization token is missing");

            log.info("grpc authenticate failed ~ jwt token is missing");
        } else if (!value.startsWith(JWTUtils.BEARER_TYPE)) {
            status = Status.UNAUTHENTICATED.withDescription("Unknown authorization type");

            log.info("grpc authenticate failed ~ unknown authorization");
        } else {

            String token = value.substring(JWTUtils.BEARER_TYPE.length()).trim();
            try {

                Long id = JWTUtils.authenticate(token);
                Context ctx = Context.current()
                        .withValue(JWTUtils.CLIENT_ID_CONTEXT_KEY, id);

                log.info("grpc authenticate successfully");
                return Contexts.interceptCall(ctx, serverCall, metadata, serverCallHandler);
            } catch (JwtException e) {
                status = Status.UNAUTHENTICATED.withDescription(e.getMessage()).withCause(e);

                log.warn("grpc authenticate failed ~ cause: {}",e.getMessage());
            }

        }

        serverCall.close(status, new Metadata());
        return new ServerCall.Listener<ReqT>() {
        };
    }
}
