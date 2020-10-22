package vn.zalopay.ducnm8.model;

import vn.zalopay.ducnm8.common.mapper.DBTable;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
  @DBTable(columnName = "id")
  long id;

  @DBTable(columnName = "user_name")
  String username;

  @DBTable(columnName = "full_name")
  String fullName;

  @DBTable(columnName = "password")
  String password;

  @DBTable(columnName = "balance")
  long balance;

  @DBTable(columnName = "last_time_update_balance")
  long lastTimeUpdateBalance;

  @DBTable(columnName = "number_notification")
  int numberNotification;
}
