package vn.zalopay.ducnm8.cache;

import vn.zalopay.ducnm8.model.Chat;
import io.vertx.core.Future;

import java.util.List;

public interface ChatListCache {
    Future<List<Chat>> set(List<Chat> chats);
}
