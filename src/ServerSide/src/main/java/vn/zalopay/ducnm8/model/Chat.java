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
    long ChatID;

    @DBTable(columnName = "mode")
    int Mode;

    @DBTable(columnName = "user_send_id")
    long UserSendID;

    @DBTable(columnName = "user_receive_id")
    long UserReceiveID;

    @DBTable(columnName =  "content")
    String Content;

    @DBTable(columnName = "sent_time")
    Long SentTime;

    String FormatTime;
}
