package vn.zalopay.ducnm8.da;

import io.vertx.core.Future;
import vn.zalopay.ducnm8.model.Conversation;

public interface ConversationDA {
    Executable<Conversation> insert(Conversation conversation);

    Future<Conversation> selectUserByUserStartId(int id);
}
