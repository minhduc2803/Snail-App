package vn.zalopay.ducnm8.grpc;

import static vn.zalopay.ducnm8.grpc.GreeterGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by VertxGrpc generator",
comments = "Source: hello.proto")
public final class VertxGreeterGrpc {
    private VertxGreeterGrpc() {}

    public static VertxGreeterStub newVertxStub(io.grpc.Channel channel) {
        return new VertxGreeterStub(channel);
    }

    /**
     * <pre>
     *  The greeting service definition.
     * </pre>
     */
    public static final class VertxGreeterStub extends io.grpc.stub.AbstractStub<VertxGreeterStub> {
        private GreeterGrpc.GreeterStub delegateStub;

        private VertxGreeterStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = GreeterGrpc.newStub(channel);
        }

        private VertxGreeterStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = GreeterGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected VertxGreeterStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new VertxGreeterStub(channel, callOptions);
        }

        /**
         * <pre>
         *  Sends a greeting
         * </pre>
         */
        public io.vertx.core.Future<vn.zalopay.ducnm8.grpc.HelloReply> sayHello(vn.zalopay.ducnm8.grpc.HelloRequest request) {
            return io.vertx.grpc.stub.ClientCalls.oneToOne(request, delegateStub::sayHello);
        }

    }

    /**
     * <pre>
     *  The greeting service definition.
     * </pre>
     */
    public static abstract class GreeterImplBase implements io.grpc.BindableService {

        private String compression;

        /**
         * Set whether the server will try to use a compressed response.
         *
         * @param compression the compression, e.g {@code gzip}
         */
        public GreeterImplBase withCompression(String compression) {
            this.compression = compression;
            return this;
        }

        /**
         * <pre>
         *  Sends a greeting
         * </pre>
         */
        public io.vertx.core.Future<vn.zalopay.ducnm8.grpc.HelloReply> sayHello(vn.zalopay.ducnm8.grpc.HelloRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            vn.zalopay.ducnm8.grpc.GreeterGrpc.getSayHelloMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            vn.zalopay.ducnm8.grpc.HelloRequest,
                                            vn.zalopay.ducnm8.grpc.HelloReply>(
                                            this, METHODID_SAY_HELLO, compression)))
                    .build();
        }
    }

    private static final int METHODID_SAY_HELLO = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

        private final GreeterImplBase serviceImpl;
        private final int methodId;
        private final String compression;

        MethodHandlers(GreeterImplBase serviceImpl, int methodId, String compression) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
            this.compression = compression;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_SAY_HELLO:
                    io.vertx.grpc.stub.ServerCalls.oneToOne((vn.zalopay.ducnm8.grpc.HelloRequest) request,
                            (io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HelloReply>) responseObserver,
                            compression,
                            serviceImpl::sayHello);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }

}
