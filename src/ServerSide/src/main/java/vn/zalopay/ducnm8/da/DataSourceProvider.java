package vn.zalopay.ducnm8.da;

import io.vertx.ext.jdbc.JDBCClient;
import vn.zalopay.ducnm8.config.MySQLConfig;

import javax.sql.DataSource;

public interface DataSourceProvider {
    JDBCClient getVertxDataSource(MySQLConfig config);

    DataSource getDataSource(MySQLConfig config);
}
