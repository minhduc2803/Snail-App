package vn.zalopay.ducnm8.dagger;

import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import io.vertx.core.Vertx;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.cache.ChatListCache;
import vn.zalopay.ducnm8.cache.RedisCache;
import vn.zalopay.ducnm8.cache.UserCache;
import vn.zalopay.ducnm8.da.DataSourceProvider;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.da.interact.AccountDA;
import vn.zalopay.ducnm8.da.interact.ChatListDA;
import vn.zalopay.ducnm8.da.interact.NotificationDA;
import vn.zalopay.ducnm8.da.interact.TransferDA;
import vn.zalopay.ducnm8.da.interact.TransferHistoryDA;
import vn.zalopay.ducnm8.grpc.FintechServiceImpl;
import vn.zalopay.ducnm8.handler.ChatListHandler;
import vn.zalopay.ducnm8.handler.HandlerFactory;
import vn.zalopay.ducnm8.handler.JWTAuthHandler;
import vn.zalopay.ducnm8.handler.LoginHandler;
import vn.zalopay.ducnm8.handler.RegisterHandler;
import vn.zalopay.ducnm8.handler.UserListHandler;
import vn.zalopay.ducnm8.handler.WSHandler;
import vn.zalopay.ducnm8.handler.grpc.GetBalanceHandler;
import vn.zalopay.ducnm8.handler.grpc.GetHistoryHandler;
import vn.zalopay.ducnm8.handler.grpc.GetNotificationHandler;
import vn.zalopay.ducnm8.handler.grpc.InterceptorHandler;
import vn.zalopay.ducnm8.handler.grpc.TransferHandler;
import vn.zalopay.ducnm8.server.GRPCServer;
import vn.zalopay.ducnm8.server.RestfulAPI;
import vn.zalopay.ducnm8.server.WebSocketServer;
import vn.zalopay.ducnm8.utils.AsyncHandler;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerServiceComponent implements ServiceComponent {
  private Provider<EchoHandler> provideEchoHandlerProvider;

  private Provider<Vertx> provideVertxProvider;

  private Provider<DataSourceProvider> provideDataSourceProvider;

  private Provider<AsyncHandler> ProvideAsyncHandlerProvider;

  private Provider<AccountDA> provideAccountDAProvider;

  private Provider<TransactionProvider> provideTransactionProvider;

  private Provider<RedisCache> provideRedisCacheProvider;

  private Provider<UserCache> provideUserCacheProvider;

  private Provider<ExampleHandler> provideExampleHandlerProvider;

  private Provider<LoginHandler> provideLoginHandlerProvider;

  private Provider<RegisterHandler> provideRegisterHandlerProvider;

  private Provider<ChatListDA> provideChatListDAProvider;

  private Provider<ChatListCache> provideChatListCacheProvider;

  private Provider<ChatListHandler> provideChatListHandlerProvider;

  private Provider<JWTAuthHandler> provideJWTAuthHandlerProvider;

  private Provider<UserListHandler> provideUserListHandlerProvider;

  private Provider<HandlerFactory> provideHandlerProvider;

  private Provider<RestfulAPI> provideRestfulAPIProvider;

  private Provider<WSHandler> provideWSHandlerProvider;

  private Provider<WebSocketServer> provideWebSocketServerProvider;

  private Provider<GetBalanceHandler> provideGetBalanceHandlerProvider;

  private Provider<TransferHistoryDA> provideTransferHistoryDAProvider;

  private Provider<GetHistoryHandler> provideGetHistoryHandlerProvider;

  private Provider<TransferDA> provideTransferDAProvider;

  private Provider<NotificationDA> provideNotificationDAProvider;

  private Provider<TransferHandler> provideTransferHandlerProvider;

  private Provider<GetNotificationHandler> provideGetNotificationHandlerProvider;

  private Provider<FintechServiceImpl> provideFintechServiceImplProvider;

  private Provider<InterceptorHandler> provideInterceptorHandlerProvider;

  private Provider<GRPCServer> provideGRPCServerProvider;

  private DaggerServiceComponent(ServiceModule serviceModuleParam) {

    initialize(serviceModuleParam);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final ServiceModule serviceModuleParam) {
    this.provideEchoHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideEchoHandlerFactory.create(serviceModuleParam));
    this.provideVertxProvider = DoubleCheck.provider(ServiceModule_ProvideVertxFactory.create(serviceModuleParam));
    this.provideDataSourceProvider = DoubleCheck.provider(ServiceModule_ProvideDataSourceProviderFactory.create(serviceModuleParam, provideVertxProvider));
    this.ProvideAsyncHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideAsyncHandlerFactory.create(serviceModuleParam, provideVertxProvider));
    this.provideAccountDAProvider = DoubleCheck.provider(ServiceModule_ProvideAccountDAFactory.create(serviceModuleParam, provideDataSourceProvider, ProvideAsyncHandlerProvider));
    this.provideTransactionProvider = DoubleCheck.provider(ServiceModule_ProvideTransactionProviderFactory.create(serviceModuleParam, provideDataSourceProvider));
    this.provideRedisCacheProvider = DoubleCheck.provider(ServiceModule_ProvideRedisCacheFactory.create(serviceModuleParam));
    this.provideUserCacheProvider = DoubleCheck.provider(ServiceModule_ProvideUserCacheFactory.create(serviceModuleParam, provideRedisCacheProvider, ProvideAsyncHandlerProvider));
    this.provideExampleHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideExampleHandlerFactory.create(serviceModuleParam, provideAccountDAProvider, provideTransactionProvider, provideUserCacheProvider));
    this.provideLoginHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideLoginHandlerFactory.create(serviceModuleParam, provideAccountDAProvider, provideTransactionProvider, provideUserCacheProvider));
    this.provideRegisterHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideRegisterHandlerFactory.create(serviceModuleParam, provideAccountDAProvider, provideTransactionProvider, provideUserCacheProvider));
    this.provideChatListDAProvider = DoubleCheck.provider(ServiceModule_ProvideChatListDAFactory.create(serviceModuleParam, provideDataSourceProvider, ProvideAsyncHandlerProvider));
    this.provideChatListCacheProvider = DoubleCheck.provider(ServiceModule_ProvideChatListCacheFactory.create(serviceModuleParam, provideRedisCacheProvider, ProvideAsyncHandlerProvider));
    this.provideChatListHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideChatListHandlerFactory.create(serviceModuleParam, provideChatListDAProvider, provideTransactionProvider, provideChatListCacheProvider));
    this.provideJWTAuthHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideJWTAuthHandlerFactory.create(serviceModuleParam));
    this.provideUserListHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideUserListHandlerFactory.create(serviceModuleParam, provideAccountDAProvider, provideTransactionProvider, provideUserCacheProvider));
    this.provideHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideHandlerFactory.create(serviceModuleParam, provideEchoHandlerProvider, provideExampleHandlerProvider, provideLoginHandlerProvider, provideRegisterHandlerProvider, provideChatListHandlerProvider, provideJWTAuthHandlerProvider, provideUserListHandlerProvider));
    this.provideRestfulAPIProvider = DoubleCheck.provider(ServiceModule_ProvideRestfulAPIFactory.create(serviceModuleParam, provideHandlerProvider, provideVertxProvider));
    this.provideWSHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideWSHandlerFactory.create(serviceModuleParam, provideChatListDAProvider, provideChatListCacheProvider, provideTransactionProvider));
    this.provideWebSocketServerProvider = DoubleCheck.provider(ServiceModule_ProvideWebSocketServerFactory.create(serviceModuleParam, provideWSHandlerProvider, provideVertxProvider));
    this.provideGetBalanceHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideGetBalanceHandlerFactory.create(serviceModuleParam, provideAccountDAProvider));
    this.provideTransferHistoryDAProvider = DoubleCheck.provider(ServiceModule_ProvideTransferHistoryDAFactory.create(serviceModuleParam, provideDataSourceProvider, ProvideAsyncHandlerProvider));
    this.provideGetHistoryHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideGetHistoryHandlerFactory.create(serviceModuleParam, provideTransferHistoryDAProvider));
    this.provideTransferDAProvider = DoubleCheck.provider(ServiceModule_ProvideTransferDAFactory.create(serviceModuleParam, provideDataSourceProvider, ProvideAsyncHandlerProvider));
    this.provideNotificationDAProvider = DoubleCheck.provider(ServiceModule_ProvideNotificationDAFactory.create(serviceModuleParam, provideDataSourceProvider, ProvideAsyncHandlerProvider));
    this.provideTransferHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideTransferHandlerFactory.create(serviceModuleParam, provideTransferDAProvider, provideAccountDAProvider, provideTransferHistoryDAProvider, provideNotificationDAProvider, provideTransactionProvider, provideWSHandlerProvider));
    this.provideGetNotificationHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideGetNotificationHandlerFactory.create(serviceModuleParam));
    this.provideFintechServiceImplProvider = DoubleCheck.provider(ServiceModule_ProvideFintechServiceImplFactory.create(serviceModuleParam, provideGetBalanceHandlerProvider, provideGetHistoryHandlerProvider, provideTransferHandlerProvider, provideGetNotificationHandlerProvider));
    this.provideInterceptorHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideInterceptorHandlerFactory.create(serviceModuleParam));
    this.provideGRPCServerProvider = DoubleCheck.provider(ServiceModule_ProvideGRPCServerFactory.create(serviceModuleParam, provideVertxProvider, provideFintechServiceImplProvider, provideInterceptorHandlerProvider));
  }

  @Override
  public RestfulAPI getRestfulAPI() {
    return provideRestfulAPIProvider.get();}

  @Override
  public WebSocketServer getWebSocketServer() {
    return provideWebSocketServerProvider.get();}

  @Override
  public Vertx getVertx() {
    return provideVertxProvider.get();}

  @Override
  public GRPCServer getGRPCServer() {
    return provideGRPCServerProvider.get();}

  public static final class Builder {
    private ServiceModule serviceModule;

    private Builder() {
    }

    public Builder serviceModule(ServiceModule serviceModule) {
      this.serviceModule = Preconditions.checkNotNull(serviceModule);
      return this;
    }

    public ServiceComponent build() {
      Preconditions.checkBuilderRequirement(serviceModule, ServiceModule.class);
      return new DaggerServiceComponent(serviceModule);
    }
  }
}
