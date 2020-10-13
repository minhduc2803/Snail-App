package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.da.interact.AccountDA;
import vn.zalopay.ducnm8.da.interact.NotificationDA;
import vn.zalopay.ducnm8.da.interact.TransferDA;
import vn.zalopay.ducnm8.da.interact.TransferHistoryDA;
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

  private final Provider<NotificationDA> notificationDAProvider;

  private final Provider<TransactionProvider> transactionProvider;

  public ServiceModule_ProvideTransferHandlerFactory(ServiceModule module,
      Provider<TransferDA> transferDAProvider, Provider<AccountDA> accountDAProvider,
      Provider<TransferHistoryDA> transferHistoryDAProvider,
      Provider<NotificationDA> notificationDAProvider,
      Provider<TransactionProvider> transactionProvider) {
    this.module = module;
    this.transferDAProvider = transferDAProvider;
    this.accountDAProvider = accountDAProvider;
    this.transferHistoryDAProvider = transferHistoryDAProvider;
    this.notificationDAProvider = notificationDAProvider;
    this.transactionProvider = transactionProvider;
  }

  @Override
  public TransferHandler get() {
    return provideTransferHandler(module, transferDAProvider.get(), accountDAProvider.get(), transferHistoryDAProvider.get(), notificationDAProvider.get(), transactionProvider.get());
  }

  public static ServiceModule_ProvideTransferHandlerFactory create(ServiceModule module,
      Provider<TransferDA> transferDAProvider, Provider<AccountDA> accountDAProvider,
      Provider<TransferHistoryDA> transferHistoryDAProvider,
      Provider<NotificationDA> notificationDAProvider,
      Provider<TransactionProvider> transactionProvider) {
    return new ServiceModule_ProvideTransferHandlerFactory(module, transferDAProvider, accountDAProvider, transferHistoryDAProvider, notificationDAProvider, transactionProvider);
  }

  public static TransferHandler provideTransferHandler(ServiceModule instance,
      TransferDA transferDA, AccountDA accountDA, TransferHistoryDA transferHistoryDA,
      NotificationDA notificationDA, TransactionProvider transactionProvider) {
    return Preconditions.checkNotNull(instance.provideTransferHandler(transferDA, accountDA, transferHistoryDA, notificationDA, transactionProvider), "Cannot return null from a non-@Nullable @Provides method");
  }
}
