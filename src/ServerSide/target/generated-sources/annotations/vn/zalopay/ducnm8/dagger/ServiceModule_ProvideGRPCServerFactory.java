package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import vn.zalopay.ducnm8.server.GRPCServer;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideGRPCServerFactory implements Factory<GRPCServer> {
  private final ServiceModule module;

  public ServiceModule_ProvideGRPCServerFactory(ServiceModule module) {
    this.module = module;
  }

  @Override
  public GRPCServer get() {
    return provideGRPCServer(module);
  }

  public static ServiceModule_ProvideGRPCServerFactory create(ServiceModule module) {
    return new ServiceModule_ProvideGRPCServerFactory(module);
  }

  public static GRPCServer provideGRPCServer(ServiceModule instance) {
    return Preconditions.checkNotNull(instance.provideGRPCServer(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
