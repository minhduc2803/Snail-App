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
    long id;

    @DBTable(columnName = "user_name")
    String username;

    @DBTable(columnName = "full_name")
    String fullName;

    @DBTable(columnName = "password")
    String password;
}
