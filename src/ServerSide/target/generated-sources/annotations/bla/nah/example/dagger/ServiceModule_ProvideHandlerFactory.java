package bla.nah.example.dagger;

import bla.nah.example.handler.ChatListHandler;
import bla.nah.example.handler.ConversationListHandler;
import bla.nah.example.handler.EchoHandler;
import bla.nah.example.handler.ExampleHandler;
import bla.nah.example.handler.HandlerFactory;
import bla.nah.example.handler.JWTAuthHandler;
import bla.nah.example.handler.LoginHandler;
import bla.nah.example.handler.RegisterHandler;
import bla.nah.example.handler.UserListHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class ServiceModule_ProvideHandlerFactory implements Factory<HandlerFactory> {
  private final ServiceModule module;

  private final Provider<EchoHandler> echoHandlerProvider;

  private final Provider<ExampleHandler> exampleHandlerProvider;

  private final Provider<LoginHandler> loginHandlerProvider;

  private final Provider<RegisterHandler> registerHandlerProvider;

  private final Provider<ConversationListHandler> conversationListHandlerProvider;

  private final Provider<ChatListHandler> chatListHandlerProvider;

  private final Provider<JWTAuthHandler> jwtAuthHandlerProvider;

  private final Provider<UserListHandler> userListHandlerProvider;

  public ServiceModule_ProvideHandlerFactory(ServiceModule module,
      Provider<EchoHandler> echoHandlerProvider, Provider<ExampleHandler> exampleHandlerProvider,
      Provider<LoginHandler> loginHandlerProvider,
      Provider<RegisterHandler> registerHandlerProvider,
      Provider<ConversationListHandler> conversationListHandlerProvider,
      Provider<ChatListHandler> chatListHandlerProvider,
      Provider<JWTAuthHandler> jwtAuthHandlerProvider,
      Provider<UserListHandler> userListHandlerProvider) {
    this.module = module;
    this.echoHandlerProvider = echoHandlerProvider;
    this.exampleHandlerProvider = exampleHandlerProvider;
    this.loginHandlerProvider = loginHandlerProvider;
    this.registerHandlerProvider = registerHandlerProvider;
    this.conversationListHandlerProvider = conversationListHandlerProvider;
    this.chatListHandlerProvider = chatListHandlerProvider;
    this.jwtAuthHandlerProvider = jwtAuthHandlerProvider;
    this.userListHandlerProvider = userListHandlerProvider;
  }

  @Override
  public HandlerFactory get() {
    return provideHandler(module, echoHandlerProvider.get(), exampleHandlerProvider.get(), loginHandlerProvider.get(), registerHandlerProvider.get(), conversationListHandlerProvider.get(), chatListHandlerProvider.get(), jwtAuthHandlerProvider.get(), userListHandlerProvider.get());
  }

  public static ServiceModule_ProvideHandlerFactory create(ServiceModule module,
      Provider<EchoHandler> echoHandlerProvider, Provider<ExampleHandler> exampleHandlerProvider,
      Provider<LoginHandler> loginHandlerProvider,
      Provider<RegisterHandler> registerHandlerProvider,
      Provider<ConversationListHandler> conversationListHandlerProvider,
      Provider<ChatListHandler> chatListHandlerProvider,
      Provider<JWTAuthHandler> jwtAuthHandlerProvider,
      Provider<UserListHandler> userListHandlerProvider) {
    return new ServiceModule_ProvideHandlerFactory(module, echoHandlerProvider, exampleHandlerProvider, loginHandlerProvider, registerHandlerProvider, conversationListHandlerProvider, chatListHandlerProvider, jwtAuthHandlerProvider, userListHandlerProvider);
  }

  public static HandlerFactory provideHandler(ServiceModule instance, EchoHandler echoHandler,
      ExampleHandler exampleHandler, LoginHandler loginHandler, RegisterHandler registerHandler,
      ConversationListHandler conversationListHandler, ChatListHandler chatListHandler,
      JWTAuthHandler jwtAuthHandler, UserListHandler userListHandler) {
    return Preconditions.checkNotNull(instance.provideHandler(echoHandler, exampleHandler, loginHandler, registerHandler, conversationListHandler, chatListHandler, jwtAuthHandler, userListHandler), "Cannot return null from a non-@Nullable @Provides method");
  }
}
