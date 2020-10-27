package fintech;

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

  public static final String SERVICE_NAME = "fintech.FintechService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<fintech.BalanceRequest,
      fintech.BalanceResponse> getGetBalanceMethod;

  public static io.grpc.MethodDescriptor<fintech.BalanceRequest,
      fintech.BalanceResponse> getGetBalanceMethod() {
    io.grpc.MethodDescriptor<fintech.BalanceRequest, fintech.BalanceResponse> getGetBalanceMethod;
    if ((getGetBalanceMethod = FintechServiceGrpc.getGetBalanceMethod) == null) {
      synchronized (FintechServiceGrpc.class) {
        if ((getGetBalanceMethod = FintechServiceGrpc.getGetBalanceMethod) == null) {
          FintechServiceGrpc.getGetBalanceMethod = getGetBalanceMethod = 
              io.grpc.MethodDescriptor.<fintech.BalanceRequest, fintech.BalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "fintech.FintechService", "getBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fintech.BalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fintech.BalanceResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FintechServiceMethodDescriptorSupplier("getBalance"))
                  .build();
          }
        }
     }
     return getGetBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fintech.HistoryRequest,
      fintech.HistoryResponse> getGetHistoryMethod;

  public static io.grpc.MethodDescriptor<fintech.HistoryRequest,
      fintech.HistoryResponse> getGetHistoryMethod() {
    io.grpc.MethodDescriptor<fintech.HistoryRequest, fintech.HistoryResponse> getGetHistoryMethod;
    if ((getGetHistoryMethod = FintechServiceGrpc.getGetHistoryMethod) == null) {
      synchronized (FintechServiceGrpc.class) {
        if ((getGetHistoryMethod = FintechServiceGrpc.getGetHistoryMethod) == null) {
          FintechServiceGrpc.getGetHistoryMethod = getGetHistoryMethod = 
              io.grpc.MethodDescriptor.<fintech.HistoryRequest, fintech.HistoryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "fintech.FintechService", "getHistory"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fintech.HistoryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fintech.HistoryResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FintechServiceMethodDescriptorSupplier("getHistory"))
                  .build();
          }
        }
     }
     return getGetHistoryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<fintech.TransferRequest,
      fintech.TransferResponse> getTransferMethod;

  public static io.grpc.MethodDescriptor<fintech.TransferRequest,
      fintech.TransferResponse> getTransferMethod() {
    io.grpc.MethodDescriptor<fintech.TransferRequest, fintech.TransferResponse> getTransferMethod;
    if ((getTransferMethod = FintechServiceGrpc.getTransferMethod) == null) {
      synchronized (FintechServiceGrpc.class) {
        if ((getTransferMethod = FintechServiceGrpc.getTransferMethod) == null) {
          FintechServiceGrpc.getTransferMethod = getTransferMethod = 
              io.grpc.MethodDescriptor.<fintech.TransferRequest, fintech.TransferResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "fintech.FintechService", "transfer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fintech.TransferRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fintech.TransferResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FintechServiceMethodDescriptorSupplier("transfer"))
                  .build();
          }
        }
     }
     return getTransferMethod;
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
    public void getBalance(fintech.BalanceRequest request,
        io.grpc.stub.StreamObserver<fintech.BalanceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBalanceMethod(), responseObserver);
    }

    /**
     */
    public void getHistory(fintech.HistoryRequest request,
        io.grpc.stub.StreamObserver<fintech.HistoryResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetHistoryMethod(), responseObserver);
    }

    /**
     */
    public void transfer(fintech.TransferRequest request,
        io.grpc.stub.StreamObserver<fintech.TransferResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTransferMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                fintech.BalanceRequest,
                fintech.BalanceResponse>(
                  this, METHODID_GET_BALANCE)))
          .addMethod(
            getGetHistoryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                fintech.HistoryRequest,
                fintech.HistoryResponse>(
                  this, METHODID_GET_HISTORY)))
          .addMethod(
            getTransferMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                fintech.TransferRequest,
                fintech.TransferResponse>(
                  this, METHODID_TRANSFER)))
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
    public void getBalance(fintech.BalanceRequest request,
        io.grpc.stub.StreamObserver<fintech.BalanceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getHistory(fintech.HistoryRequest request,
        io.grpc.stub.StreamObserver<fintech.HistoryResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetHistoryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void transfer(fintech.TransferRequest request,
        io.grpc.stub.StreamObserver<fintech.TransferResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), request, responseObserver);
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
    public fintech.BalanceResponse getBalance(fintech.BalanceRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetBalanceMethod(), getCallOptions(), request);
    }

    /**
     */
    public fintech.HistoryResponse getHistory(fintech.HistoryRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetHistoryMethod(), getCallOptions(), request);
    }

    /**
     */
    public fintech.TransferResponse transfer(fintech.TransferRequest request) {
      return blockingUnaryCall(
          getChannel(), getTransferMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<fintech.BalanceResponse> getBalance(
        fintech.BalanceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fintech.HistoryResponse> getHistory(
        fintech.HistoryRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetHistoryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fintech.TransferResponse> transfer(
        fintech.TransferRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), request);
    }
  }

  /**
   */
  public static abstract class FintechServiceVertxImplBase implements io.grpc.BindableService {

    /**
     */
    public void getBalance(fintech.BalanceRequest request,
        io.vertx.core.Future<fintech.BalanceResponse> response) {
      asyncUnimplementedUnaryCall(getGetBalanceMethod(), FintechServiceGrpc.toObserver(response.completer()));
    }

    /**
     */
    public void getHistory(fintech.HistoryRequest request,
        io.vertx.core.Future<fintech.HistoryResponse> response) {
      asyncUnimplementedUnaryCall(getGetHistoryMethod(), FintechServiceGrpc.toObserver(response.completer()));
    }

    /**
     */
    public void transfer(fintech.TransferRequest request,
        io.vertx.core.Future<fintech.TransferResponse> response) {
      asyncUnimplementedUnaryCall(getTransferMethod(), FintechServiceGrpc.toObserver(response.completer()));
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetBalanceMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                fintech.BalanceRequest,
                fintech.BalanceResponse>(
                  this, METHODID_GET_BALANCE)))
          .addMethod(
            getGetHistoryMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                fintech.HistoryRequest,
                fintech.HistoryResponse>(
                  this, METHODID_GET_HISTORY)))
          .addMethod(
            getTransferMethod(),
            asyncUnaryCall(
              new VertxMethodHandlers<
                fintech.TransferRequest,
                fintech.TransferResponse>(
                  this, METHODID_TRANSFER)))
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
    public void getBalance(fintech.BalanceRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<fintech.BalanceResponse>> response) {
      asyncUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, FintechServiceGrpc.toObserver(response));
    }

    /**
     */
    public void getHistory(fintech.HistoryRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<fintech.HistoryResponse>> response) {
      asyncUnaryCall(
          getChannel().newCall(getGetHistoryMethod(), getCallOptions()), request, FintechServiceGrpc.toObserver(response));
    }

    /**
     */
    public void transfer(fintech.TransferRequest request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<fintech.TransferResponse>> response) {
      asyncUnaryCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), request, FintechServiceGrpc.toObserver(response));
    }
  }

  private static final int METHODID_GET_BALANCE = 0;
  private static final int METHODID_GET_HISTORY = 1;
  private static final int METHODID_TRANSFER = 2;

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
          serviceImpl.getBalance((fintech.BalanceRequest) request,
              (io.grpc.stub.StreamObserver<fintech.BalanceResponse>) responseObserver);
          break;
        case METHODID_GET_HISTORY:
          serviceImpl.getHistory((fintech.HistoryRequest) request,
              (io.grpc.stub.StreamObserver<fintech.HistoryResponse>) responseObserver);
          break;
        case METHODID_TRANSFER:
          serviceImpl.transfer((fintech.TransferRequest) request,
              (io.grpc.stub.StreamObserver<fintech.TransferResponse>) responseObserver);
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
          serviceImpl.getBalance((fintech.BalanceRequest) request,
              (io.vertx.core.Future<fintech.BalanceResponse>) io.vertx.core.Future.<fintech.BalanceResponse>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<fintech.BalanceResponse>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        case METHODID_GET_HISTORY:
          serviceImpl.getHistory((fintech.HistoryRequest) request,
              (io.vertx.core.Future<fintech.HistoryResponse>) io.vertx.core.Future.<fintech.HistoryResponse>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<fintech.HistoryResponse>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        case METHODID_TRANSFER:
          serviceImpl.transfer((fintech.TransferRequest) request,
              (io.vertx.core.Future<fintech.TransferResponse>) io.vertx.core.Future.<fintech.TransferResponse>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<fintech.TransferResponse>) responseObserver).onNext(ar.result());
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
      return fintech.FinTechProto.getDescriptor();
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
              .build();
        }
      }
    }
    return result;
  }
}
