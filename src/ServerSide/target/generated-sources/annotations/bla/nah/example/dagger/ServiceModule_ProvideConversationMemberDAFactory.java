package bla.nah.example.dagger;

import bla.nah.example.da.ConversationMemberDA;
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
public final class ServiceModule_ProvideConversationMemberDAFactory implements Factory<ConversationMemberDA> {
  private final ServiceModule module;

  private final Provider<DataSourceProvider> dataSourceProvider;

  private final Provider<AsyncHandler> asyncHandlerProvider;

  public ServiceModule_ProvideConversationMemberDAFactory(ServiceModule module,
      Provider<DataSourceProvider> dataSourceProvider,
      Provider<AsyncHandler> asyncHandlerProvider) {
    this.module = module;
    this.dataSourceProvider = dataSourceProvider;
    this.asyncHandlerProvider = asyncHandlerProvider;
  }

  @Override
  public ConversationMemberDA get() {
    return provideConversationMemberDA(module, dataSourceProvider.get(), asyncHandlerProvider.get());
  }

  public static ServiceModule_ProvideConversationMemberDAFactory create(ServiceModule module,
      Provider<DataSourceProvider> dataSourceProvider,
      Provider<AsyncHandler> asyncHandlerProvider) {
    return new ServiceModule_ProvideConversationMemberDAFactory(module, dataSourceProvider, asyncHandlerProvider);
  }

  public static ConversationMemberDA provideConversationMemberDA(ServiceModule instance,
      DataSourceProvider dataSourceProvider, AsyncHandler asyncHandler) {
    return Preconditions.checkNotNull(instance.provideConversationMemberDA(dataSourceProvider, asyncHandler), "Cannot return null from a non-@Nullable @Provides method");
  }
}
