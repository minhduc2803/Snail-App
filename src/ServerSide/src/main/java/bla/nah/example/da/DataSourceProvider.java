package bla.nah.example.da;

import bla.nah.example.config.MySQLConfig;
import io.vertx.ext.jdbc.JDBCClient;

import javax.sql.DataSource;

public interface DataSourceProvider {
  JDBCClient getVertxDataSource(MySQLConfig config);

  DataSource getDataSource(MySQLConfig config);
}
