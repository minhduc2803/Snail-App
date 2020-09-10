package bla.nah.example.dagger;

import bla.nah.example.da.ConversationDA;
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
public final class ServiceModule_ProvideConversationDAFactory implements Factory<ConversationDA> {
  private final ServiceModule module;

  private final Provider<DataSourceProvider> dataSourceProvider;

  private final Provider<AsyncHandler> asyncHandlerProvider;

  public ServiceModule_ProvideConversationDAFactory(ServiceModule module,
      Provider<DataSourceProvider> dataSourceProvider,
      Provider<AsyncHandler> asyncHandlerProvider) {
    this.module = module;
    this.dataSourceProvider = dataSourceProvider;
    this.asyncHandlerProvider = asyncHandlerProvider;
  }

  @Override
  public ConversationDA get() {
    return provideConversationDA(module, dataSourceProvider.get(), asyncHandlerProvider.get());
  }

  public static ServiceModule_ProvideConversationDAFactory create(ServiceModule module,
      Provider<DataSourceProvider> dataSourceProvider,
      Provider<AsyncHandler> asyncHandlerProvider) {
    return new ServiceModule_ProvideConversationDAFactory(module, dataSourceProvider, asyncHandlerProvider);
  }

  public static ConversationDA provideConversationDA(ServiceModule instance,
      DataSourceProvider dataSourceProvider, AsyncHandler asyncHandler) {
    return Preconditions.checkNotNull(instance.provideConversationDA(dataSourceProvider, asyncHandler), "Cannot return null from a non-@Nullable @Provides method");
  }
}
