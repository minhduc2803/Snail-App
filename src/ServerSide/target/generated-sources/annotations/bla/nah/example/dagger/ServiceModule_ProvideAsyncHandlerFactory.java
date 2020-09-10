package bla.nah.example.dagger;

import bla.nah.example.utils.AsyncHandler;
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
public final class ServiceModule_ProvideAsyncHandlerFactory implements Factory<AsyncHandler> {
  private final ServiceModule module;

  private final Provider<Vertx> vertxProvider;

  public ServiceModule_ProvideAsyncHandlerFactory(ServiceModule module,
      Provider<Vertx> vertxProvider) {
    this.module = module;
    this.vertxProvider = vertxProvider;
  }

  @Override
  public AsyncHandler get() {
    return ProvideAsyncHandler(module, vertxProvider.get());
  }

  public static ServiceModule_ProvideAsyncHandlerFactory create(ServiceModule module,
      Provider<Vertx> vertxProvider) {
    return new ServiceModule_ProvideAsyncHandlerFactory(module, vertxProvider);
  }

  public static AsyncHandler ProvideAsyncHandler(ServiceModule instance, Vertx vertx) {
    return Preconditions.checkNotNull(instance.ProvideAsyncHandler(vertx), "Cannot return null from a non-@Nullable @Provides method");
  }
}
