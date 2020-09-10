package bla.nah.example.da;


import io.vertx.core.Future;
import io.vertx.ext.sql.SQLConnection;

public interface Executable<T> {
  Future<T> execute(SQLConnection connection);
}
