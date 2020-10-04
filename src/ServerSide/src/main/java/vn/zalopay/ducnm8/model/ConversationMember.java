package vn.zalopay.ducnm8.model;

import vn.zalopay.ducnm8.common.mapper.DBTable;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConversationMember {
    @DBTable(columnName = "ConversationMemberID")
    int ConversationMemberID;

    @DBTable(columnName = "ConversationID")
    int ConversationID;

    @DBTable(columnName = "MemberID")
    int MemberID;

    @DBTable(columnName = "NotSeenChat")
    String NotSeenChat;

    @DBTable(columnName =  "Fullname")
    String Fullname;

}
