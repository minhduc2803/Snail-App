package vn.zalopay.ducnm8.da;

import vn.zalopay.ducnm8.model.Chat;
import io.vertx.core.Future;

import java.util.List;

public interface ChatListDA {
    Executable<Chat> insert(Chat chat);

    Future<List<Chat>> listChatByMember(int UserSendID, int UserReceiveID);
}
