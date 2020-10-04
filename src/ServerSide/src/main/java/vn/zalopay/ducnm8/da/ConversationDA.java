package vn.zalopay.ducnm8.da;

import vn.zalopay.ducnm8.model.Conversation;
import io.vertx.core.Future;

public interface ConversationDA {
    Executable<Conversation> insert(Conversation conversation);

    Future<Conversation> selectUserByUserStartId(int id);
}
