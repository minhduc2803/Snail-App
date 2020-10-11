package vn.zalopay.ducnm8.server;

import vn.zalopay.ducnm8.handler.WSHandler;
import vn.zalopay.ducnm8.utils.JWTUtils;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.ext.auth.jwt.JWTAuth;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Builder
public class WebSocketServer {
    private final WSHandler wsHandler;
    private final Vertx vertx;
    private final int port;
    private HttpServer httpServer;
    private final JWTAuth jwtAuth;


    private Future<Long> authenticate(ServerWebSocket ws) {
        Future<Long> future = Future.future();

        String query = ws.query();
        if (query != null &&  !query.isEmpty()) {
            String token = query.substring(query.indexOf('=') + 1);
            if (token != null &&  !token.isEmpty()) {
                JWTUtils
                        .authenticate(jwtAuth, token)
                        .setHandler(UserID -> {
                                    if (UserID.succeeded()) future.complete(UserID.result());
                                });
            } else{
                log.error("JWT token is invalid");
                future.fail("JWT token is not given");
            }
        } else future.fail("URL query is not given");
        return future;
    }

    public void start() {
        log.info("Starting WebSocket Server ...");
        httpServer =
            vertx
                .createHttpServer()
                .websocketHandler(ws -> {
                    authenticate(ws)
                        .setHandler(
                            UserIDDecode -> {
                                if (UserIDDecode.succeeded()) {
                                    long UserID = UserIDDecode.result();
                                    ws.accept();

                                    log.info("websocket connected from UserID: {}",UserID);
                                    wsHandler.addClient(ws, UserID);

                                    ws.closeHandler(event -> {
                                            wsHandler.removeClient(ws, UserID);
                                        });
                                    ws.handler(buffer -> wsHandler.handle(buffer, UserID));

                                } else {
                                    log.info("websocket reject");
                                    ws.reject();
                                }
                            });
                    })
                .listen(port,
                        ar -> {
                            if (ar.succeeded()) {
                                log.info("WebSocket Server start successfully !, port:{}", port);
                            } else {
                                log.error("WebSocket Server start fail. Reason: {}", ar.cause().getMessage());
                        }});
    }

    public void close() {
        httpServer.close();
    }
}
