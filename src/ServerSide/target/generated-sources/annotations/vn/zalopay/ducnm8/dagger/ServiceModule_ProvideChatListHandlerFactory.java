package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.cache.ChatListCache;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.da.interact.ChatListDA;
import vn.zalopay.ducnm8.handler.ChatListHandler;

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

  public ServiceModule_ProvideChatListHandlerFactory(ServiceModule module,
      Provider<ChatListDA> chatListDAProvider, Provider<TransactionProvider> transactionProvider,
      Provider<ChatListCache> chatListCacheProvider) {
    this.module = module;
    this.chatListDAProvider = chatListDAProvider;
    this.transactionProvider = transactionProvider;
    this.chatListCacheProvider = chatListCacheProvider;
  }

  @Override
  public ChatListHandler get() {
    return provideChatListHandler(module, chatListDAProvider.get(), transactionProvider.get(), chatListCacheProvider.get());
  }

  public static ServiceModule_ProvideChatListHandlerFactory create(ServiceModule module,
      Provider<ChatListDA> chatListDAProvider, Provider<TransactionProvider> transactionProvider,
      Provider<ChatListCache> chatListCacheProvider) {
    return new ServiceModule_ProvideChatListHandlerFactory(module, chatListDAProvider, transactionProvider, chatListCacheProvider);
  }

  public static ChatListHandler provideChatListHandler(ServiceModule instance,
      ChatListDA chatListDA, TransactionProvider transactionProvider, ChatListCache chatListCache) {
    return Preconditions.checkNotNull(instance.provideChatListHandler(chatListDA, transactionProvider, chatListCache), "Cannot return null from a non-@Nullable @Provides method");
  }
}
