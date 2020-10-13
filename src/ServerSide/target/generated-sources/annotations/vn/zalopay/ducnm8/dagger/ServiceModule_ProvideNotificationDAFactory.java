package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.da.DataSourceProvider;
import vn.zalopay.ducnm8.da.interact.NotificationDA;
import vn.zalopay.ducnm8.utils.AsyncHandler;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideNotificationDAFactory implements Factory<NotificationDA> {
  private final ServiceModule module;

  private final Provider<DataSourceProvider> dataSourceProvider;

  private final Provider<AsyncHandler> asyncHandlerProvider;

  public ServiceModule_ProvideNotificationDAFactory(ServiceModule module,
      Provider<DataSourceProvider> dataSourceProvider,
      Provider<AsyncHandler> asyncHandlerProvider) {
    this.module = module;
    this.dataSourceProvider = dataSourceProvider;
    this.asyncHandlerProvider = asyncHandlerProvider;
  }

  @Override
  public NotificationDA get() {
    return provideNotificationDA(module, dataSourceProvider.get(), asyncHandlerProvider.get());
  }

  public static ServiceModule_ProvideNotificationDAFactory create(ServiceModule module,
      Provider<DataSourceProvider> dataSourceProvider,
      Provider<AsyncHandler> asyncHandlerProvider) {
    return new ServiceModule_ProvideNotificationDAFactory(module, dataSourceProvider, asyncHandlerProvider);
  }

  public static NotificationDA provideNotificationDA(ServiceModule instance,
      DataSourceProvider dataSourceProvider, AsyncHandler asyncHandler) {
    return Preconditions.checkNotNull(instance.provideNotificationDA(dataSourceProvider, asyncHandler), "Cannot return null from a non-@Nullable @Provides method");
  }
}
