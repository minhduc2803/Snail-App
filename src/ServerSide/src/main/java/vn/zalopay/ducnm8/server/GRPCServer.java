package vn.zalopay.ducnm8.server;

import io.grpc.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.zalopay.ducnm8.grpc.GreeterImpl;

import java.lang.invoke.MethodHandles;

public class GRPCServer {
    private static final Logger LOGGER =
            LogManager.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());
    public void start() {
        LOGGER.info("Starting RestfulAPI Server ...");
        // Create a new server to listen on port 8080
        Server server = ServerBuilder.forPort(8080)
                .addService(new GreeterImpl())
                .build();

        try {
            // Start the server
            server.start();

            // Server threads are running in the background.
            LOGGER.info("GRPC Server start successfully !, port:{}", 8080);
            // Don't exit the main thread. Wait until server is terminated.

        }catch(Exception e){}
    }
}