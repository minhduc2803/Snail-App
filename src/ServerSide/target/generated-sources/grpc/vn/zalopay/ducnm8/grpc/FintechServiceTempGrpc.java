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
    comments = "Source: fintech.proto")
public final class FintechServiceTempGrpc {

  private FintechServiceTempGrpc() {}

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

  public static final String SERVICE_NAME = "fintech.FintechServiceTemp";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.BalanceRequest,
      vn.zalopay.ducnm8.grpc.BalanceResponse> getGetBalanceMethod;

  public static io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.BalanceRequest,
      vn.zalopay.ducnm8.grpc.BalanceResponse> getGetBalanceMethod() {
    io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.BalanceRequest, vn.zalopay.ducnm8.grpc.BalanceResponse> getGetBalanceMethod;
    if ((getGetBalanceMethod = FintechServiceTempGrpc.getGetBalanceMethod) == null) {
      synchronized (FintechServiceTempGrpc.class) {
        if ((getGetBalanceMethod = FintechServiceTempGrpc.getGetBalanceMethod) == null) {
          FintechServiceTempGrpc.getGetBalanceMethod = getGetBalanceMethod = 
              io.grpc.MethodDescriptor.<vn.zalopay.ducnm8.grpc.BalanceRequest, vn.zalopay.ducnm8.grpc.BalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "fintech.FintechServiceTemp", "getBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.zalopay.ducnm8.grpc.BalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.zalopay.ducnm8.grpc.BalanceResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FintechServiceTempMethodDescriptorSupplier("getBalance"))
                  .build();
          }
        }
     }
     return getGetBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.HistoryRequest,
      vn.zalopay.ducnm8.grpc.HistoryResponse> getGetHistoryMethod;

  public static io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.HistoryRequest,
      vn.zalopay.ducnm8.grpc.HistoryResponse> getGetHistoryMethod() {
    io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.HistoryRequest, vn.zalopay.ducnm8.grpc.HistoryResponse> getGetHistoryMethod;
    if ((getGetHistoryMethod = FintechServiceTempGrpc.getGetHistoryMethod) == null) {
      synchronized (FintechServiceTempGrpc.class) {
        if ((getGetHistoryMethod = FintechServiceTempGrpc.getGetHistoryMethod) == null) {
          FintechServiceTempGrpc.getGetHistoryMethod = getGetHistoryMethod = 
              io.grpc.MethodDescriptor.<vn.zalopay.ducnm8.grpc.HistoryRequest, vn.zalopay.ducnm8.grpc.HistoryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "fintech.FintechServiceTemp", "getHistory"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.zalopay.ducnm8.grpc.HistoryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.zalopay.ducnm8.grpc.HistoryResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FintechServiceTempMethodDescriptorSupplier("getHistory"))
                  .build();
          }
        }
     }
     return getGetHistoryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.TransferRequest,
      vn.zalopay.ducnm8.grpc.TransferResponse> getTransferMethod;

  public static io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.TransferRequest,
      vn.zalopay.ducnm8.grpc.TransferResponse> getTransferMethod() {
    io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.TransferRequest, vn.zalopay.ducnm8.grpc.TransferResponse> getTransferMethod;
    if ((getTransferMethod = FintechServiceTempGrpc.getTransferMethod) == null) {
      synchronized (FintechServiceTempGrpc.class) {
        if ((getTransferMethod = FintechServiceTempGrpc.getTransferMethod) == null) {
          FintechServiceTempGrpc.getTransferMethod = getTransferMethod = 
              io.grpc.MethodDescriptor.<vn.zalopay.ducnm8.grpc.TransferRequest, vn.zalopay.ducnm8.grpc.TransferResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "fintech.FintechServiceTemp", "transfer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.zalopay.ducnm8.grpc.TransferRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.zalopay.ducnm8.grpc.TransferResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FintechServiceTempMethodDescriptorSupplier("transfer"))
                  .build();
          }
        }
     }
     return getTransferMethod;
  }

  private static volatile io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.NotificationRequest,
      vn.zalopay.ducnm8.grpc.NotificationResponse> getGetNotificationMethod;

  public static io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.NotificationRequest,
      vn.zalopay.ducnm8.grpc.NotificationResponse> getGetNotificationMethod() {
    io.grpc.MethodDescriptor<vn.zalopay.ducnm8.grpc.NotificationRequest, vn.zalopay.ducnm8.grpc.NotificationResponse> getGetNotificationMethod;
    if ((getGetNotificationMethod = FintechServiceTempGrpc.getGetNotificationMethod) == null) {
      synchronized (FintechServiceTempGrpc.class) {
        if ((getGetNotificationMethod = FintechServiceTempGrpc.getGetNotificationMethod) == null) {
          FintechServiceTempGrpc.getGetNotificationMethod = getGetNotificationMethod = 
              io.grpc.MethodDescriptor.<vn.zalopay.ducnm8.grpc.NotificationRequest, vn.zalopay.ducnm8.grpc.NotificationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "fintech.FintechServiceTemp", "getNotification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.zalopay.ducnm8.grpc.NotificationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.zalopay.ducnm8.grpc.NotificationResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FintechServiceTempMethodDescriptorSupplier("getNotification"))
                  .build();
          }
        }
     }
     return getGetNotificationMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FintechServiceTempStub newStub(io.grpc.Channel channel) {
    return new FintechServiceTempStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FintechServiceTempBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FintechServiceTempBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FintechServiceTempFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FintechServiceTempFutureStub(channel);
  }

  /**
   * Creates a new vertx stub that supports all call types for the service
   */
  public static FintechServiceTempVertxStub newVertxStub(io.grpc.Channel channel) {
    return new FintechServiceTempVertxStub(channel);
  }

  /**
   */
  public static abstract class FintechServiceTempImplBase implements io.grpc.BindableService {

    /**
     */
    public void getBalance(vn.zalopay.ducnm8.grpc.BalanceRequest request,
        io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.BalanceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBalanceMethod(), responseObserver);
    }

    /**
     */
    public void getHistory(vn.zalopay.ducnm8.grpc.HistoryRequest request,
        io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HistoryResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetHistoryMethod(), responseObserver);
    }

    /**
     */
    public void transfer(vn.zalopay.ducnm8.grpc.TransferRequest request,
        io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.TransferResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTransferMethod(), responseObserver);
    }

    /**
     */
    public void getNotification(vn.zalopay.ducnm8.grpc.NotificationRequest request,
        io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.NotificationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetNotificationMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                vn.zalopay.ducnm8.grpc.BalanceRequest,
                vn.zalopay.ducnm8.grpc.BalanceResponse>(
                  this, METHODID_GET_BALANCE)))
          .addMethod(
            getGetHistoryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                vn.zalopay.ducnm8.grpc.HistoryRequest,
                vn.zalopay.ducnm8.grpc.HistoryResponse>(
                  this, METHODID_GET_HISTORY)))
          .addMethod(
            getTransferMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                vn.zalopay.ducnm8.grpc.TransferRequest,
                vn.zalopay.ducnm8.grpc.TransferResponse>(
                  this, METHODID_TRANSFER)))
          .addMethod(
            getGetNotificationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                vn.zalopay.ducnm8.grpc.NotificationRequest,
                vn.zalopay.ducnm8.grpc.NotificationResponse>(
                  this, METHODID_GET_NOTIFICATION)))
          .build();
    }
  }

  /**
   */
  public static final class FintechServiceTempStub extends io.grpc.stub.AbstractStub<FintechServiceTempStub> {
    public FintechServiceTempStub(io.grpc.Channel channel) {
      super(channel);
    }

    public FintechServiceTempStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FintechServiceTempStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FintechServiceTempStub(channel, callOptions);
    }

    /**
     */
    public void getBalance(vn.zalopay.ducnm8.grpc.BalanceRequest request,
        io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.BalanceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getHistory(vn.zalopay.ducnm8.grpc.HistoryRequest request,
        io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HistoryResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetHistoryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void transfer(vn.zalopay.ducnm8.grpc.TransferRequest request,
        io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.TransferResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getNotification(vn.zalopay.ducnm8.grpc.NotificationRequest request,
        io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.NotificationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetNotificationMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FintechServiceTempBlockingStub extends io.grpc.stub.AbstractStub<FintechServiceTempBlockingStub> {
    public FintechServiceTempBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    public FintechServiceTempBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FintechServiceTempBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FintechServiceTempBlockingStub(channel, callOptions);
    }

    /**
     */
    public vn.zalopay.ducnm8.grpc.BalanceResponse getBalance(vn.zalopay.ducnm8.grpc.BalanceRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetBalanceMethod(), getCallOptions(), request);
    }

    /**
     */
    public vn.zalopay.ducnm8.grpc.HistoryResponse getHistory(vn.zalopay.ducnm8.grpc.HistoryRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetHistoryMethod(), getCallOptions(), request);
    }

    /**
     */
    public vn.zalopay.ducnm8.grpc.TransferResponse transfer(vn.zalopay.ducnm8.grpc.TransferRequest request) {
      return blockingUnaryCall(
          getChannel(), getTransferMethod(), getCallOptions(), request);
    }

    /**
     */
    public vn.zalopay.ducnm8.grpc.NotificationResponse getNotification(vn.zalopay.ducnm8.grpc.NotificationRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetNotificationMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FintechServiceTempFutureStub extends io.grpc.stub.AbstractStub<FintechServiceTempFutureStub> {
    public FintechServiceTempFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    public FintechServiceTempFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FintechServiceTempFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FintechServiceTempFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.zalopay.ducnm8.grpc.BalanceResponse> getBalance(
        vn.zalopay.ducnm8.grpc.BalanceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.zalopay.ducnm8.grpc.HistoryResponse> getHistory(
        vn.zalopay.ducnm8.grpc.HistoryRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetHistoryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.zalopay.ducnm8.grpc.TransferResponse> transfer(
        vn.zalopay.ducnm8.grpc.TransferRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.zalopay.ducnm8.grpc.NotificationResponse> getNotification(
        vn.zalopay.ducnm8.grpc.NotificationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetNotificationMethod(), getCallOptions()), request);
    }
  }

  /**
   */
  public static abstract class FintechServiceTempVertxImplBase implements io.grpc.BindableService {

    /**
     */
    public void getBalance(vn.zalopay.ducnm8.grpc.BalanceRequest request,
        io.vertx.core.Future<vn.zalopay.ducnm8.grpc.BalanceResponse> response) {
      asyncUnimplementedUnaryCall(getGetBalanceMethod(), FintechServiceTempGrpc.toObserver(response.completer()));
    }

    /**
     */
    public void getHistory(vn.zalopay.ducnm8.grpc.HistoryRequest request,
        io.vertx.core.Future<vn.zalopay.ducnm8.grpc.HistoryResponse> response) {
      asyncUnimplementedUnaryCall(getGetHistoryMethod(), FintechServiceTempGrpc.toObserver(response.completer()));
    }

    /**
     */
    public void transfer(vn.zalopay.ducnm8.grpc.TransferRequest request,
        io.vertx.core.Future<vn.zalopay.ducnm8.grpc.TransferResponse> response) {
      asyncUnimplementedUnaryCall(getTransferMethod(), FintechServiceTempGrpc.toObserver(response.completer()));
    }

    /**
     */
    public void getNotification(vn.zalopay.ducnm8.grpc.NotificationRequest request,
        io.vertx.core.Future<vn.zalopay.ducnm8.grpc.NotificationResponse> response) {
      asyncUnimplementedUnaryCall(getGetNotificationMethod(), FintechServiceTempGrpc.toObserver(response.completer()));
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetBalanceMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                vn.zalopay.ducnm8.grpc.BalanceRequest,
                vn.zalopay.ducnm8.grpc.BalanceResponse>(
                  this, METHODID_GET_BALANCE)))
          .addMethod(
            getGetHistoryMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                vn.zalopay.ducnm8.grpc.HistoryRequest,
                vn.zalopay.ducnm8.grpc.HistoryResponse>(
                  this, METHODID_GET_HISTORY)))
          .addMethod(
            getTransferMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                vn.zalopay.ducnm8.grpc.TransferRequest,
                vn.zalopay.ducnm8.grpc.TransferResponse>(
                  this, METHODID_TRANSFER)))
          .addMethod(
            getGetNotificationMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                vn.zalopay.ducnm8.grpc.NotificationRequest,
                vn.zalopay.ducnm8.grpc.NotificationResponse>(
                  this, METHODID_GET_NOTIFICATION)))
          .build();
    }
  }

  /**
   */
  public static final class FintechServiceTempVertxStub extends io.grpc.stub.AbstractStub<FintechServiceTempVertxStub> {
    public FintechServiceTempVertxStub(io.grpc.Channel channel) {
      super(channel);
    }

    public FintechServiceTempVertxStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FintechServiceTempVertxStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FintechServiceTempVertxStub(channel, callOptions);
    }

    /**
     */
    public void getBalance(vn.zalopay.ducnm8.grpc.BalanceRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<vn.zalopay.ducnm8.grpc.BalanceResponse>> response) {
      asyncUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, FintechServiceTempGrpc.toObserver(response));
    }

    /**
     */
    public void getHistory(vn.zalopay.ducnm8.grpc.HistoryRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<vn.zalopay.ducnm8.grpc.HistoryResponse>> response) {
      asyncUnaryCall(
          getChannel().newCall(getGetHistoryMethod(), getCallOptions()), request, FintechServiceTempGrpc.toObserver(response));
    }

    /**
     */
    public void transfer(vn.zalopay.ducnm8.grpc.TransferRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<vn.zalopay.ducnm8.grpc.TransferResponse>> response) {
      asyncUnaryCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), request, FintechServiceTempGrpc.toObserver(response));
    }

    /**
     */
    public void getNotification(vn.zalopay.ducnm8.grpc.NotificationRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<vn.zalopay.ducnm8.grpc.NotificationResponse>> response) {
      asyncUnaryCall(
          getChannel().newCall(getGetNotificationMethod(), getCallOptions()), request, FintechServiceTempGrpc.toObserver(response));
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
    private final FintechServiceTempImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FintechServiceTempImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BALANCE:
          serviceImpl.getBalance((vn.zalopay.ducnm8.grpc.BalanceRequest) request,
              (io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.BalanceResponse>) responseObserver);
          break;
        case METHODID_GET_HISTORY:
          serviceImpl.getHistory((vn.zalopay.ducnm8.grpc.HistoryRequest) request,
              (io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HistoryResponse>) responseObserver);
          break;
        case METHODID_TRANSFER:
          serviceImpl.transfer((vn.zalopay.ducnm8.grpc.TransferRequest) request,
              (io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.TransferResponse>) responseObserver);
          break;
        case METHODID_GET_NOTIFICATION:
          serviceImpl.getNotification((vn.zalopay.ducnm8.grpc.NotificationRequest) request,
              (io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.NotificationResponse>) responseObserver);
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
    private final FintechServiceTempVertxImplBase serviceImpl;
    private final int methodId;

    VertxMethodHandlers(FintechServiceTempVertxImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BALANCE:
          serviceImpl.getBalance((vn.zalopay.ducnm8.grpc.BalanceRequest) request,
              (io.vertx.core.Future<vn.zalopay.ducnm8.grpc.BalanceResponse>) io.vertx.core.Future.<vn.zalopay.ducnm8.grpc.BalanceResponse>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.BalanceResponse>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        case METHODID_GET_HISTORY:
          serviceImpl.getHistory((vn.zalopay.ducnm8.grpc.HistoryRequest) request,
              (io.vertx.core.Future<vn.zalopay.ducnm8.grpc.HistoryResponse>) io.vertx.core.Future.<vn.zalopay.ducnm8.grpc.HistoryResponse>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.HistoryResponse>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        case METHODID_TRANSFER:
          serviceImpl.transfer((vn.zalopay.ducnm8.grpc.TransferRequest) request,
              (io.vertx.core.Future<vn.zalopay.ducnm8.grpc.TransferResponse>) io.vertx.core.Future.<vn.zalopay.ducnm8.grpc.TransferResponse>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.TransferResponse>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        case METHODID_GET_NOTIFICATION:
          serviceImpl.getNotification((vn.zalopay.ducnm8.grpc.NotificationRequest) request,
              (io.vertx.core.Future<vn.zalopay.ducnm8.grpc.NotificationResponse>) io.vertx.core.Future.<vn.zalopay.ducnm8.grpc.NotificationResponse>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<vn.zalopay.ducnm8.grpc.NotificationResponse>) responseObserver).onNext(ar.result());
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

  private static abstract class FintechServiceTempBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FintechServiceTempBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return vn.zalopay.ducnm8.grpc.FinTechProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FintechServiceTemp");
    }
  }

  private static final class FintechServiceTempFileDescriptorSupplier
      extends FintechServiceTempBaseDescriptorSupplier {
    FintechServiceTempFileDescriptorSupplier() {}
  }

  private static final class FintechServiceTempMethodDescriptorSupplier
      extends FintechServiceTempBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FintechServiceTempMethodDescriptorSupplier(String methodName) {
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
      synchronized (FintechServiceTempGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FintechServiceTempFileDescriptorSupplier())
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
