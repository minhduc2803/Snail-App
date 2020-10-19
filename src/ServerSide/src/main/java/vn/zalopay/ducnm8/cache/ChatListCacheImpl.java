package vn.zalopay.ducnm8.cache;

import vn.zalopay.ducnm8.model.Chat;
import vn.zalopay.ducnm8.utils.AsyncHandler;
import io.vertx.core.Future;
import lombok.Builder;

import java.util.List;

@Builder
public class ChatListCacheImpl implements ChatListCache {
    private final RedisCache redisCache;
    private final AsyncHandler asyncHandler;

    @Override
    public Future<List<Chat>> set(List<Chat> chats) {
        return null;
    }
}
