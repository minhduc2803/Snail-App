package bla.nah.example.da;

import bla.nah.example.model.Chat;
import bla.nah.example.model.Conversation;
import bla.nah.example.model.User;
import io.vertx.core.Future;

import java.util.List;

public interface ChatListDA {
    Executable<Chat> insert(Chat chat);

    Future<List<Chat>> listChatByMember(int UserSendID, int UserReceiveID);
}
