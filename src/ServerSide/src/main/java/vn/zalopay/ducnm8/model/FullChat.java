package vn.zalopay.ducnm8.model;

import vn.zalopay.ducnm8.common.mapper.DBTable;
import lombok.*;

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FullChat {
    @DBTable(columnName = "id")
    long id;

    @DBTable(columnName = "conversation_id")
    long conversationId;

    @DBTable(columnName = "sender_id")
    long senderId;

    @DBTable(columnName = "chat_type")
    int chatType;

    @DBTable(columnName =  "content")
    String content;

    @DBTable(columnName = "sent_time")
    Long sentTime;

    @DBTable(columnName = "number_unseen_chat")
    int numberUnseenChat;

    @DBTable(columnName =  "conversation_title")
    String conversationTitle;

}
