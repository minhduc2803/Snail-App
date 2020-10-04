package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.cache.ChatListCache;
import vn.zalopay.ducnm8.da.ChatListDA;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.handler.WSHandler;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideWSHandlerFactory implements Factory<WSHandler> {
  private final ServiceModule module;

  private final Provider<ChatListDA> chatListDAProvider;

  private final Provider<ChatListCache> chatListCacheProvider;

  private final Provider<TransactionProvider> transactionProvider;

  public ServiceModule_ProvideWSHandlerFactory(ServiceModule module,
      Provider<ChatListDA> chatListDAProvider, Provider<ChatListCache> chatListCacheProvider,
      Provider<TransactionProvider> transactionProvider) {
    this.module = module;
    this.chatListDAProvider = chatListDAProvider;
    this.chatListCacheProvider = chatListCacheProvider;
    this.transactionProvider = transactionProvider;
  }

  @Override
  public WSHandler get() {
    return provideWSHandler(module, chatListDAProvider.get(), chatListCacheProvider.get(), transactionProvider.get());
  }

  public static ServiceModule_ProvideWSHandlerFactory create(ServiceModule module,
      Provider<ChatListDA> chatListDAProvider, Provider<ChatListCache> chatListCacheProvider,
      Provider<TransactionProvider> transactionProvider) {
    return new ServiceModule_ProvideWSHandlerFactory(module, chatListDAProvider, chatListCacheProvider, transactionProvider);
  }

  public static WSHandler provideWSHandler(ServiceModule instance, ChatListDA chatListDA,
      ChatListCache chatListCache, TransactionProvider transactionProvider) {
    return Preconditions.checkNotNull(instance.provideWSHandler(chatListDA, chatListCache, transactionProvider), "Cannot return null from a non-@Nullable @Provides method");
  }
}
