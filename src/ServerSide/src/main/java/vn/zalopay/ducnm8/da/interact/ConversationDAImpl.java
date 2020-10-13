package vn.zalopay.ducnm8.da.interact;

import io.vertx.core.Future;
import org.apache.logging.log4j.Logger;
import vn.zalopay.ducnm8.da.BaseTransactionDA;
import vn.zalopay.ducnm8.da.Executable;
import vn.zalopay.ducnm8.model.Conversation;
import vn.zalopay.ducnm8.utils.AsyncHandler;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ConversationDAImpl extends BaseTransactionDA implements ConversationDA{
    private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(ConversationDAImpl.class);
    private final DataSource dataSource;
    private final AsyncHandler asyncHandler;

    private static final String INSERT_CONVERSATION_STATEMENT = "INSERT INTO Conversation(`id`, `last_time_chat`) VALUES (?, ?);";

    public ConversationDAImpl(DataSource dataSource, AsyncHandler asyncHandler) {
        super();
        this.dataSource = dataSource;
        this.asyncHandler = asyncHandler;
    }

    @Override
    public Executable<Long> insert(Conversation conversation) {
        log.info("MYSQL: INSERTING A NEW CONVERSATION");
        return connection -> {
            Future<Long> future = Future.future();
            asyncHandler.run(
                    () -> {
                        Object[] params = {conversation.getId(), conversation.getLastTimeChat()};
                        try {
                            executeWithParams(
                                    future, connection.unwrap(), INSERT_CONVERSATION_STATEMENT, params, "insertChat");

                        } catch (SQLException e) {
                            future.fail(e);
                        }
                    });
            return future;
        };
    }

}
