package vn.zalopay.ducnm8.da;

import vn.zalopay.ducnm8.model.ConversationMember;
import io.vertx.core.Future;

import java.util.List;

public interface ConversationMemberDA {
    Executable<ConversationMember> insert(ConversationMember conversationMember);

    Future<List<ConversationMember>> listConversationByMember(int MemberID);
}
