package bla.nah.example.cache;

import bla.nah.example.model.ConversationMember;
import io.vertx.core.Future;

import java.util.List;
import bla.nah.example.model.ConversationMember;
import bla.nah.example.utils.AsyncHandler;
import io.vertx.core.Future;
import lombok.Builder;
import org.redisson.api.RMap;

@Builder
public class ConversationListCacheImpl implements ConversationListCache{
    private final RedisCache redisCache;
    private final AsyncHandler asyncHandler;
    @Override
    public Future<List<ConversationMember>> set(List<ConversationMember> conversationMembers) {
        return null;
    }
}
