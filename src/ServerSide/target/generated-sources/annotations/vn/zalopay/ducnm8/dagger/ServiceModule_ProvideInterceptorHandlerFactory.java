package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import vn.zalopay.ducnm8.handler.grpc.InterceptorHandler;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideInterceptorHandlerFactory implements Factory<InterceptorHandler> {
  private final ServiceModule module;

  public ServiceModule_ProvideInterceptorHandlerFactory(ServiceModule module) {
    this.module = module;
  }

  @Override
  public InterceptorHandler get() {
    return provideInterceptorHandler(module);
  }

  public static ServiceModule_ProvideInterceptorHandlerFactory create(ServiceModule module) {
    return new ServiceModule_ProvideInterceptorHandlerFactory(module);
  }

  public static InterceptorHandler provideInterceptorHandler(ServiceModule instance) {
    return Preconditions.checkNotNull(instance.provideInterceptorHandler(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
