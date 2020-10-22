package vn.zalopay.ducnm8.da.interact;

import io.vertx.core.Future;
import vn.zalopay.ducnm8.da.Executable;
import vn.zalopay.ducnm8.model.Conversation;

public interface ConversationDA {
  Executable<Conversation> insert(Conversation conversation);

}
