package vn.zalopay.ducnm8.model;

import vn.zalopay.ducnm8.common.mapper.DBTable;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @DBTable(columnName = "id")
  int UserID;

  @DBTable(columnName = "user_name")
  String Username;

  @DBTable(columnName = "full_name")
  String Fullname;

  @DBTable(columnName = "password")
  String Password;

}
