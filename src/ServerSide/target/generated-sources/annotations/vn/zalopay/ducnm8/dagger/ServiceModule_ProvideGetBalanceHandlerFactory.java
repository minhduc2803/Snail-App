package vn.zalopay.ducnm8.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.zalopay.ducnm8.da.interact.AccountDA;
import vn.zalopay.ducnm8.handler.grpc.GetBalanceHandler;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideGetBalanceHandlerFactory implements Factory<GetBalanceHandler> {
  private final ServiceModule module;

  private final Provider<AccountDA> accountDAProvider;

  public ServiceModule_ProvideGetBalanceHandlerFactory(ServiceModule module,
      Provider<AccountDA> accountDAProvider) {
    this.module = module;
    this.accountDAProvider = accountDAProvider;
  }

  @Override
  public GetBalanceHandler get() {
    return provideGetBalanceHandler(module, accountDAProvider.get());
  }

  public static ServiceModule_ProvideGetBalanceHandlerFactory create(ServiceModule module,
      Provider<AccountDA> accountDAProvider) {
    return new ServiceModule_ProvideGetBalanceHandlerFactory(module, accountDAProvider);
  }

  public static GetBalanceHandler provideGetBalanceHandler(ServiceModule instance,
      AccountDA accountDA) {
    return Preconditions.checkNotNull(instance.provideGetBalanceHandler(accountDA), "Cannot return null from a non-@Nullable @Provides method");
  }
}
