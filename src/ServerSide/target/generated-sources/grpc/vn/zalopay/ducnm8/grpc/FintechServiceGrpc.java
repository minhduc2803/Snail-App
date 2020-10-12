package vn.zalopay.ducnm8.grpc;

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
public final class FintechServiceGrpc {

  private FintechServiceGrpc() {}

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

  public static final String SERVICE_NAME = "helloworld.FintechService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.HelloRequest,
      vn.zalopay.ducnm8.grpc.HelloReply> getGetBalanceMethod;

  public static io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.HelloRequest,
      vn.zalopay.ducnm8.grpc.HelloReply> getGetBalanceMethod() {
    io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.HelloRequest, vn.zalopay.ducnm8.grpc.HelloReply> getGetBalanceMethod;
    if ((getGetBalanceMethod = FintechServiceGrpc.getGetBalanceMethod) == null) {
      synchronized (FintechServiceGrpc.class) {
        if ((getGetBalanceMethod = FintechServiceGrpc.getGetBalanceMethod) == null) {
          FintechServiceGrpc.getGetBalanceMethod = getGetBalanceMethod = 
              io.grpc.MethodDescriptor.<vn.zalopay.ducnm8.grpc.HelloRequest, vn.zalopay.ducnm8.grpc.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.FintechService", "getBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.zalopay.ducnm8.grpc.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.zalopay.ducnm8.grpc.HelloReply.getDefaultInstance()))
                  .setSchemaDescriptor(new FintechServiceMethodDescriptorSupplier("getBalance"))
                  .build();
          }
        }
     }
     return getGetBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.HelloRequest,
      vn.zalopay.ducnm8.grpc.HelloReply> getGetHistoryMethod;

  public static io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.HelloRequest,
      vn.zalopay.ducnm8.grpc.HelloReply> getGetHistoryMethod() {
    io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.HelloRequest, vn.zalopay.ducnm8.grpc.HelloReply> getGetHistoryMethod;
    if ((getGetHistoryMethod = FintechServiceGrpc.getGetHistoryMethod) == null) {
      synchronized (FintechServiceGrpc.class) {
        if ((getGetHistoryMethod = FintechServiceGrpc.getGetHistoryMethod) == null) {
          FintechServiceGrpc.getGetHistoryMethod = getGetHistoryMethod = 
              io.grpc.MethodDescriptor.<vn.zalopay.ducnm8.grpc.HelloRequest, vn.zalopay.ducnm8.grpc.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.FintechService", "getHistory"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.zalopay.ducnm8.grpc.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.zalopay.ducnm8.grpc.HelloReply.getDefaultInstance()))
                  .setSchemaDescriptor(new FintechServiceMethodDescriptorSupplier("getHistory"))
                  .build();
          }
        }
     }
     return getGetHistoryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.HelloRequest,
      vn.zalopay.ducnm8.grpc.HelloReply> getTransferMethod;

  public static io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.HelloRequest,
      vn.zalopay.ducnm8.grpc.HelloReply> getTransferMethod() {
    io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.HelloRequest, vn.zalopay.ducnm8.grpc.HelloReply> getTransferMethod;
    if ((getTransferMethod = FintechServiceGrpc.getTransferMethod) == null) {
      synchronized (FintechServiceGrpc.class) {
        if ((getTransferMethod = FintechServiceGrpc.getTransferMethod) == null) {
          FintechServiceGrpc.getTransferMethod = getTransferMethod = 
              io.grpc.MethodDescriptor.<vn.zalopay.ducnm8.grpc.HelloRequest, vn.zalopay.ducnm8.grpc.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.FintechService", "transfer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.zalopay.ducnm8.grpc.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.zalopay.ducnm8.grpc.HelloReply.getDefaultInstance()))
                  .setSchemaDescriptor(new FintechServiceMethodDescriptorSupplier("transfer"))
                  .build();
          }
        }
     }
     return getTransferMethod;
  }

  private static volatile io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.HelloRequest,
      vn.zalopay.ducnm8.grpc.HelloReply> getGetNotificationMethod;

  public static io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.HelloRequest,
      vn.zalopay.ducnm8.grpc.HelloReply> getGetNotificationMethod() {
    io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.HelloRequest, vn.zalopay.ducnm8.grpc.HelloReply> getGetNotificationMethod;
    if ((getGetNotificationMethod = FintechServiceGrpc.getGetNotificationMethod) == null) {
      synchronized (FintechServiceGrpc.class) {
        if ((getGetNotificationMethod = FintechServiceGrpc.getGetNotificationMethod) == null) {
          FintechServiceGrpc.getGetNotificationMethod = getGetNotificationMethod = 
              io.grpc.MethodDescriptor.<vn.zalopay.ducnm8.grpc.HelloRequest, vn.zalopay.ducnm8.grpc.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.FintechService", "getNotification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.zalopay.ducnm8.grpc.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.zalopay.ducnm8.grpc.HelloReply.getDefaultInstance()))
                  .setSchemaDescriptor(new FintechServiceMethodDescriptorSupplier("getNotification"))
                  .build();
          }
        }
     }
     return getGetNotificationMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FintechServiceStub newStub(io.grpc.Channel channel) {
    return new FintechServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FintechServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FintechServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FintechServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FintechServiceFutureStub(channel);
  }

  /**
   * Creates a new vertx stub that supports all call types for the service
   */
  public static FintechServiceVertxStub newVertxStub(io.grpc.Channel channel) {
    return new FintechServiceVertxStub(channel);
  }

  /**
   */
  public static abstract class FintechServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getBalance(vn.zalopay.ducnm8.grpc.HelloRequest request,
        io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBalanceMethod(), responseObserver);
    }

    /**
     */
    public void getHistory(vn.zalopay.ducnm8.grpc.HelloRequest request,
        io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetHistoryMethod(), responseObserver);
    }

    /**
     */
    public void transfer(vn.zalopay.ducnm8.grpc.HelloRequest request,
        io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(getTransferMethod(), responseObserver);
    }

    /**
     */
    public void getNotification(vn.zalopay.ducnm8.grpc.HelloRequest request,
        io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetNotificationMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                vn.zalopay.ducnm8.grpc.HelloRequest,
                vn.zalopay.ducnm8.grpc.HelloReply>(
                  this, METHODID_GET_BALANCE)))
          .addMethod(
            getGetHistoryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                vn.zalopay.ducnm8.grpc.HelloRequest,
                vn.zalopay.ducnm8.grpc.HelloReply>(
                  this, METHODID_GET_HISTORY)))
          .addMethod(
            getTransferMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                vn.zalopay.ducnm8.grpc.HelloRequest,
                vn.zalopay.ducnm8.grpc.HelloReply>(
                  this, METHODID_TRANSFER)))
          .addMethod(
            getGetNotificationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                vn.zalopay.ducnm8.grpc.HelloRequest,
                vn.zalopay.ducnm8.grpc.HelloReply>(
                  this, METHODID_GET_NOTIFICATION)))
          .build();
    }
  }

  /**
   */
  public static final class FintechServiceStub extends io.grpc.stub.AbstractStub<FintechServiceStub> {
    public FintechServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    public FintechServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FintechServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FintechServiceStub(channel, callOptions);
    }

    /**
     */
    public void getBalance(vn.zalopay.ducnm8.grpc.HelloRequest request,
        io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HelloReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getHistory(vn.zalopay.ducnm8.grpc.HelloRequest request,
        io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HelloReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetHistoryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void transfer(vn.zalopay.ducnm8.grpc.HelloRequest request,
        io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HelloReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getNotification(vn.zalopay.ducnm8.grpc.HelloRequest request,
        io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HelloReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetNotificationMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FintechServiceBlockingStub extends io.grpc.stub.AbstractStub<FintechServiceBlockingStub> {
    public FintechServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    public FintechServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FintechServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FintechServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public vn.zalopay.ducnm8.grpc.HelloReply getBalance(vn.zalopay.ducnm8.grpc.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetBalanceMethod(), getCallOptions(), request);
    }

    /**
     */
    public vn.zalopay.ducnm8.grpc.HelloReply getHistory(vn.zalopay.ducnm8.grpc.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetHistoryMethod(), getCallOptions(), request);
    }

    /**
     */
    public vn.zalopay.ducnm8.grpc.HelloReply transfer(vn.zalopay.ducnm8.grpc.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getTransferMethod(), getCallOptions(), request);
    }

    /**
     */
    public vn.zalopay.ducnm8.grpc.HelloReply getNotification(vn.zalopay.ducnm8.grpc.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetNotificationMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FintechServiceFutureStub extends io.grpc.stub.AbstractStub<FintechServiceFutureStub> {
    public FintechServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    public FintechServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FintechServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FintechServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.zalopay.ducnm8.grpc.HelloReply> getBalance(
        vn.zalopay.ducnm8.grpc.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.zalopay.ducnm8.grpc.HelloReply> getHistory(
        vn.zalopay.ducnm8.grpc.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetHistoryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.zalopay.ducnm8.grpc.HelloReply> transfer(
        vn.zalopay.ducnm8.grpc.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.zalopay.ducnm8.grpc.HelloReply> getNotification(
        vn.zalopay.ducnm8.grpc.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetNotificationMethod(), getCallOptions()), request);
    }
  }

  /**
   */
  public static abstract class FintechServiceVertxImplBase implements io.grpc.BindableService {

    /**
     */
    public void getBalance(vn.zalopay.ducnm8.grpc.HelloRequest request,
        io.vertx.core.Future<vn.zalopay.ducnm8.grpc.HelloReply> response) {
      asyncUnimplementedUnaryCall(getGetBalanceMethod(), FintechServiceGrpc.toObserver(response.completer()));
    }

    /**
     */
    public void getHistory(vn.zalopay.ducnm8.grpc.HelloRequest request,
        io.vertx.core.Future<vn.zalopay.ducnm8.grpc.HelloReply> response) {
      asyncUnimplementedUnaryCall(getGetHistoryMethod(), FintechServiceGrpc.toObserver(response.completer()));
    }

    /**
     */
    public void transfer(vn.zalopay.ducnm8.grpc.HelloRequest request,
        io.vertx.core.Future<vn.zalopay.ducnm8.grpc.HelloReply> response) {
      asyncUnimplementedUnaryCall(getTransferMethod(), FintechServiceGrpc.toObserver(response.completer()));
    }

    /**
     */
    public void getNotification(vn.zalopay.ducnm8.grpc.HelloRequest request,
        io.vertx.core.Future<vn.zalopay.ducnm8.grpc.HelloReply> response) {
      asyncUnimplementedUnaryCall(getGetNotificationMethod(), FintechServiceGrpc.toObserver(response.completer()));
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetBalanceMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                vn.zalopay.ducnm8.grpc.HelloRequest,
                vn.zalopay.ducnm8.grpc.HelloReply>(
                  this, METHODID_GET_BALANCE)))
          .addMethod(
            getGetHistoryMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                vn.zalopay.ducnm8.grpc.HelloRequest,
                vn.zalopay.ducnm8.grpc.HelloReply>(
                  this, METHODID_GET_HISTORY)))
          .addMethod(
            getTransferMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                vn.zalopay.ducnm8.grpc.HelloRequest,
                vn.zalopay.ducnm8.grpc.HelloReply>(
                  this, METHODID_TRANSFER)))
          .addMethod(
            getGetNotificationMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                vn.zalopay.ducnm8.grpc.HelloRequest,
                vn.zalopay.ducnm8.grpc.HelloReply>(
                  this, METHODID_GET_NOTIFICATION)))
          .build();
    }
  }

  /**
   */
  public static final class FintechServiceVertxStub extends io.grpc.stub.AbstractStub<FintechServiceVertxStub> {
    public FintechServiceVertxStub(io.grpc.Channel channel) {
      super(channel);
    }

    public FintechServiceVertxStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FintechServiceVertxStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FintechServiceVertxStub(channel, callOptions);
    }

    /**
     */
    public void getBalance(vn.zalopay.ducnm8.grpc.HelloRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<vn.zalopay.ducnm8.grpc.HelloReply>> response) {
      asyncUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, FintechServiceGrpc.toObserver(response));
    }

    /**
     */
    public void getHistory(vn.zalopay.ducnm8.grpc.HelloRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<vn.zalopay.ducnm8.grpc.HelloReply>> response) {
      asyncUnaryCall(
          getChannel().newCall(getGetHistoryMethod(), getCallOptions()), request, FintechServiceGrpc.toObserver(response));
    }

    /**
     */
    public void transfer(vn.zalopay.ducnm8.grpc.HelloRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<vn.zalopay.ducnm8.grpc.HelloReply>> response) {
      asyncUnaryCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), request, FintechServiceGrpc.toObserver(response));
    }

    /**
     */
    public void getNotification(vn.zalopay.ducnm8.grpc.HelloRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<vn.zalopay.ducnm8.grpc.HelloReply>> response) {
      asyncUnaryCall(
          getChannel().newCall(getGetNotificationMethod(), getCallOptions()), request, FintechServiceGrpc.toObserver(response));
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
    private final FintechServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FintechServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BALANCE:
          serviceImpl.getBalance((vn.zalopay.ducnm8.grpc.HelloRequest) request,
              (io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HelloReply>) responseObserver);
          break;
        case METHODID_GET_HISTORY:
          serviceImpl.getHistory((vn.zalopay.ducnm8.grpc.HelloRequest) request,
              (io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HelloReply>) responseObserver);
          break;
        case METHODID_TRANSFER:
          serviceImpl.transfer((vn.zalopay.ducnm8.grpc.HelloRequest) request,
              (io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HelloReply>) responseObserver);
          break;
        case METHODID_GET_NOTIFICATION:
          serviceImpl.getNotification((vn.zalopay.ducnm8.grpc.HelloRequest) request,
              (io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HelloReply>) responseObserver);
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
    private final FintechServiceVertxImplBase serviceImpl;
    private final int methodId;

    VertxMethodHandlers(FintechServiceVertxImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BALANCE:
          serviceImpl.getBalance((vn.zalopay.ducnm8.grpc.HelloRequest) request,
              (io.vertx.core.Future<vn.zalopay.ducnm8.grpc.HelloReply>) io.vertx.core.Future.<vn.zalopay.ducnm8.grpc.HelloReply>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HelloReply>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        case METHODID_GET_HISTORY:
          serviceImpl.getHistory((vn.zalopay.ducnm8.grpc.HelloRequest) request,
              (io.vertx.core.Future<vn.zalopay.ducnm8.grpc.HelloReply>) io.vertx.core.Future.<vn.zalopay.ducnm8.grpc.HelloReply>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HelloReply>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        case METHODID_TRANSFER:
          serviceImpl.transfer((vn.zalopay.ducnm8.grpc.HelloRequest) request,
              (io.vertx.core.Future<vn.zalopay.ducnm8.grpc.HelloReply>) io.vertx.core.Future.<vn.zalopay.ducnm8.grpc.HelloReply>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HelloReply>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        case METHODID_GET_NOTIFICATION:
          serviceImpl.getNotification((vn.zalopay.ducnm8.grpc.HelloRequest) request,
              (io.vertx.core.Future<vn.zalopay.ducnm8.grpc.HelloReply>) io.vertx.core.Future.<vn.zalopay.ducnm8.grpc.HelloReply>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HelloReply>) responseObserver).onNext(ar.result());
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

  private static abstract class FintechServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FintechServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return vn.zalopay.ducnm8.grpc.HelloWorldProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FintechService");
    }
  }

  private static final class FintechServiceFileDescriptorSupplier
      extends FintechServiceBaseDescriptorSupplier {
    FintechServiceFileDescriptorSupplier() {}
  }

  private static final class FintechServiceMethodDescriptorSupplier
      extends FintechServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FintechServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (FintechServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FintechServiceFileDescriptorSupplier())
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
