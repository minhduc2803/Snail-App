package vn.zalopay.ducnm8.handler.grpc;

import io.grpc.*;
import io.vertx.core.Future;
import io.vertx.ext.auth.jwt.JWTAuth;
import vn.zalopay.ducnm8.utils.JWTUtils;


import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;
import static io.vertx.ext.sync.Sync.awaitResult;

public class InterceptorHandler implements ServerInterceptor {
    private JWTAuth jwtAuth;

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall,
        Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
//
//        String value = metadata.get(Metadata.Key.of("Authorization", ASCII_STRING_MARSHALLER));
//
//        Status status = Status.OK;
//
//        if (value == null)
//            status = Status.UNAUTHENTICATED.withDescription("Authorization is missing");
//        else if(!value.startsWith("Bearer"))
//            status = Status.UNAUTHENTICATED.withDescription("Unknown authorization type");
//        else {
//            String token = value.substring("Bearer ".length()).trim();
//
//            Integer id = awaitResult(
//
//            });
//
//
//
//        }
        Status status = Status.OK;

        serverCall.close(status, new Metadata());
        return new ServerCall.Listener<ReqT>() {
        };
    }
}
