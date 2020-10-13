package vn.zalopay.ducnm8.server;

import io.grpc.Status;
import io.jsonwebtoken.JwtException;
import vn.zalopay.ducnm8.handler.WSHandler;
import vn.zalopay.ducnm8.utils.JWTUtils;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.ServerWebSocket;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Builder
public class WebSocketServer {
    private final WSHandler wsHandler;
    private final Vertx vertx;
    private final int port;
    private HttpServer httpServer;


    private String getToken(ServerWebSocket ws) throws Exception{
        String query = ws.query();
        if (query != null &&  !query.isEmpty()) {
            return query.substring(query.indexOf('=') + 1);
        } else throw new Exception("URL query is not given");
    }

    public void start() {
        log.info("Starting WebSocket Server ...");
        httpServer =
            vertx
                .createHttpServer()
                .websocketHandler(ws -> {
                    try {
                        long id = JWTUtils.authenticate(getToken(ws));
                        ws.accept();

                        log.info("web socket connected from UserID: {}",id);
                        wsHandler.addClient(ws, id);

                        ws.closeHandler(event -> {
                            wsHandler.removeClient(ws, id);
                        });
                        ws.handler(buffer -> wsHandler.handle(buffer, id));

                    } catch (Exception e) {
                        ws.reject();
                        log.info("web socket reject, cause: {}",e.getMessage());
                    }
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
