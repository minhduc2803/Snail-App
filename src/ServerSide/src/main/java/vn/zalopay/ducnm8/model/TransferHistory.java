package vn.zalopay.ducnm8.model;

import vn.zalopay.ducnm8.common.mapper.DBTable;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransferHistory {
    @DBTable(columnName = "id")
    long id;

    @DBTable(columnName = "transfer_id")
    long transferId;

    @DBTable(columnName = "user_id")
    long userId;

    @DBTable(columnName = "partner_id")
    long partnerId;

    @DBTable(columnName = "transfer_type")
    long transferType;

    @DBTable(columnName = "balance")
    long balance;

    @DBTable(columnName = "amount")
    long amount;

    @DBTable(columnName = "message")
    String message;

    @DBTable(columnName = "transfer_time")
    long transferTime;

    @DBTable(columnName = "user_name")
    long userName;

    @DBTable(columnName = "full_name")
    long fullName;

}
