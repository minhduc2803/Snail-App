package vn.zalopay.ducnm8.model;

import vn.zalopay.ducnm8.common.mapper.DBTable;
import lombok.*;

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    @DBTable(columnName = "id")
    long id;

    @DBTable(columnName = "chat_type")
    int chatType;

    @DBTable(columnName = "user_send_id")
    long senderId;

    @DBTable(columnName = "user_receive_id")
    long receiverId;

    @DBTable(columnName =  "content")
    String content;

    @DBTable(columnName = "sent_time")
    Long sentTime;

}
