package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import vn.zalopay.ducnm8.handler.grpc.GetNotificationHandler;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideGetNotificationHandlerFactory implements Factory<GetNotificationHandler> {
  private final ServiceModule module;

  public ServiceModule_ProvideGetNotificationHandlerFactory(ServiceModule module) {
    this.module = module;
  }

  @Override
  public GetNotificationHandler get() {
    return provideGetNotificationHandler(module);
  }

  public static ServiceModule_ProvideGetNotificationHandlerFactory create(ServiceModule module) {
    return new ServiceModule_ProvideGetNotificationHandlerFactory(module);
  }

  public static GetNotificationHandler provideGetNotificationHandler(ServiceModule instance) {
    return Preconditions.checkNotNull(instance.provideGetNotificationHandler(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
