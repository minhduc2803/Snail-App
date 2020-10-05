package vn.zalopay.ducnm8.server;

import io.grpc.*;
import vn.zalopay.ducnm8.grpc.GreetingServiceImpl;

public class GRPCServer {
    public void start() {
        // Create a new server to listen on port 8080
        Server server = ServerBuilder.forPort(8080)
                .addService(new GreetingServiceImpl())
                .build();

        try {
            // Start the server
            server.start();

            // Server threads are running in the background.
            System.out.println("Server started");
            // Don't exit the main thread. Wait until server is terminated.
            server.awaitTermination();
        }catch(Exception e){}
    }
}