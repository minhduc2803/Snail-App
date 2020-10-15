package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.da.interact.TransferHistoryDA;
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

  private final Provider<TransferHistoryDA> transferHistoryDAProvider;

  public ServiceModule_ProvideGetHistoryHandlerFactory(ServiceModule module,
      Provider<TransferHistoryDA> transferHistoryDAProvider) {
    this.module = module;
    this.transferHistoryDAProvider = transferHistoryDAProvider;
  }

  @Override
  public GetHistoryHandler get() {
    return provideGetHistoryHandler(module, transferHistoryDAProvider.get());
  }

  public static ServiceModule_ProvideGetHistoryHandlerFactory create(ServiceModule module,
      Provider<TransferHistoryDA> transferHistoryDAProvider) {
    return new ServiceModule_ProvideGetHistoryHandlerFactory(module, transferHistoryDAProvider);
  }

  public static GetHistoryHandler provideGetHistoryHandler(ServiceModule instance,
      TransferHistoryDA transferHistoryDA) {
    return Preconditions.checkNotNull(instance.provideGetHistoryHandler(transferHistoryDA), "Cannot return null from a non-@Nullable @Provides method");
  }
}
