package bla.nah.example.model;

import bla.nah.example.common.mapper.DBTable;
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
