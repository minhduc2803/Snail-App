package bla.nah.example.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import javax.annotation.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ServiceModule_ProvideJWTAuthOptionsFactory implements Factory<JWTAuthOptions> {
  private final ServiceModule module;

  public ServiceModule_ProvideJWTAuthOptionsFactory(ServiceModule module) {
    this.module = module;
  }

  @Override
  public JWTAuthOptions get() {
    return provideJWTAuthOptions(module);
  }

  public static ServiceModule_ProvideJWTAuthOptionsFactory create(ServiceModule module) {
    return new ServiceModule_ProvideJWTAuthOptionsFactory(module);
  }

  public static JWTAuthOptions provideJWTAuthOptions(ServiceModule instance) {
    return Preconditions.checkNotNull(instance.provideJWTAuthOptions(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
