package vn.zalopay.ducnm8.dagger;

import vn.zalopay.ducnm8.cache.*;
import vn.zalopay.ducnm8.config.ServiceConfig;
import vn.zalopay.ducnm8.da.*;
import vn.zalopay.ducnm8.da.interact.*;
import vn.zalopay.ducnm8.grpc.FintechServiceImpl;
import vn.zalopay.ducnm8.handler.*;
import vn.zalopay.ducnm8.handler.grpc.*;
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
                                ChatListHandler chatListHandler,
                                JWTAuthHandler jwtAuthHandler, UserListHandler userListHandler) {
    return HandlerFactory.builder()
            .echoHandler(echoHandler)
            .exampleHandler(exampleHandler)
            .loginHandler(loginHandler)
            .registerHandler(registerHandler)
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
  AccountDA provideAccountDA(DataSourceProvider dataSourceProvider, AsyncHandler asyncHandler) {
    return new AccountDAImpl(
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
  NotificationDA provideNotificationDA(DataSourceProvider dataSourceProvider, AsyncHandler asyncHandler) {
    return new NotificationDAImpl(
            dataSourceProvider.getDataSource(serviceConfig.getMySQLConfig()), asyncHandler);
  }

  @Provides
  @Singleton
  TransferDA provideTransferDA(DataSourceProvider dataSourceProvider, AsyncHandler asyncHandler) {
    return new TransferDAImpl(
            dataSourceProvider.getDataSource(serviceConfig.getMySQLConfig()), asyncHandler);
  }

  @Provides
  @Singleton
  TransferHistoryDA provideTransferHistoryDA(DataSourceProvider dataSourceProvider, AsyncHandler asyncHandler) {
    return new TransferHistoryDAImpl(
            dataSourceProvider.getDataSource(serviceConfig.getMySQLConfig()), asyncHandler);
  }

  @Provides
  @Singleton
  ExampleHandler provideExampleHandler(
          AccountDA accountDA, TransactionProvider transactionProvider, UserCache userCache) {
    return new ExampleHandler(accountDA, userCache, transactionProvider);
  }

  @Provides
  @Singleton
  LoginHandler provideLoginHandler(
          AccountDA accountDA, TransactionProvider transactionProvider, UserCache userCache) {
    return new LoginHandler(accountDA, userCache, transactionProvider);
  }

  @Provides
  @Singleton
  UserListHandler provideUserListHandler(
          AccountDA accountDA, TransactionProvider transactionProvider, UserCache userCache) {
    return new UserListHandler(accountDA, userCache, transactionProvider);
  }

  @Provides
  @Singleton
  ChatListHandler provideChatListHandler(
          ChatListDA chatListDA, TransactionProvider transactionProvider, ChatListCache chatListCache) {
    return new ChatListHandler(chatListDA, chatListCache, transactionProvider);
  }

  @Provides
  @Singleton
  RegisterHandler provideRegisterHandler(
          AccountDA accountDA, TransactionProvider transactionProvider, UserCache userCache) {
    return new RegisterHandler(accountDA, userCache, transactionProvider);
  }


  @Provides
  @Singleton
  JWTAuthHandler provideJWTAuthHandler() {
    return JWTAuthHandler.builder().build();
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
  RestfulAPI provideRestfulAPI(HandlerFactory handlerFactory, Vertx vertx) {
    return RestfulAPI.builder()
        .vertx(vertx)
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
          WSHandler wsHandler, Vertx vertx) {
    return WebSocketServer.builder()
            .wsHandler(wsHandler)
            .vertx(vertx)
            .port(serviceConfig.getWsPort())
            .build();
  }

  @Provides
  @Singleton
  FintechServiceImpl provideFintechServiceImpl(
          GetBalanceHandler getBalanceHandler,
          GetHistoryHandler getHistoryHandler,
          TransferHandler transferHandler,
          GetNotificationHandler getNotificationHandler
  ){
    return FintechServiceImpl.builder()
            .getBalanceHandler(getBalanceHandler)
            .getHistoryHandler(getHistoryHandler)
            .transferHandler(transferHandler)
            .getNotificationHandler(getNotificationHandler)
            .build();
  }

  @Provides
  @Singleton
  GetBalanceHandler provideGetBalanceHandler(AccountDA accountDA){
    return new GetBalanceHandler(accountDA);
  }

  @Provides
  @Singleton
  GetHistoryHandler provideGetHistoryHandler(TransferHistoryDA transferHistoryDA){
    return new GetHistoryHandler(transferHistoryDA);
  }

  @Provides
  @Singleton
  TransferHandler provideTransferHandler(
           TransferDA transferDA,
           AccountDA accountDA,
           TransferHistoryDA transferHistoryDA,
           NotificationDA notificationDA,
           TransactionProvider transactionProvider,
           WSHandler wsHandler
  ){
    return new TransferHandler(transferDA, accountDA, transferHistoryDA, notificationDA, transactionProvider, wsHandler);
  }

  @Provides
  @Singleton
  GetNotificationHandler provideGetNotificationHandler(){
    return new GetNotificationHandler();
  }


  @Provides
  @Singleton
  InterceptorHandler provideInterceptorHandler(){
    return InterceptorHandler.builder().build();
  }

  @Provides
  @Singleton
  GRPCServer provideGRPCServer(Vertx vertx, FintechServiceImpl fintechService, InterceptorHandler interceptorHandler){
    return GRPCServer.builder()
            .vertx(vertx)
            .fintechService(fintechService)
            .interceptorHandler(interceptorHandler)
            .port(serviceConfig.getGrpcPort())
            .build();
  }

}
