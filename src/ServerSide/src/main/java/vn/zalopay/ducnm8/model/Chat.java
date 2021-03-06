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

  @DBTable(columnName = "sender_id")
  long senderId;

  @DBTable(columnName = "receiver_id")
  long receiverId;

  @DBTable(columnName = "chat_type")
  int chatType;

  @DBTable(columnName = "content")
  String content;

  @DBTable(columnName = "sent_time")
  Long sentTime;

}
