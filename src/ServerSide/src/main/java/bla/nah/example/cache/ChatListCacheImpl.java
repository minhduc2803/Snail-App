package bla.nah.example.cache;

import bla.nah.example.model.Chat;
import bla.nah.example.model.ConversationMember;
import bla.nah.example.utils.AsyncHandler;
import io.vertx.core.Future;
import lombok.Builder;
import java.util.List;

@Builder
public class ChatListCacheImpl implements ChatListCache{
    private final RedisCache redisCache;
    private final AsyncHandler asyncHandler;
    @Override
    public Future<List<Chat>> set(List<Chat> chats) {
        return null;
    }
}
