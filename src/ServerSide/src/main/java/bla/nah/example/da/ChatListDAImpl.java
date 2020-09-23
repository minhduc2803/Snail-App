package bla.nah.example.da;

import bla.nah.example.common.mapper.EntityMapper;
import bla.nah.example.model.Chat;
import bla.nah.example.model.ConversationMember;
import bla.nah.example.model.User;
import bla.nah.example.utils.AsyncHandler;
import io.vertx.core.Future;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ChatListDAImpl extends BaseTransactionDA implements ChatListDA{
    private final DataSource dataSource;
    private final AsyncHandler asyncHandler;

    private static final String INSERT_CHAT_STATEMENT = "INSERT INTO chat (`mode`, `user_send_id`, `user_receive_id`, `content`, `sent_time`) VALUES (?, ?, ?, ?, ?);";
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
        log.info(chat.getMode());
        log.info(chat.getUserSendID());
        log.info(chat.getUserReceiveID());
        log.info(chat.getContent());
        log.info(chat.getSentTime());
        return connection -> {
            Future<Chat> future = Future.future();
            Future<Void> temp = Future.future();
            asyncHandler.run(
                    () -> {
                        Object[] params = {chat.getMode(), chat.getUserSendID(), chat.getUserReceiveID(), chat.getContent(), chat.getSentTime()};
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
    public Future<List<Chat>> listChatByMember(int UserSendID, int UserReceiveID) {
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
