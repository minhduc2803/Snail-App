package vn.zalopay.ducnm8.model;

import vn.zalopay.ducnm8.common.mapper.DBTable;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Balance {
    @DBTable(columnName = "balance")
    long balance;

    @DBTable(columnName = "last_time_update_balance")
    long lastTimeUpdate;

    @DBTable(columnName = "unread_notification")
    int unReadNotification;
}
