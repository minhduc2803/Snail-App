package vn.zalopay.ducnm8.da.interact;

import io.vertx.core.Future;
import vn.zalopay.ducnm8.da.Executable;
import vn.zalopay.ducnm8.model.ConversationMember;

import java.util.List;

public interface ConversationMemberDA {
    Executable<Long> insert(ConversationMember conversationMember);

    Future<List<ConversationMember>> listMember(long conversationId);
}
