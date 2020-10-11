package vn.zalopay.ducnm8.server;

import io.vertx.core.Vertx;
import io.vertx.grpc.VertxServer;
import io.vertx.grpc.VertxServerBuilder;
import lombok.extern.log4j.Log4j2;
import lombok.Builder;
import vn.zalopay.ducnm8.grpc.GreeterImpl;
import vn.zalopay.ducnm8.handler.grpc.InterceptorHandler;


@Log4j2
public class GRPCServer {
    private VertxServer server;
    private Vertx vertx;
    private GreeterImpl greeterSevice;
    private int port;

    @Builder
    public GRPCServer(Vertx vertx, GreeterImpl greeterSevice, int port){
        this.vertx = vertx;
        this.greeterSevice = greeterSevice;
        this.port = port;
    }
    public void start() {
        log.info("Starting GRPC Server on port {}", port);
        server = VertxServerBuilder
                .forAddress(vertx, "0.0.0.0",port)
                .addService(greeterSevice)
                .build();

        try {
            // Start the server
            server.start();

            // Server threads are running in the background.

            log.info("GRPC Server start successfully !, port:{}", port);

        }catch(Exception e){
            log.error("GRPC Server start fail. Reason: {}", e.getMessage());
        }
    }

    public void close(){
        server.shutdown();
    }
}