package vn.zalopay.ducnm8.cache;

import vn.zalopay.ducnm8.model.ConversationMember;
import io.vertx.core.Future;

import java.util.List;

public interface ConversationListCache {
    Future<List<ConversationMember>> set(List<ConversationMember> conversationMembers);
}
