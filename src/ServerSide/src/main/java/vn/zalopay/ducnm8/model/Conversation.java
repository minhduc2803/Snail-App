package vn.zalopay.ducnm8.model;

import vn.zalopay.ducnm8.common.mapper.DBTable;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {
    @DBTable(columnName = "ConversationID")
    int ConversationID;

    @DBTable(columnName = "UserStartID")
    int UserStartID;

    @DBTable(columnName = "Mode")
    int Mode;

    @DBTable(columnName = "Name")
    String Name;

}
