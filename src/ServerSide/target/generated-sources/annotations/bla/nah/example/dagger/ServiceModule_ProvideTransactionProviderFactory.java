package bla.nah.example.dagger;

import bla.nah.example.da.DataSourceProvider;
import bla.nah.example.da.TransactionProvider;
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
public final class ServiceModule_ProvideTransactionProviderFactory implements Factory<TransactionProvider> {
  private final ServiceModule module;

  private final Provider<DataSourceProvider> dataSourceProvider;

  public ServiceModule_ProvideTransactionProviderFactory(ServiceModule module,
      Provider<DataSourceProvider> dataSourceProvider) {
    this.module = module;
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public TransactionProvider get() {
    return provideTransactionProvider(module, dataSourceProvider.get());
  }

  public static ServiceModule_ProvideTransactionProviderFactory create(ServiceModule module,
      Provider<DataSourceProvider> dataSourceProvider) {
    return new ServiceModule_ProvideTransactionProviderFactory(module, dataSourceProvider);
  }

  public static TransactionProvider provideTransactionProvider(ServiceModule instance,
      DataSourceProvider dataSourceProvider) {
    return Preconditions.checkNotNull(instance.provideTransactionProvider(dataSourceProvider), "Cannot return null from a non-@Nullable @Provides method");
  }
}
