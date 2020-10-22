package vn.zalopay.ducnm8.model;

import vn.zalopay.ducnm8.common.mapper.DBTable;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {
  @DBTable(columnName = "id")
  long id;

  @DBTable(columnName = "last_time_chat")
  long lastTimeChat;

}
