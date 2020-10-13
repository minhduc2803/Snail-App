package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import vn.zalopay.ducnm8.handler.grpc.GetHistoryHandler;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideGetHistoryHandlerFactory implements Factory<GetHistoryHandler> {
  private final ServiceModule module;

  public ServiceModule_ProvideGetHistoryHandlerFactory(ServiceModule module) {
    this.module = module;
  }

  @Override
  public GetHistoryHandler get() {
    return provideGetHistoryHandler(module);
  }

  public static ServiceModule_ProvideGetHistoryHandlerFactory create(ServiceModule module) {
    return new ServiceModule_ProvideGetHistoryHandlerFactory(module);
  }

  public static GetHistoryHandler provideGetHistoryHandler(ServiceModule instance) {
    return Preconditions.checkNotNull(instance.provideGetHistoryHandler(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
