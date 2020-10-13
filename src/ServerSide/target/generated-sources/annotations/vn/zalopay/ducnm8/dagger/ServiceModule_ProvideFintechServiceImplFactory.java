package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.grpc.FintechServiceImpl;
import vn.zalopay.ducnm8.handler.grpc.GetBalanceHandler;
import vn.zalopay.ducnm8.handler.grpc.GetHistoryHandler;
import vn.zalopay.ducnm8.handler.grpc.GetNotificationHandler;
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

  private final Provider<GetNotificationHandler> getNotificationHandlerProvider;

  public ServiceModule_ProvideFintechServiceImplFactory(ServiceModule module,
      Provider<GetBalanceHandler> getBalanceHandlerProvider,
      Provider<GetHistoryHandler> getHistoryHandlerProvider,
      Provider<TransferHandler> transferHandlerProvider,
      Provider<GetNotificationHandler> getNotificationHandlerProvider) {
    this.module = module;
    this.getBalanceHandlerProvider = getBalanceHandlerProvider;
    this.getHistoryHandlerProvider = getHistoryHandlerProvider;
    this.transferHandlerProvider = transferHandlerProvider;
    this.getNotificationHandlerProvider = getNotificationHandlerProvider;
  }

  @Override
  public FintechServiceImpl get() {
    return provideFintechServiceImpl(module, getBalanceHandlerProvider.get(), getHistoryHandlerProvider.get(), transferHandlerProvider.get(), getNotificationHandlerProvider.get());
  }

  public static ServiceModule_ProvideFintechServiceImplFactory create(ServiceModule module,
      Provider<GetBalanceHandler> getBalanceHandlerProvider,
      Provider<GetHistoryHandler> getHistoryHandlerProvider,
      Provider<TransferHandler> transferHandlerProvider,
      Provider<GetNotificationHandler> getNotificationHandlerProvider) {
    return new ServiceModule_ProvideFintechServiceImplFactory(module, getBalanceHandlerProvider, getHistoryHandlerProvider, transferHandlerProvider, getNotificationHandlerProvider);
  }

  public static FintechServiceImpl provideFintechServiceImpl(ServiceModule instance,
      GetBalanceHandler getBalanceHandler, GetHistoryHandler getHistoryHandler,
      TransferHandler transferHandler, GetNotificationHandler getNotificationHandler) {
    return Preconditions.checkNotNull(instance.provideFintechServiceImpl(getBalanceHandler, getHistoryHandler, transferHandler, getNotificationHandler), "Cannot return null from a non-@Nullable @Provides method");
  }
}
