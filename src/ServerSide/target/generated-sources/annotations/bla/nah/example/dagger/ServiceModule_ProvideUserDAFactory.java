package bla.nah.example.dagger;

import bla.nah.example.da.DataSourceProvider;
import bla.nah.example.da.UserDA;
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
public final class ServiceModule_ProvideUserDAFactory implements Factory<UserDA> {
  private final ServiceModule module;

  private final Provider<DataSourceProvider> dataSourceProvider;

  private final Provider<AsyncHandler> asyncHandlerProvider;

  public ServiceModule_ProvideUserDAFactory(ServiceModule module,
      Provider<DataSourceProvider> dataSourceProvider,
      Provider<AsyncHandler> asyncHandlerProvider) {
    this.module = module;
    this.dataSourceProvider = dataSourceProvider;
    this.asyncHandlerProvider = asyncHandlerProvider;
  }

  @Override
  public UserDA get() {
    return provideUserDA(module, dataSourceProvider.get(), asyncHandlerProvider.get());
  }

  public static ServiceModule_ProvideUserDAFactory create(ServiceModule module,
      Provider<DataSourceProvider> dataSourceProvider,
      Provider<AsyncHandler> asyncHandlerProvider) {
    return new ServiceModule_ProvideUserDAFactory(module, dataSourceProvider, asyncHandlerProvider);
  }

  public static UserDA provideUserDA(ServiceModule instance, DataSourceProvider dataSourceProvider,
      AsyncHandler asyncHandler) {
    return Preconditions.checkNotNull(instance.provideUserDA(dataSourceProvider, asyncHandler), "Cannot return null from a non-@Nullable @Provides method");
  }
}
