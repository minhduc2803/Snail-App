package bla.nah.example.dagger;

import bla.nah.example.cache.ChatListCache;
import bla.nah.example.da.ChatListDA;
import bla.nah.example.da.TransactionProvider;
import bla.nah.example.handler.ChatListHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.vertx.ext.auth.jwt.JWTAuth;
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
public final class ServiceModule_ProvideChatListHandlerFactory implements Factory<ChatListHandler> {
  private final ServiceModule module;

  private final Provider<ChatListDA> chatListDAProvider;

  private final Provider<TransactionProvider> transactionProvider;

  private final Provider<ChatListCache> chatListCacheProvider;

  private final Provider<JWTAuth> jwtAuthProvider;

  public ServiceModule_ProvideChatListHandlerFactory(ServiceModule module,
      Provider<ChatListDA> chatListDAProvider, Provider<TransactionProvider> transactionProvider,
      Provider<ChatListCache> chatListCacheProvider, Provider<JWTAuth> jwtAuthProvider) {
    this.module = module;
    this.chatListDAProvider = chatListDAProvider;
    this.transactionProvider = transactionProvider;
    this.chatListCacheProvider = chatListCacheProvider;
    this.jwtAuthProvider = jwtAuthProvider;
  }

  @Override
  public ChatListHandler get() {
    return provideChatListHandler(module, chatListDAProvider.get(), transactionProvider.get(), chatListCacheProvider.get(), jwtAuthProvider.get());
  }

  public static ServiceModule_ProvideChatListHandlerFactory create(ServiceModule module,
      Provider<ChatListDA> chatListDAProvider, Provider<TransactionProvider> transactionProvider,
      Provider<ChatListCache> chatListCacheProvider, Provider<JWTAuth> jwtAuthProvider) {
    return new ServiceModule_ProvideChatListHandlerFactory(module, chatListDAProvider, transactionProvider, chatListCacheProvider, jwtAuthProvider);
  }

  public static ChatListHandler provideChatListHandler(ServiceModule instance,
      ChatListDA chatListDA, TransactionProvider transactionProvider, ChatListCache chatListCache,
      JWTAuth jwtAuth) {
    return Preconditions.checkNotNull(instance.provideChatListHandler(chatListDA, transactionProvider, chatListCache, jwtAuth), "Cannot return null from a non-@Nullable @Provides method");
  }
}
