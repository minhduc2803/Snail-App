package vn.zalopay.ducnm8.dagger;

import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import io.vertx.core.Vertx;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.cache.ChatListCache;
import vn.zalopay.ducnm8.cache.ConversationListCache;
import vn.zalopay.ducnm8.cache.RedisCache;
import vn.zalopay.ducnm8.cache.UserCache;
import vn.zalopay.ducnm8.da.DataSourceProvider;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.da.interact.AccountDA;
import vn.zalopay.ducnm8.da.interact.ChatListDA;
import vn.zalopay.ducnm8.da.interact.ConversationMemberDA;
import vn.zalopay.ducnm8.grpc.FintechServiceImpl;
import vn.zalopay.ducnm8.handler.ChatListHandler;
import vn.zalopay.ducnm8.handler.ConversationListHandler;
import vn.zalopay.ducnm8.handler.EchoHandler;
import vn.zalopay.ducnm8.handler.ExampleHandler;
import vn.zalopay.ducnm8.handler.HandlerFactory;
import vn.zalopay.ducnm8.handler.JWTAuthHandler;
import vn.zalopay.ducnm8.handler.LoginHandler;
import vn.zalopay.ducnm8.handler.RegisterHandler;
import vn.zalopay.ducnm8.handler.UserListHandler;
import vn.zalopay.ducnm8.handler.WSHandler;
import vn.zalopay.ducnm8.handler.grpc.InterceptorHandler;
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

  private Provider<AccountDA> provideUserDAProvider;

  private Provider<TransactionProvider> provideTransactionProvider;

  private Provider<RedisCache> provideRedisCacheProvider;

  private Provider<UserCache> provideUserCacheProvider;

  private Provider<ExampleHandler> provideExampleHandlerProvider;

  private Provider<JWTAuthOptions> provideJWTAuthOptionsProvider;

  private Provider<JWTAuth> provideAuthProvider;

  private Provider<LoginHandler> provideLoginHandlerProvider;

  private Provider<RegisterHandler> provideRegisterHandlerProvider;

  private Provider<ConversationMemberDA> provideConversationMemberDAProvider;

  private Provider<ConversationListCache> provideConversationListCacheProvider;

  private Provider<ConversationListHandler> provideConversationListHandlerProvider;

  private Provider<ChatListDA> provideChatListDAProvider;

  private Provider<ChatListCache> provideChatListCacheProvider;

  private Provider<ChatListHandler> provideChatListHandlerProvider;

  private Provider<JWTAuthHandler> provideJWTAuthHandlerProvider;

  private Provider<UserListHandler> provideUserListHandlerProvider;

  private Provider<HandlerFactory> provideHandlerProvider;

  private Provider<RestfulAPI> provideRestfulAPIProvider;

  private Provider<WSHandler> provideWSHandlerProvider;

  private Provider<WebSocketServer> provideWebSocketServerProvider;

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
    this.provideUserDAProvider = DoubleCheck.provider(ServiceModule_ProvideUserDAFactory.create(serviceModuleParam, provideDataSourceProvider, ProvideAsyncHandlerProvider));
    this.provideTransactionProvider = DoubleCheck.provider(ServiceModule_ProvideTransactionProviderFactory.create(serviceModuleParam, provideDataSourceProvider));
    this.provideRedisCacheProvider = DoubleCheck.provider(ServiceModule_ProvideRedisCacheFactory.create(serviceModuleParam));
    this.provideUserCacheProvider = DoubleCheck.provider(ServiceModule_ProvideUserCacheFactory.create(serviceModuleParam, provideRedisCacheProvider, ProvideAsyncHandlerProvider));
    this.provideExampleHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideExampleHandlerFactory.create(serviceModuleParam, provideUserDAProvider, provideTransactionProvider, provideUserCacheProvider));
    this.provideJWTAuthOptionsProvider = DoubleCheck.provider(ServiceModule_ProvideJWTAuthOptionsFactory.create(serviceModuleParam));
    this.provideAuthProvider = DoubleCheck.provider(ServiceModule_ProvideAuthProviderFactory.create(serviceModuleParam, provideVertxProvider, provideJWTAuthOptionsProvider));
    this.provideLoginHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideLoginHandlerFactory.create(serviceModuleParam, provideUserDAProvider, provideTransactionProvider, provideUserCacheProvider, provideAuthProvider));
    this.provideRegisterHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideRegisterHandlerFactory.create(serviceModuleParam, provideUserDAProvider, provideTransactionProvider, provideUserCacheProvider, provideAuthProvider));
    this.provideConversationMemberDAProvider = DoubleCheck.provider(ServiceModule_ProvideConversationMemberDAFactory.create(serviceModuleParam, provideDataSourceProvider, ProvideAsyncHandlerProvider));
    this.provideConversationListCacheProvider = DoubleCheck.provider(ServiceModule_ProvideConversationListCacheFactory.create(serviceModuleParam, provideRedisCacheProvider, ProvideAsyncHandlerProvider));
    this.provideConversationListHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideConversationListHandlerFactory.create(serviceModuleParam, provideConversationMemberDAProvider, provideTransactionProvider, provideConversationListCacheProvider));
    this.provideChatListDAProvider = DoubleCheck.provider(ServiceModule_ProvideChatListDAFactory.create(serviceModuleParam, provideDataSourceProvider, ProvideAsyncHandlerProvider));
    this.provideChatListCacheProvider = DoubleCheck.provider(ServiceModule_ProvideChatListCacheFactory.create(serviceModuleParam, provideRedisCacheProvider, ProvideAsyncHandlerProvider));
    this.provideChatListHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideChatListHandlerFactory.create(serviceModuleParam, provideChatListDAProvider, provideTransactionProvider, provideChatListCacheProvider, provideAuthProvider));
    this.provideJWTAuthHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideJWTAuthHandlerFactory.create(serviceModuleParam, provideAuthProvider));
    this.provideUserListHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideUserListHandlerFactory.create(serviceModuleParam, provideUserDAProvider, provideTransactionProvider, provideUserCacheProvider, provideAuthProvider));
    this.provideHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideHandlerFactory.create(serviceModuleParam, provideEchoHandlerProvider, provideExampleHandlerProvider, provideLoginHandlerProvider, provideRegisterHandlerProvider, provideConversationListHandlerProvider, provideChatListHandlerProvider, provideJWTAuthHandlerProvider, provideUserListHandlerProvider));
    this.provideRestfulAPIProvider = DoubleCheck.provider(ServiceModule_ProvideRestfulAPIFactory.create(serviceModuleParam, provideHandlerProvider, provideVertxProvider, provideAuthProvider));
    this.provideWSHandlerProvider = DoubleCheck.provider(ServiceModule_ProvideWSHandlerFactory.create(serviceModuleParam, provideChatListDAProvider, provideChatListCacheProvider, provideTransactionProvider));
    this.provideWebSocketServerProvider = DoubleCheck.provider(ServiceModule_ProvideWebSocketServerFactory.create(serviceModuleParam, provideWSHandlerProvider, provideVertxProvider, provideAuthProvider));
    this.provideFintechServiceImplProvider = DoubleCheck.provider(ServiceModule_ProvideFintechServiceImplFactory.create(serviceModuleParam));
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
