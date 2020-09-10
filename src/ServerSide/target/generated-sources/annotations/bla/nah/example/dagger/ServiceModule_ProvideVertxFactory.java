package bla.nah.example.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.vertx.core.Vertx;
import javax.annotation.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideVertxFactory implements Factory<Vertx> {
  private final ServiceModule module;

  public ServiceModule_ProvideVertxFactory(ServiceModule module) {
    this.module = module;
  }

  @Override
  public Vertx get() {
    return provideVertx(module);
  }

  public static ServiceModule_ProvideVertxFactory create(ServiceModule module) {
    return new ServiceModule_ProvideVertxFactory(module);
  }

  public static Vertx provideVertx(ServiceModule instance) {
    return Preconditions.checkNotNull(instance.provideVertx(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
