package vn.zalopay.ducnm8.handler;

import vn.zalopay.ducnm8.cache.ChatListCache;
import vn.zalopay.ducnm8.da.interact.ChatListDA;
import vn.zalopay.ducnm8.da.Transaction;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.entity.response.WebSocketResponse;
import vn.zalopay.ducnm8.model.Chat;
import vn.zalopay.ducnm8.model.TransferHistory;
import vn.zalopay.ducnm8.utils.JsonProtoUtils;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.core.json.JsonObject;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;
import vn.zalopay.ducnm8.utils.Tracker;

import java.util.Map;
import java.time.Instant;
import java.util.Set;

@Log4j2
@Builder
public class WSHandler {
  private final String METRIC = "WSHandler";
  private final Map<Long, Set<ServerWebSocket>> clients;
  private final ChatListDA chatListDA;
  private final ChatListCache chatListCache;
  private final TransactionProvider transactionProvider;

  public void addClient(ServerWebSocket webSocket, long UserID) {
    if (clients.containsKey(UserID)) {
      clients.get(UserID).add(webSocket);
    } else {
      Set<ServerWebSocket> client = new ConcurrentHashSet<>();
      client.add(webSocket);
      clients.put(UserID, client);
    }
  }

  public void removeClient(ServerWebSocket webSocket, long userId) {
    if (clients.containsKey(userId)) {
      Set<ServerWebSocket> removedClient = clients.get(userId);
      removedClient.remove(webSocket);
      if (removedClient.isEmpty()) clients.remove(userId);
    }
  }

  public void handle(Buffer buffer, long senderId) {

    Tracker.TrackerBuilder tracker =
        Tracker.builder().metricName(METRIC).startTime(System.currentTimeMillis());

    log.info("Send chat from userId: {}", senderId);
    JsonObject json = new JsonObject(buffer.toString());

    log.info("a json chat {}", json);

    try {
      Chat chat = Chat.builder()
          .chatType(json.getInteger("chatType"))
          .senderId(senderId)
          .receiverId(json.getInteger("receiverId"))
          .content(json.getString("content"))
          .sentTime(Instant.now().getEpochSecond())
          .build();

      log.info(chat.getSentTime());

      chatListDA.insert(chat)
          .setHandler(result -> {
            if (result.succeeded()) {
              log.info("insert a new chat");
              sendChat(chat, senderId);
              sendChat(chat, chat.getReceiverId());

              tracker.step("handle").code("SUCCESS").build().record();
            } else {
              log.error("Error: cannot insert a new chat");
            }

          });
    } catch (Exception e) {
      log.error(e);
    }
  }

  private void sendChat(Chat chat, long receiverId) {
    log.info("Send chat to accountId: {}", receiverId);

    WebSocketResponse webSocketResponse = WebSocketResponse.builder()
        .responseType(1)
        .data(chat)
        .build();

    Set<ServerWebSocket> receiveWS = clients.get(receiverId);
    if (receiveWS == null)
      return;
    receiveWS.forEach(
        conn -> conn.writeTextMessage(JsonProtoUtils.printGson(webSocketResponse)));
  }

  public void sendTransferHistory(TransferHistory transferHistory, long receiverId) {
    log.info("Send Transfer history to accountId: {}", receiverId);

    WebSocketResponse webSocketResponse = WebSocketResponse.builder()
        .responseType(2)
        .data(transferHistory)
        .build();

    Set<ServerWebSocket> receiveWS = clients.get(receiverId);
    if (receiveWS == null)
      return;
    receiveWS.forEach(
        conn -> conn.writeTextMessage(JsonProtoUtils.printGson(webSocketResponse)));
  }

}