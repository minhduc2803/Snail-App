package bla.nah.example.dagger;

import bla.nah.example.da.DataSourceProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.vertx.core.Vertx;
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
public final class ServiceModule_ProvideDataSourceProviderFactory implements Factory<DataSourceProvider> {
  private final ServiceModule module;

  private final Provider<Vertx> vertxProvider;

  public ServiceModule_ProvideDataSourceProviderFactory(ServiceModule module,
      Provider<Vertx> vertxProvider) {
    this.module = module;
    this.vertxProvider = vertxProvider;
  }

  @Override
  public DataSourceProvider get() {
    return provideDataSourceProvider(module, vertxProvider.get());
  }

  public static ServiceModule_ProvideDataSourceProviderFactory create(ServiceModule module,
      Provider<Vertx> vertxProvider) {
    return new ServiceModule_ProvideDataSourceProviderFactory(module, vertxProvider);
  }

  public static DataSourceProvider provideDataSourceProvider(ServiceModule instance, Vertx vertx) {
    return Preconditions.checkNotNull(instance.provideDataSourceProvider(vertx), "Cannot return null from a non-@Nullable @Provides method");
  }
}
