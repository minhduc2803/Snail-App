package bla.nah.example.dagger;

import bla.nah.example.da.ChatListDA;
import bla.nah.example.da.DataSourceProvider;
import bla.nah.example.utils.AsyncHandler;
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
public final class ServiceModule_ProvideChatListDAFactory implements Factory<ChatListDA> {
  private final ServiceModule module;

  private final Provider<DataSourceProvider> dataSourceProvider;

  private final Provider<AsyncHandler> asyncHandlerProvider;

  public ServiceModule_ProvideChatListDAFactory(ServiceModule module,
      Provider<DataSourceProvider> dataSourceProvider,
      Provider<AsyncHandler> asyncHandlerProvider) {
    this.module = module;
    this.dataSourceProvider = dataSourceProvider;
    this.asyncHandlerProvider = asyncHandlerProvider;
  }

  @Override
  public ChatListDA get() {
    return provideChatListDA(module, dataSourceProvider.get(), asyncHandlerProvider.get());
  }

  public static ServiceModule_ProvideChatListDAFactory create(ServiceModule module,
      Provider<DataSourceProvider> dataSourceProvider,
      Provider<AsyncHandler> asyncHandlerProvider) {
    return new ServiceModule_ProvideChatListDAFactory(module, dataSourceProvider, asyncHandlerProvider);
  }

  public static ChatListDA provideChatListDA(ServiceModule instance,
      DataSourceProvider dataSourceProvider, AsyncHandler asyncHandler) {
    return Preconditions.checkNotNull(instance.provideChatListDA(dataSourceProvider, asyncHandler), "Cannot return null from a non-@Nullable @Provides method");
  }
}
