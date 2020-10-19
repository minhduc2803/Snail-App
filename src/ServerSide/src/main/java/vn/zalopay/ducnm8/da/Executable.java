package vn.zalopay.ducnm8.da;


import io.vertx.core.Future;
import io.vertx.ext.sql.SQLConnection;

public interface Executable<T> {
    Future<T> execute(SQLConnection connection);
}
