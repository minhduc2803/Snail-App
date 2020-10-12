package vn.zalopay.ducnm8.da.interact;

import io.vertx.core.Future;
import org.apache.logging.log4j.Logger;
import vn.zalopay.ducnm8.common.mapper.EntityMapper;
import vn.zalopay.ducnm8.da.BaseTransactionDA;
import vn.zalopay.ducnm8.da.Executable;
import vn.zalopay.ducnm8.model.Chat;
import vn.zalopay.ducnm8.utils.AsyncHandler;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatListDAImpl extends BaseTransactionDA implements ChatListDA{
    private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(ChatListDAImpl.class);
    private final DataSource dataSource;
    private final AsyncHandler asyncHandler;

    private static final String INSERT_CHAT_STATEMENT =
            "INSERT INTO chat (`mode`, `user_send_id`, `user_receive_id`, `content`, `sent_time`) VALUES (?, ?, ?, ?, ?);";
    private static final String LIST_CHAT_BY_MEMBER = "SELECT * FROM chat\n" +
            "WHERE (user_send_id = ? and user_receive_id = ?) or (user_send_id = ? and user_receive_id = ?);";

    public ChatListDAImpl(DataSource dataSource, AsyncHandler asyncHandler) {
        super();
        this.dataSource = dataSource;
        this.asyncHandler = asyncHandler;
    }
    @Override
    public Executable<Chat> insert(Chat chat) {
        log.info("MYSQL: INSERTING A NEW CHAT");
        log.info(chat.getChatType());
        log.info(chat.getSenderId());
        log.info(chat.getReceiverId());
        log.info(chat.getContent());
        log.info(chat.getSentTime());
        return connection -> {
            Future<Chat> future = Future.future();
            Future<Void> temp = Future.future();
            asyncHandler.run(
                    () -> {
                        Object[] params = {chat.getChatType(), chat.getSenderId(), chat.getReceiverId(), chat.getContent(), chat.getSentTime()};
                        try {
                            boolean isSuccess = executeWithParams(
                                    temp, connection.unwrap(), INSERT_CHAT_STATEMENT, params, "insertChat");
                            if(isSuccess){

                                future.complete(chat);
                            }else{
                                future.fail("Wrong Insert Statement");
                            }
                        } catch (SQLException e) {
                            log.error(e);
                            future.fail(e);
                        }
                    });
            return future;
        };
    }

    @Override
    public Future<List<Chat>> listChatByMember(long UserSendID, long UserReceiveID) {
        log.info("MYSQL: SELECT LIST CHAT WITH ANOTHER USER");
        Future<List<Chat>> future = Future.future();
        asyncHandler.run(
                () -> {
                    Object[] params = {UserSendID, UserReceiveID, UserReceiveID, UserSendID};
                    queryEntity(
                            "queryListChat",
                            future,
                            LIST_CHAT_BY_MEMBER,
                            params,
                            this::mapListChat,
                            dataSource::getConnection,
                            false);
                });

        return future;
    }
    private List<Chat> mapListChat(ResultSet resultSet) throws Exception {
        List<Chat> Chats = new ArrayList<>() ;

        log.info(resultSet);

        while (resultSet.next()) {
            Chat chat = new Chat();
            EntityMapper.getInstance().loadResultSetIntoObject(resultSet, chat);
            Chats.add(chat);
        }

        return Chats;
    }
}
