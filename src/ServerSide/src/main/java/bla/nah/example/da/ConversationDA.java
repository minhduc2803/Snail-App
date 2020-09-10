package bla.nah.example.da;

import bla.nah.example.model.Conversation;
import bla.nah.example.model.User;
import io.vertx.core.Future;

public interface ConversationDA {
    Executable<Conversation> insert(Conversation conversation);

    Future<Conversation> selectUserByUserStartId(int id);
}
