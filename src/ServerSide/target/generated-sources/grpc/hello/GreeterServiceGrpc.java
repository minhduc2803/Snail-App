package hello;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: hello.proto")
public final class GreeterServiceGrpc {

  private GreeterServiceGrpc() {}

  private static <T> io.grpc.stub.StreamObserver<T> toObserver(final io.vertx.core.Handler<io.vertx.core.AsyncResult<T>> handler) {
    return new io.grpc.stub.StreamObserver<T>() {
      private volatile boolean resolved = false;
      @Override
      public void onNext(T value) {
        if (!resolved) {
          resolved = true;
          handler.handle(io.vertx.core.Future.succeededFuture(value));
        }
      }

      @Override
      public void onError(Throwable t) {
        if (!resolved) {
          resolved = true;
          handler.handle(io.vertx.core.Future.failedFuture(t));
        }
      }

      @Override
      public void onCompleted() {
        if (!resolved) {
          resolved = true;
          handler.handle(io.vertx.core.Future.succeededFuture());
        }
      }
    };
  }

  public static final String SERVICE_NAME = "helloworld.GreeterService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<hello.HelloRequest,
      hello.HelloReply> getGetBalanceMethod;

  public static io.grpc.MethodDescriptor<hello.HelloRequest,
      hello.HelloReply> getGetBalanceMethod() {
    io.grpc.MethodDescriptor<hello.HelloRequest, hello.HelloReply> getGetBalanceMethod;
    if ((getGetBalanceMethod = GreeterServiceGrpc.getGetBalanceMethod) == null) {
      synchronized (GreeterServiceGrpc.class) {
        if ((getGetBalanceMethod = GreeterServiceGrpc.getGetBalanceMethod) == null) {
          GreeterServiceGrpc.getGetBalanceMethod = getGetBalanceMethod = 
              io.grpc.MethodDescriptor.<hello.HelloRequest, hello.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.GreeterService", "getBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hello.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hello.HelloReply.getDefaultInstance()))
                  .setSchemaDescriptor(new GreeterServiceMethodDescriptorSupplier("getBalance"))
                  .build();
          }
        }
     }
     return getGetBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<hello.HelloRequest,
      hello.HelloReply> getGetHistoryMethod;

  public static io.grpc.MethodDescriptor<hello.HelloRequest,
      hello.HelloReply> getGetHistoryMethod() {
    io.grpc.MethodDescriptor<hello.HelloRequest, hello.HelloReply> getGetHistoryMethod;
    if ((getGetHistoryMethod = GreeterServiceGrpc.getGetHistoryMethod) == null) {
      synchronized (GreeterServiceGrpc.class) {
        if ((getGetHistoryMethod = GreeterServiceGrpc.getGetHistoryMethod) == null) {
          GreeterServiceGrpc.getGetHistoryMethod = getGetHistoryMethod = 
              io.grpc.MethodDescriptor.<hello.HelloRequest, hello.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.GreeterService", "getHistory"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hello.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hello.HelloReply.getDefaultInstance()))
                  .setSchemaDescriptor(new GreeterServiceMethodDescriptorSupplier("getHistory"))
                  .build();
          }
        }
     }
     return getGetHistoryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<hello.HelloRequest,
      hello.HelloReply> getTransferMethod;

  public static io.grpc.MethodDescriptor<hello.HelloRequest,
      hello.HelloReply> getTransferMethod() {
    io.grpc.MethodDescriptor<hello.HelloRequest, hello.HelloReply> getTransferMethod;
    if ((getTransferMethod = GreeterServiceGrpc.getTransferMethod) == null) {
      synchronized (GreeterServiceGrpc.class) {
        if ((getTransferMethod = GreeterServiceGrpc.getTransferMethod) == null) {
          GreeterServiceGrpc.getTransferMethod = getTransferMethod = 
              io.grpc.MethodDescriptor.<hello.HelloRequest, hello.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.GreeterService", "transfer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hello.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hello.HelloReply.getDefaultInstance()))
                  .setSchemaDescriptor(new GreeterServiceMethodDescriptorSupplier("transfer"))
                  .build();
          }
        }
     }
     return getTransferMethod;
  }

  private static volatile io.grpc.MethodDescriptor<hello.HelloRequest,
      hello.HelloReply> getGetNotificationMethod;

  public static io.grpc.MethodDescriptor<hello.HelloRequest,
      hello.HelloReply> getGetNotificationMethod() {
    io.grpc.MethodDescriptor<hello.HelloRequest, hello.HelloReply> getGetNotificationMethod;
    if ((getGetNotificationMethod = GreeterServiceGrpc.getGetNotificationMethod) == null) {
      synchronized (GreeterServiceGrpc.class) {
        if ((getGetNotificationMethod = GreeterServiceGrpc.getGetNotificationMethod) == null) {
          GreeterServiceGrpc.getGetNotificationMethod = getGetNotificationMethod = 
              io.grpc.MethodDescriptor.<hello.HelloRequest, hello.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.GreeterService", "getNotification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hello.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hello.HelloReply.getDefaultInstance()))
                  .setSchemaDescriptor(new GreeterServiceMethodDescriptorSupplier("getNotification"))
                  .build();
          }
        }
     }
     return getGetNotificationMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GreeterServiceStub newStub(io.grpc.Channel channel) {
    return new GreeterServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GreeterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GreeterServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GreeterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GreeterServiceFutureStub(channel);
  }

  /**
   * Creates a new vertx stub that supports all call types for the service
   */
  public static GreeterServiceVertxStub newVertxStub(io.grpc.Channel channel) {
    return new GreeterServiceVertxStub(channel);
  }

  /**
   */
  public static abstract class GreeterServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getBalance(hello.HelloRequest request,
        io.grpc.stub.StreamObserver<hello.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBalanceMethod(), responseObserver);
    }

    /**
     */
    public void getHistory(hello.HelloRequest request,
        io.grpc.stub.StreamObserver<hello.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetHistoryMethod(), responseObserver);
    }

    /**
     */
    public void transfer(hello.HelloRequest request,
        io.grpc.stub.StreamObserver<hello.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(getTransferMethod(), responseObserver);
    }

    /**
     */
    public void getNotification(hello.HelloRequest request,
        io.grpc.stub.StreamObserver<hello.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetNotificationMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                hello.HelloRequest,
                hello.HelloReply>(
                  this, METHODID_GET_BALANCE)))
          .addMethod(
            getGetHistoryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                hello.HelloRequest,
                hello.HelloReply>(
                  this, METHODID_GET_HISTORY)))
          .addMethod(
            getTransferMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                hello.HelloRequest,
                hello.HelloReply>(
                  this, METHODID_TRANSFER)))
          .addMethod(
            getGetNotificationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                hello.HelloRequest,
                hello.HelloReply>(
                  this, METHODID_GET_NOTIFICATION)))
          .build();
    }
  }

  /**
   */
  public static final class GreeterServiceStub extends io.grpc.stub.AbstractStub<GreeterServiceStub> {
    public GreeterServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    public GreeterServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterServiceStub(channel, callOptions);
    }

    /**
     */
    public void getBalance(hello.HelloRequest request,
        io.grpc.stub.StreamObserver<hello.HelloReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getHistory(hello.HelloRequest request,
        io.grpc.stub.StreamObserver<hello.HelloReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetHistoryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void transfer(hello.HelloRequest request,
        io.grpc.stub.StreamObserver<hello.HelloReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getNotification(hello.HelloRequest request,
        io.grpc.stub.StreamObserver<hello.HelloReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetNotificationMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GreeterServiceBlockingStub extends io.grpc.stub.AbstractStub<GreeterServiceBlockingStub> {
    public GreeterServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    public GreeterServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public hello.HelloReply getBalance(hello.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetBalanceMethod(), getCallOptions(), request);
    }

    /**
     */
    public hello.HelloReply getHistory(hello.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetHistoryMethod(), getCallOptions(), request);
    }

    /**
     */
    public hello.HelloReply transfer(hello.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getTransferMethod(), getCallOptions(), request);
    }

    /**
     */
    public hello.HelloReply getNotification(hello.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetNotificationMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GreeterServiceFutureStub extends io.grpc.stub.AbstractStub<GreeterServiceFutureStub> {
    public GreeterServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    public GreeterServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hello.HelloReply> getBalance(
        hello.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hello.HelloReply> getHistory(
        hello.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetHistoryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hello.HelloReply> transfer(
        hello.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hello.HelloReply> getNotification(
        hello.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetNotificationMethod(), getCallOptions()), request);
    }
  }

  /**
   */
  public static abstract class GreeterServiceVertxImplBase implements io.grpc.BindableService {

    /**
     */
    public void getBalance(hello.HelloRequest request,
        io.vertx.core.Future<hello.HelloReply> response) {
      asyncUnimplementedUnaryCall(getGetBalanceMethod(), GreeterServiceGrpc.toObserver(response.completer()));
    }

    /**
     */
    public void getHistory(hello.HelloRequest request,
        io.vertx.core.Future<hello.HelloReply> response) {
      asyncUnimplementedUnaryCall(getGetHistoryMethod(), GreeterServiceGrpc.toObserver(response.completer()));
    }

    /**
     */
    public void transfer(hello.HelloRequest request,
        io.vertx.core.Future<hello.HelloReply> response) {
      asyncUnimplementedUnaryCall(getTransferMethod(), GreeterServiceGrpc.toObserver(response.completer()));
    }

    /**
     */
    public void getNotification(hello.HelloRequest request,
        io.vertx.core.Future<hello.HelloReply> response) {
      asyncUnimplementedUnaryCall(getGetNotificationMethod(), GreeterServiceGrpc.toObserver(response.completer()));
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetBalanceMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                hello.HelloRequest,
                hello.HelloReply>(
                  this, METHODID_GET_BALANCE)))
          .addMethod(
            getGetHistoryMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                hello.HelloRequest,
                hello.HelloReply>(
                  this, METHODID_GET_HISTORY)))
          .addMethod(
            getTransferMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                hello.HelloRequest,
                hello.HelloReply>(
                  this, METHODID_TRANSFER)))
          .addMethod(
            getGetNotificationMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                hello.HelloRequest,
                hello.HelloReply>(
                  this, METHODID_GET_NOTIFICATION)))
          .build();
    }
  }

  /**
   */
  public static final class GreeterServiceVertxStub extends io.grpc.stub.AbstractStub<GreeterServiceVertxStub> {
    public GreeterServiceVertxStub(io.grpc.Channel channel) {
      super(channel);
    }

    public GreeterServiceVertxStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterServiceVertxStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterServiceVertxStub(channel, callOptions);
    }

    /**
     */
    public void getBalance(hello.HelloRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<hello.HelloReply>> response) {
      asyncUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, GreeterServiceGrpc.toObserver(response));
    }

    /**
     */
    public void getHistory(hello.HelloRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<hello.HelloReply>> response) {
      asyncUnaryCall(
          getChannel().newCall(getGetHistoryMethod(), getCallOptions()), request, GreeterServiceGrpc.toObserver(response));
    }

    /**
     */
    public void transfer(hello.HelloRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<hello.HelloReply>> response) {
      asyncUnaryCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), request, GreeterServiceGrpc.toObserver(response));
    }

    /**
     */
    public void getNotification(hello.HelloRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<hello.HelloReply>> response) {
      asyncUnaryCall(
          getChannel().newCall(getGetNotificationMethod(), getCallOptions()), request, GreeterServiceGrpc.toObserver(response));
    }
  }

  private static final int METHODID_GET_BALANCE = 0;
  private static final int METHODID_GET_HISTORY = 1;
  private static final int METHODID_TRANSFER = 2;
  private static final int METHODID_GET_NOTIFICATION = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GreeterServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GreeterServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BALANCE:
          serviceImpl.getBalance((hello.HelloRequest) request,
              (io.grpc.stub.StreamObserver<hello.HelloReply>) responseObserver);
          break;
        case METHODID_GET_HISTORY:
          serviceImpl.getHistory((hello.HelloRequest) request,
              (io.grpc.stub.StreamObserver<hello.HelloReply>) responseObserver);
          break;
        case METHODID_TRANSFER:
          serviceImpl.transfer((hello.HelloRequest) request,
              (io.grpc.stub.StreamObserver<hello.HelloReply>) responseObserver);
          break;
        case METHODID_GET_NOTIFICATION:
          serviceImpl.getNotification((hello.HelloRequest) request,
              (io.grpc.stub.StreamObserver<hello.HelloReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class VertxMethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GreeterServiceVertxImplBase serviceImpl;
    private final int methodId;

    VertxMethodHandlers(GreeterServiceVertxImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BALANCE:
          serviceImpl.getBalance((hello.HelloRequest) request,
              (io.vertx.core.Future<hello.HelloReply>) io.vertx.core.Future.<hello.HelloReply>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<hello.HelloReply>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        case METHODID_GET_HISTORY:
          serviceImpl.getHistory((hello.HelloRequest) request,
              (io.vertx.core.Future<hello.HelloReply>) io.vertx.core.Future.<hello.HelloReply>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<hello.HelloReply>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        case METHODID_TRANSFER:
          serviceImpl.transfer((hello.HelloRequest) request,
              (io.vertx.core.Future<hello.HelloReply>) io.vertx.core.Future.<hello.HelloReply>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<hello.HelloReply>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        case METHODID_GET_NOTIFICATION:
          serviceImpl.getNotification((hello.HelloRequest) request,
              (io.vertx.core.Future<hello.HelloReply>) io.vertx.core.Future.<hello.HelloReply>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<hello.HelloReply>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GreeterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GreeterServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return hello.HelloWorldProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GreeterService");
    }
  }

  private static final class GreeterServiceFileDescriptorSupplier
      extends GreeterServiceBaseDescriptorSupplier {
    GreeterServiceFileDescriptorSupplier() {}
  }

  private static final class GreeterServiceMethodDescriptorSupplier
      extends GreeterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GreeterServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GreeterServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GreeterServiceFileDescriptorSupplier())
              .addMethod(getGetBalanceMethod())
              .addMethod(getGetHistoryMethod())
              .addMethod(getTransferMethod())
              .addMethod(getGetNotificationMethod())
              .build();
        }
      }
    }
    return result;
  }
}
