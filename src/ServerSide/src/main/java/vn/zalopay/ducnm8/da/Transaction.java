package vn.zalopay.ducnm8.da;

import io.vertx.core.Future;
import org.apache.logging.log4j.Logger;
import vn.zalopay.ducnm8.utils.ExceptionUtil;

public interface Transaction {
  Future<Void> begin();

  Future<Void> commit();

  Future<Void> rollback();

  Future<Void> close();

  default void close(Logger logger, String method) {
    this.close()
        .setHandler(
            rs -> {
              if (rs.failed()) {
                logger.error(
                    "{} close transaction failed cause={}",
                    method,
                    ExceptionUtil.getDetail(rs.cause()));
              }
            });
  }

  <R> Future<R> execute(Executable<R> executable);
}
