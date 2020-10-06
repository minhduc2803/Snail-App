package vn.zalopay.ducnm8.grpc;

import io.vertx.core.Future;
import lombok.Builder;

@Builder
public class GreeterImpl extends GreeterGrpc.GreeterVertxImplBase{

    public GreeterImpl(){

    }
    @Override
    public void sayHello(HelloRequest helloRequest, Future<HelloReply> helloReplyFuture){
        HelloReply helloReply = HelloReply.newBuilder().setMessage("Hello "+helloRequest.getName()).build();
        helloReplyFuture.complete(helloReply);
    }
}
