package vn.zalopay.ducnm8.da.interact;

import io.vertx.core.Future;
import org.apache.logging.log4j.Logger;
import vn.zalopay.ducnm8.common.mapper.EntityMapper;
import vn.zalopay.ducnm8.da.BaseTransactionDA;
import vn.zalopay.ducnm8.da.Executable;
import vn.zalopay.ducnm8.model.Chat;
import vn.zalopay.ducnm8.model.FullChat;
import vn.zalopay.ducnm8.utils.AsyncHandler;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatListDAImpl extends BaseTransactionDA implements ChatListDA {
  private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(ChatListDAImpl.class);
  private final DataSource dataSource;
  private final AsyncHandler asyncHandler;

  private static final String INSERT_CHAT_STATEMENT =
      "INSERT INTO chat (`sender_id`, `receiver_id`, `chat_type`, `content`, `sent_time`) VALUES (?, ?, ?, ?, ?);";
  private static final String LIST_CHAT_BETWEEN_TWO_USERS =
      "SELECT * FROM chat\n" +
          "WHERE (sender_id = ? and receiver_id = ?) or (receiver_id = ? and sender_id = ?);";

  public ChatListDAImpl(DataSource dataSource, AsyncHandler asyncHandler) {
    super();
    this.dataSource = dataSource;
    this.asyncHandler = asyncHandler;
  }

  @Override
  public Future<Chat> insert(Chat chat) {
    log.info("insert a new chat, senderId = {} receiverId {}", chat.getSenderId(), chat.getReceiverId());

    Future<Chat> future = Future.future();
    asyncHandler.run(
        () -> {
          Object[] params = {chat.getSenderId(), chat.getReceiverId(), chat.getChatType(), chat.getContent(), chat.getSentTime()};
          try {
            long id = executeWithParamsAndGetId(dataSource::getConnection, INSERT_CHAT_STATEMENT, params, "insertChat");
            chat.setId(id);
            future.complete(chat);
          } catch (Exception e) {
            log.error("insert new chat failed, senderId = {} receiverId = {} ~ cause: {}", chat.getSenderId(), chat.getReceiverId(), e.getMessage());
            future.fail("insert new chat failed");
          }
        });
    return future;

  }

  @Override
  public Future<List<Chat>> getListChat(long senderId, long receiverId, long offset) {
    log.info("select list chat between {} and {}, offset = {}", senderId, receiverId, offset);
    Future<List<Chat>> future = Future.future();
    asyncHandler.run(
        () -> {
          Object[] params = {senderId, receiverId, senderId, receiverId};
          queryEntity(
              "queryListChat",
              future,
              LIST_CHAT_BETWEEN_TWO_USERS,
              params,
              this::mapListChat,
              dataSource::getConnection,
              false);
        });

    return future;
  }

  private List<Chat> mapListChat(ResultSet resultSet) throws Exception {
    List<Chat> Chats = new ArrayList<>();

    log.info(resultSet);

    while (resultSet.next()) {
      Chat chat = new Chat();
      EntityMapper.getInstance().loadResultSetIntoObject(resultSet, chat);
      Chats.add(chat);
    }

    return Chats;
  }
}
