package vn.zalopay.ducnm8.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServiceConfig {
    private VertxConfig vertxConfig;
    private MySQLConfig mySQLConfig;
    int port;
    int wsPort;
    int grpcPort;
}
