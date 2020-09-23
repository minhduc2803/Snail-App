package bla.nah.example.model;

import bla.nah.example.common.mapper.DBTable;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithoutPassword {
    @DBTable(columnName = "id")
    int UserID;

    @DBTable(columnName = "user_name")
    String Username;

    @DBTable(columnName = "full_name")
    String Fullname;

}
