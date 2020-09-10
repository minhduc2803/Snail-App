package bla.nah.example.handler;

import bla.nah.example.cache.ChatListCache;
import bla.nah.example.da.ChatListDA;
import bla.nah.example.da.Transaction;
import bla.nah.example.da.TransactionProvider;
import bla.nah.example.entity.request.MessageRequest;
import bla.nah.example.model.Chat;
import bla.nah.example.server.WebSocketServer;
import bla.nah.example.utils.JsonProtoUtils;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.core.json.JsonObject;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.time.Instant;
import java.util.Set;

@Log4j2
@Builder
public class WSHandler {
    private Map<Integer, Set<ServerWebSocket>> clients;
    private final ChatListDA chatListDA;
    private final ChatListCache chatListCache;
    private final TransactionProvider transactionProvider;

    public void addClient(ServerWebSocket webSocket, int UserID) {
        if (clients.containsKey(UserID)) {
            clients.get(UserID).add(webSocket);
        } else {
            Set<ServerWebSocket> client = new ConcurrentHashSet<>();
            client.add(webSocket);
            clients.put(UserID, client);
        }
    }

    public void removeClient(ServerWebSocket webSocket, int userId) {
        if (clients.containsKey(userId)) {
            Set<ServerWebSocket> removedClient = clients.get(userId);
            removedClient.remove(webSocket);
            if (removedClient.isEmpty()) clients.remove(userId);
        }
    }

    public void handle(Buffer buffer, int UserID) {
        log.info("Send chat from UserID: {}",UserID);
        JsonObject json = new JsonObject(buffer.toString());

        log.info(buffer.toString());
        Date date = new Date();
        Chat chat = Chat.builder()
                        .Mode(json.getInteger("Mode"))
                        .UserSendID(UserID)
                        .UserReceiveID(json.getInteger("UserReceiveID"))
                        .Content(json.getString("Content"))
                        .SentTime(Instant.now().getEpochSecond())
                        .build();

        log.info(chat.getSentTime());
        Future<Chat> future = Future.future();

        Transaction transaction = transactionProvider.newTransaction();

        transaction
            .begin()
            .compose(next -> transaction.execute(chatListDA.insert(chat)))
            .setHandler(result -> {
                    if (result.succeeded()) {

                        Timestamp formatTime = new Timestamp(chat.getSentTime());
                        chat.setFormatTime(String.valueOf(formatTime));
                        SendChat(chat, UserID);
                        SendChat(chat, chat.getUserReceiveID());
                        transaction
                                .commit()
                                .compose(next -> transaction.close())
                                .setHandler(e -> future.complete(result.result()));
                    } else {
                        log.error("Error: cannot insert a new chat");
                        future.complete(null);
                    }

                });
    }

    private void SendChat(Chat chat, int UserReceiveID) {
        log.info("Send chat to UserID: {}",UserReceiveID);
        Set<ServerWebSocket> receiveWS = clients.get(UserReceiveID);
        receiveWS.forEach(
                conn -> {
                    conn.writeTextMessage(JsonProtoUtils.printGson(chat));
                });
    }
}