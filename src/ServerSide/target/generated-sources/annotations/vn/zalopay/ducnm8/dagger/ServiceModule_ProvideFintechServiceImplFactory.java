package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.grpc.FintechServiceImpl;
import vn.zalopay.ducnm8.handler.grpc.GetBalanceHandler;
import vn.zalopay.ducnm8.handler.grpc.GetHistoryHandler;
import vn.zalopay.ducnm8.handler.grpc.TransferHandler;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideFintechServiceImplFactory implements Factory<FintechServiceImpl> {
  private final ServiceModule module;

  private final Provider<GetBalanceHandler> getBalanceHandlerProvider;

  private final Provider<GetHistoryHandler> getHistoryHandlerProvider;

  private final Provider<TransferHandler> transferHandlerProvider;

  public ServiceModule_ProvideFintechServiceImplFactory(ServiceModule module,
      Provider<GetBalanceHandler> getBalanceHandlerProvider,
      Provider<GetHistoryHandler> getHistoryHandlerProvider,
      Provider<TransferHandler> transferHandlerProvider) {
    this.module = module;
    this.getBalanceHandlerProvider = getBalanceHandlerProvider;
    this.getHistoryHandlerProvider = getHistoryHandlerProvider;
    this.transferHandlerProvider = transferHandlerProvider;
  }

  @Override
  public FintechServiceImpl get() {
    return provideFintechServiceImpl(module, getBalanceHandlerProvider.get(), getHistoryHandlerProvider.get(), transferHandlerProvider.get());
  }

  public static ServiceModule_ProvideFintechServiceImplFactory create(ServiceModule module,
      Provider<GetBalanceHandler> getBalanceHandlerProvider,
      Provider<GetHistoryHandler> getHistoryHandlerProvider,
      Provider<TransferHandler> transferHandlerProvider) {
    return new ServiceModule_ProvideFintechServiceImplFactory(module, getBalanceHandlerProvider, getHistoryHandlerProvider, transferHandlerProvider);
  }

  public static FintechServiceImpl provideFintechServiceImpl(ServiceModule instance,
      GetBalanceHandler getBalanceHandler, GetHistoryHandler getHistoryHandler,
      TransferHandler transferHandler) {
    return Preconditions.checkNotNull(instance.provideFintechServiceImpl(getBalanceHandler, getHistoryHandler, transferHandler), "Cannot return null from a non-@Nullable @Provides method");
  }
}
