package bla.nah.example.model;

import bla.nah.example.common.mapper.DBTable;
import lombok.*;

import java.sql.Timestamp;

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    @DBTable(columnName = "ChatID")
    int ChatID;

    @DBTable(columnName = "Mode")
    int Mode;

    @DBTable(columnName = "UserSendID")
    int UserSendID;

    @DBTable(columnName = "UserReceiveID")
    int UserReceiveID;

    @DBTable(columnName =  "Content")
    String Content;

    @DBTable(columnName = "SentTime")
    Long SentTime;

    String FormatTime;
}
