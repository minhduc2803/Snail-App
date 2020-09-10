package bla.nah.example.da;

import bla.nah.example.model.Conversation;
import bla.nah.example.model.ConversationMember;
import bla.nah.example.model.User;
import io.vertx.core.Future;

import java.util.List;

public interface ConversationMemberDA {
    Executable<ConversationMember> insert(ConversationMember conversationMember);

    Future<List<ConversationMember>> listConversationByMember(int MemberID);
}
