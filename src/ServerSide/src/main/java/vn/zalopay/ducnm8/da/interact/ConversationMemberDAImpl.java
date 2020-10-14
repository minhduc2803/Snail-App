package vn.zalopay.ducnm8.da.interact;

import io.vertx.core.Future;
import org.apache.logging.log4j.Logger;
import vn.zalopay.ducnm8.common.mapper.EntityMapper;
import vn.zalopay.ducnm8.da.BaseTransactionDA;
import vn.zalopay.ducnm8.da.Executable;
import vn.zalopay.ducnm8.model.ConversationMember;
import vn.zalopay.ducnm8.utils.AsyncHandler;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConversationMemberDAImpl extends BaseTransactionDA implements ConversationMemberDA{
    private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(ConversationMemberDAImpl.class);
    private final DataSource dataSource;
    private final AsyncHandler asyncHandler;

    private static final String INSERT_CONVERSATION_MEMBER =
            "INSERT INTO conversation_member(`conversationId`, `member_id`,`number_unseen_chat`,`conversation_title`) VALUES (?, ?, ?, ?);";
    private static final String LIST_MEMBER_OF_A_CONVERSATION =
            "SELECT * FROM conversation_member WHERE conversation_id = ?";

    public ConversationMemberDAImpl(DataSource dataSource, AsyncHandler asyncHandler) {
        super();
        this.dataSource = dataSource;
        this.asyncHandler = asyncHandler;
    }
    @Override
    public Executable<ConversationMember> insert(ConversationMember conversationMember) {
        log.info("MYSQL: INSERTING A NEW CHAT MEMBER");
        return connection -> {
            Future<ConversationMember> future = Future.future();
            asyncHandler.run(
                    () -> {
                        Object[] params = {conversationMember.getConversationId()};
                        try {
                            long id = executeWithParamsAndGetId(connection.unwrap(), INSERT_CONVERSATION_MEMBER, params, "insertConversationMember");
                            conversationMember.setId(id);
                            future.complete(conversationMember);
                        } catch (SQLException e) {
                            future.fail(e);
                        }
                    });
            return future;
        };
    }

    @Override
    public Future<List<ConversationMember>> listMember(long conversation_id) {
        log.info("select list member of a conversation: {}",conversation_id);
        Future<List<ConversationMember>> future = Future.future();
        asyncHandler.run(
                () -> {
                    Object[] params = {conversation_id};
                    queryEntity(
                            "queryListConversation",
                            future,
                            LIST_MEMBER_OF_A_CONVERSATION,
                            params,
                            this::mapListMembers,
                            dataSource::getConnection,
                            false);
                });

        return future;
    }
    private List<ConversationMember> mapListMembers(ResultSet resultSet) throws Exception {
        List<ConversationMember> conversationMembers = new ArrayList<>() ;

        while (resultSet.next()) {
            ConversationMember conversationMember = new ConversationMember();
            EntityMapper.getInstance().loadResultSetIntoObject(resultSet, conversationMember);
            conversationMembers.add(conversationMember);
        }

        return conversationMembers;
    }
}
