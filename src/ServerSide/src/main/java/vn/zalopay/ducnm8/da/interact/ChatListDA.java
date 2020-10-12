package vn.zalopay.ducnm8.da.interact;

import io.vertx.core.Future;
import vn.zalopay.ducnm8.da.Executable;
import vn.zalopay.ducnm8.model.Chat;

import java.util.List;

public interface ChatListDA {
    Executable<Chat> insert(Chat chat);

    Future<List<Chat>> listChatByMember(long UserSendID, long UserReceiveID);
}
