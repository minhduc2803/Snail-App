package vn.zalopay.ducnm8.dagger;

import vn.zalopay.ducnm8.cache.*;
import vn.zalopay.ducnm8.config.ServiceConfig;
import vn.zalopay.ducnm8.da.*;
import vn.zalopay.ducnm8.handler.*;
import vn.zalopay.ducnm8.server.GRPCServer;
import vn.zalopay.ducnm8.server.RestfulAPI;
import vn.zalopay.ducnm8.server.WebSocketServer;
import vn.zalopay.ducnm8.utils.AsyncHandler;
import vn.zalopay.ducnm8.utils.Tracker;
import dagger.Module;
import dagger.Provides;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.micrometer.MicrometerMetricsOptions;
import io.vertx.micrometer.VertxPrometheusOptions;
import lombok.Builder;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Module
@Builder
public class ServiceModule {
  ServiceConfig serviceConfig;

  @Provides
  @Singleton
  HandlerFactory provideHandler(EchoHandler echoHandler, ExampleHandler exampleHandler,
                                LoginHandler loginHandler, RegisterHandler registerHandler,
                                ConversationListHandler conversationListHandler, ChatListHandler chatListHandler,
                                JWTAuthHandler jwtAuthHandler, UserListHandler userListHandler) {
    return HandlerFactory.builder()
            .echoHandler(echoHandler)
            .exampleHandler(exampleHandler)
            .loginHandler(loginHandler)
            .registerHandler(registerHandler)
            .conversationListHandler(conversationListHandler)
            .chatListHandler(chatListHandler)
            .jwtAuthHandler(jwtAuthHandler)
            .userListHandler(userListHandler)
            .build();
  }

  @Provides
  @Singleton
  AsyncHandler ProvideAsyncHandler(Vertx vertx) {
    return AsyncHandler.builder().vertx(vertx).build();
  }

  @Provides
  @Singleton
  RedisCache provideRedisCache() {
    try {
      return RedisCache.newInstance();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Provides
  @Singleton
  UserCache provideUserCache(RedisCache redisCache, AsyncHandler asyncHandler) {
    return UserCacheImpl.builder().redisCache(redisCache).asyncHandler(asyncHandler).build();
  }

  @Provides
  @Singleton
  ConversationListCache provideConversationListCache(RedisCache redisCache, AsyncHandler asyncHandler) {
    return ConversationListCacheImpl.builder().redisCache(redisCache).asyncHandler(asyncHandler).build();
  }

  @Provides
  @Singleton
  ChatListCache provideChatListCache(RedisCache redisCache, AsyncHandler asyncHandler) {
    return ChatListCacheImpl.builder().redisCache(redisCache).asyncHandler(asyncHandler).build();
  }

  @Provides
  @Singleton
  EchoHandler provideEchoHandler() {
    return new EchoHandler();
  }

  @Provides
  @Singleton
  UserDA provideUserDA(DataSourceProvider dataSourceProvider, AsyncHandler asyncHandler) {
    return new UserDAImpl(
        dataSourceProvider.getDataSource(serviceConfig.getMySQLConfig()), asyncHandler);
  }

  @Provides
  @Singleton
  ConversationDA provideConversationDA(DataSourceProvider dataSourceProvider, AsyncHandler asyncHandler) {
    return new ConversationDAImpl(
            dataSourceProvider.getDataSource(serviceConfig.getMySQLConfig()), asyncHandler);
  }

  @Provides
  @Singleton
  ConversationMemberDA provideConversationMemberDA(DataSourceProvider dataSourceProvider, AsyncHandler asyncHandler) {
    return new ConversationMemberDAImpl(
            dataSourceProvider.getDataSource(serviceConfig.getMySQLConfig()), asyncHandler);
  }

  @Provides
  @Singleton
  ChatListDA provideChatListDA(DataSourceProvider dataSourceProvider, AsyncHandler asyncHandler) {
    return new ChatListDAImpl(
            dataSourceProvider.getDataSource(serviceConfig.getMySQLConfig()), asyncHandler);
  }

  @Provides
  @Singleton
  ExampleHandler provideExampleHandler(
      UserDA userDA, TransactionProvider transactionProvider, UserCache userCache) {
    return new ExampleHandler(userDA, userCache, transactionProvider);
  }

  @Provides
  @Singleton
  LoginHandler provideLoginHandler(
          UserDA userDA, TransactionProvider transactionProvider, UserCache userCache, JWTAuth jwtAuth) {
    return new LoginHandler(userDA, userCache, transactionProvider, jwtAuth);
  }

  @Provides
  @Singleton
  UserListHandler provideUserListHandler(
          UserDA userDA, TransactionProvider transactionProvider, UserCache userCache, JWTAuth jwtAuth) {
    return new UserListHandler(userDA, userCache, transactionProvider, jwtAuth);
  }

  @Provides
  @Singleton
  ConversationListHandler provideConversationListHandler(
          ConversationMemberDA conversationMemberDA, TransactionProvider transactionProvider, ConversationListCache conversationListCache) {
    return new ConversationListHandler(conversationMemberDA, conversationListCache, transactionProvider);
  }

  @Provides
  @Singleton
  ChatListHandler provideChatListHandler(
          ChatListDA chatListDA, TransactionProvider transactionProvider, ChatListCache chatListCache, JWTAuth jwtAuth) {
    return new ChatListHandler(chatListDA, chatListCache, transactionProvider, jwtAuth);
  }

  @Provides
  @Singleton
  RegisterHandler provideRegisterHandler(
          UserDA userDA, TransactionProvider transactionProvider, UserCache userCache, JWTAuth jwtAuth) {
    return new RegisterHandler(userDA, userCache, transactionProvider, jwtAuth);
  }


  @Provides
  @Singleton
  JWTAuthHandler provideJWTAuthHandler(JWTAuth authProvider) {
    return JWTAuthHandler.builder().authProvider(authProvider).build();
  }

  @Singleton
  @Provides
  DataSourceProvider provideDataSourceProvider(Vertx vertx) {
    return new DataSourceProviderImpl(vertx);
  }

  @Singleton
  @Provides
  TransactionProvider provideTransactionProvider(DataSourceProvider dataSourceProvider) {
    return new TransactionProviderImpl(
        dataSourceProvider.getVertxDataSource(serviceConfig.getMySQLConfig()));
  }

  @Provides
  @Singleton
  Vertx provideVertx() {
    return Vertx.vertx(
        new VertxOptions()
            .setPreferNativeTransport(true)
            .setMetricsOptions(
                new MicrometerMetricsOptions()
                    .setMicrometerRegistry(Tracker.getMeterRegistry())
                    .setJvmMetricsEnabled(true)
                    .setPrometheusOptions(
                        new VertxPrometheusOptions()
                            .setEnabled(true)
                            .setStartEmbeddedServer(true)
                            .setPublishQuantiles(true)
                            .setEmbeddedServerOptions(
                                new HttpServerOptions()
                                    .setPort(serviceConfig.getVertxConfig().getPrometheusPort()))
                            .setEmbeddedServerEndpoint("/metrics"))
                    .setEnabled(true)));
  }

  @Provides
  @Singleton
  JWTAuthOptions provideJWTAuthOptions() {
    // TODO: add key store
    return new JWTAuthOptions()
            .addPubSecKey(
                    new PubSecKeyOptions().setAlgorithm("HS256").setPublicKey("2002").setSymmetric(true));
  }

  @Provides
  @Singleton
  JWTAuth provideAuthProvider(Vertx vertx, JWTAuthOptions authConfig) {
    return JWTAuth.create(vertx, authConfig);
  }

  @Provides
  @Singleton
  RestfulAPI provideRestfulAPI(HandlerFactory handlerFactory, Vertx vertx, JWTAuth authProvider) {
    return RestfulAPI.builder()
        .vertx(vertx)
        .authProvider(authProvider)
        .handlerFactory(handlerFactory)
        .port(serviceConfig.getPort())
        .build();
  }

  @Provides
  @Singleton
  WSHandler provideWSHandler(
          ChatListDA chatListDA, ChatListCache chatListCache, TransactionProvider transactionProvider) {
    return WSHandler.builder()
            .chatListDA(chatListDA)
            .chatListCache(chatListCache)
            .clients(new ConcurrentHashMap<>())
            .transactionProvider(transactionProvider)
            .build();
  }

  @Provides
  @Singleton
  WebSocketServer provideWebSocketServer(
          WSHandler wsHandler, Vertx vertx, JWTAuth jwtAuth) {
    return WebSocketServer.builder()
            .wsHandler(wsHandler)
            .vertx(vertx)
            .port(serviceConfig.getWsPort())
            .jwtAuth(jwtAuth)
            .build();
  }

  @Provides
  @Singleton
  GRPCServer provideGRPCServer(){
    return new GRPCServer();

  }

}
