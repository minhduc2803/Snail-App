package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import vn.zalopay.ducnm8.grpc.FintechServiceImpl;

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

  public ServiceModule_ProvideFintechServiceImplFactory(ServiceModule module) {
    this.module = module;
  }

  @Override
  public FintechServiceImpl get() {
    return provideFintechServiceImpl(module);
  }

  public static ServiceModule_ProvideFintechServiceImplFactory create(ServiceModule module) {
    return new ServiceModule_ProvideFintechServiceImplFactory(module);
  }

  public static FintechServiceImpl provideFintechServiceImpl(ServiceModule instance) {
    return Preconditions.checkNotNull(instance.provideFintechServiceImpl(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
