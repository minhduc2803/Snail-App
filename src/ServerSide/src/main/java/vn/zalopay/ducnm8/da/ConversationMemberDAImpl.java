package vn.zalopay.ducnm8.da;

import io.vertx.core.Future;
import org.apache.logging.log4j.Logger;
import vn.zalopay.ducnm8.common.mapper.EntityMapper;
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

    private static final String INSERT_CONVERSATION_MEMBER = "INSERT INTO `chatapp`.`ConversationMember` (`ConversationID`, `MemberID`) VALUES (?, ?);";
    private static final String LIST_CONVERSATION_BY_MEMBER =
            "SELECT C2.ConversationMemberID, C2.ConversationID, C2.MemberID, C2.NotSeenChat, U.Fullname\n" +
            "FROM \n" +
            "(chatapp.ConversationMember as C1\n" +
            "INNER JOIN chatapp.ConversationMember as C2\n" +
            "On C1.ConversationID = C2.ConversationID and C1.MemberID = ? and C2.MemberID != ? )\n" +
            "INNER JOIN chatapp.User as U\n" +
            "On C2.MemberID = U.UserID;";

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
            Future<Void> temp = Future.future();
            asyncHandler.run(
                    () -> {
                        Object[] params = {conversationMember.getConversationID(), conversationMember.getMemberID()};
                        try {
                            boolean isSuccess = executeWithParams(
                                    temp, connection.unwrap(), INSERT_CONVERSATION_MEMBER, params, "insertConversationMember");
                            if(isSuccess){
                                future.complete(conversationMember);
                            }else{
                                future.fail("Wrong Insert Statement");
                            }
                        } catch (SQLException e) {
                            future.fail(e);
                        }
                    });
            return future;
        };
    }

    @Override
    public Future<List<ConversationMember>> listConversationByMember(int MemberID) {
        log.info("MYSQL: SELECTING ALL CONVERSATION OF " + String.valueOf(MemberID));
        Future<List<ConversationMember>> future = Future.future();
        asyncHandler.run(
                () -> {
                    Object[] params = {MemberID, MemberID};
                    queryEntity(
                            "queryListConversation",
                            future,
                            LIST_CONVERSATION_BY_MEMBER,
                            params,
                            this::mapListConversations,
                            dataSource::getConnection,
                            false);
                });

        return future;
    }
    private List<ConversationMember> mapListConversations(ResultSet resultSet) throws Exception {
        List<ConversationMember> conversationMembers = new ArrayList<>() ;

        while (resultSet.next()) {
            ConversationMember conversationMember = new ConversationMember();
            EntityMapper.getInstance().loadResultSetIntoObject(resultSet, conversationMember);
            conversationMembers.add(conversationMember);
        }

        return conversationMembers;
    }
}
