package vn.zalopay.ducnm8.model;

import vn.zalopay.ducnm8.common.mapper.DBTable;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConversationMember {
  @DBTable(columnName = "id")
  long id;

  @DBTable(columnName = "conversation_id")
  long conversationId;

  @DBTable(columnName = "member_id")
  long MemberID;

  @DBTable(columnName = "number_unseen_chat")
  int numberUnseenChat;

  @DBTable(columnName = "conversation_title")
  String conversationTitle;

}
