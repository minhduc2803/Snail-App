package bla.nah.example.dagger;

import bla.nah.example.handler.EchoHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideEchoHandlerFactory implements Factory<EchoHandler> {
  private final ServiceModule module;

  public ServiceModule_ProvideEchoHandlerFactory(ServiceModule module) {
    this.module = module;
  }

  @Override
  public EchoHandler get() {
    return provideEchoHandler(module);
  }

  public static ServiceModule_ProvideEchoHandlerFactory create(ServiceModule module) {
    return new ServiceModule_ProvideEchoHandlerFactory(module);
  }

  public static EchoHandler provideEchoHandler(ServiceModule instance) {
    return Preconditions.checkNotNull(instance.provideEchoHandler(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
