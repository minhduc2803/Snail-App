package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.da.interact.AccountDA;
import vn.zalopay.ducnm8.da.interact.TransferDA;
import vn.zalopay.ducnm8.da.interact.TransferHistoryDA;
import vn.zalopay.ducnm8.handler.WSHandler;
import vn.zalopay.ducnm8.handler.grpc.TransferHandler;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideTransferHandlerFactory implements Factory<TransferHandler> {
  private final ServiceModule module;

  private final Provider<TransferDA> transferDAProvider;

  private final Provider<AccountDA> accountDAProvider;

  private final Provider<TransferHistoryDA> transferHistoryDAProvider;

  private final Provider<TransactionProvider> transactionProvider;

  private final Provider<WSHandler> wsHandlerProvider;

  public ServiceModule_ProvideTransferHandlerFactory(ServiceModule module,
      Provider<TransferDA> transferDAProvider, Provider<AccountDA> accountDAProvider,
      Provider<TransferHistoryDA> transferHistoryDAProvider,
      Provider<TransactionProvider> transactionProvider, Provider<WSHandler> wsHandlerProvider) {
    this.module = module;
    this.transferDAProvider = transferDAProvider;
    this.accountDAProvider = accountDAProvider;
    this.transferHistoryDAProvider = transferHistoryDAProvider;
    this.transactionProvider = transactionProvider;
    this.wsHandlerProvider = wsHandlerProvider;
  }

  @Override
  public TransferHandler get() {
    return provideTransferHandler(module, transferDAProvider.get(), accountDAProvider.get(), transferHistoryDAProvider.get(), transactionProvider.get(), wsHandlerProvider.get());
  }

  public static ServiceModule_ProvideTransferHandlerFactory create(ServiceModule module,
      Provider<TransferDA> transferDAProvider, Provider<AccountDA> accountDAProvider,
      Provider<TransferHistoryDA> transferHistoryDAProvider,
      Provider<TransactionProvider> transactionProvider, Provider<WSHandler> wsHandlerProvider) {
    return new ServiceModule_ProvideTransferHandlerFactory(module, transferDAProvider, accountDAProvider, transferHistoryDAProvider, transactionProvider, wsHandlerProvider);
  }

  public static TransferHandler provideTransferHandler(ServiceModule instance,
      TransferDA transferDA, AccountDA accountDA, TransferHistoryDA transferHistoryDA,
      TransactionProvider transactionProvider, WSHandler wsHandler) {
    return Preconditions.checkNotNull(instance.provideTransferHandler(transferDA, accountDA, transferHistoryDA, transactionProvider, wsHandler), "Cannot return null from a non-@Nullable @Provides method");
  }
}
