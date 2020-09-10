package bla.nah.example.model;

import bla.nah.example.common.mapper.DBTable;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @DBTable(columnName = "UserID")
  int UserID;

  @DBTable(columnName = "Username")
  String Username;

  @DBTable(columnName = "Fullname")
  String Fullname;

  @DBTable(columnName = "Password")
  String Password;

}
