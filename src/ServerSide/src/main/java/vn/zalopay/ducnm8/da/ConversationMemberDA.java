package vn.zalopay.ducnm8.da;

import io.vertx.core.Future;
import vn.zalopay.ducnm8.model.ConversationMember;

import java.util.List;

public interface ConversationMemberDA {
    Executable<ConversationMember> insert(ConversationMember conversationMember);

    Future<List<ConversationMember>> listConversationByMember(int MemberID);
}
