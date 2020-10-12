package vn.zalopay.ducnm8.model;

import vn.zalopay.ducnm8.common.mapper.DBTable;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {
    @DBTable(columnName = "id")
    long id;

    @DBTable(columnName = "sender_id")
    long senderId;

    @DBTable(columnName = "receiverId")
    long receiverId;

    @DBTable(columnName = "amount")
    long amount;

    @DBTable(columnName = "message")
    String message;

    @DBTable(columnName = "transfer_time")
    long transferTime;

}
