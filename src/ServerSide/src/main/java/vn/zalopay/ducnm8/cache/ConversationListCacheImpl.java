package vn.zalopay.ducnm8.cache;

import vn.zalopay.ducnm8.model.ConversationMember;
import io.vertx.core.Future;

import java.util.List;

import vn.zalopay.ducnm8.utils.AsyncHandler;
import lombok.Builder;

@Builder
public class ConversationListCacheImpl implements ConversationListCache {
  private final RedisCache redisCache;
  private final AsyncHandler asyncHandler;

  @Override
  public Future<List<ConversationMember>> set(List<ConversationMember> conversationMembers) {
    return null;
  }
}
