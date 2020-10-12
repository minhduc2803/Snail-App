package vn.zalopay.ducnm8.model;

import vn.zalopay.ducnm8.common.mapper.DBTable;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @DBTable(columnName = "id")
    long id;

    @DBTable(columnName = "notification_type")
    long notificationType;

    @DBTable(columnName = "user_id")
    long userId;

    @DBTable(columnName = "partner_id")
    long partnerId;

    @DBTable(columnName = "amount")
    long amount;

    @DBTable(columnName = "message")
    String message;

    @DBTable(columnName = "seen")
    boolean seen;

    @DBTable(columnName = "user_name")
    String userName;

    @DBTable(columnName = "full_name")
    String fullName;

}
