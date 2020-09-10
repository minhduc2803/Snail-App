package bla.nah.example.dagger;

import bla.nah.example.cache.ChatListCache;
import bla.nah.example.cache.ConversationListCache;
import bla.nah.example.cache.RedisCache;
import bla.nah.example.cache.UserCache;
import bla.nah.example.da.ChatListDA;
import bla.nah.example.da.ConversationMemberDA;
import bla.nah.example.da.DataSourceProvider;
import bla.nah.example.da.TransactionProvider;
import bla.nah.example.da.UserDA;
import bla.nah.example.handler.ChatListHandler;
import bla.nah.example.handler.ConversationListHandler;
import bla.nah.example.handler.EchoHandler;
import bla.nah.example.handler.ExampleHandler;
import bla.nah.example.handler.HandlerFactory;
import bla.nah.example.handler.JWTAuthHandler;
import bla.nah.example.handler.LoginHandler;
import bla.nah.example.handler.RegisterHandler;
import bla.nah.example.handler.UserListHandler;
import bla.nah.example.handler.WSHandler;
import bla.nah.example.server.RestfulAPI;
import bla.nah.example.server.WebSocketServer;
import bla.nah.example.utils.AsyncHandler;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import io.vertx.core.Vertx;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import javax.annotation.Generated;
import javax.inject.Provider;

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

  private Provider<UserDA> provideUserDAProvider;

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
