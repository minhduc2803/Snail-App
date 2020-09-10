package bla.nah.example.cache;

import bla.nah.example.model.Chat;
import bla.nah.example.model.ConversationMember;
import bla.nah.example.model.User;
import io.vertx.core.Future;

import java.util.List;

public interface ChatListCache {
    Future<List<Chat>> set(List<Chat> chats);
}
