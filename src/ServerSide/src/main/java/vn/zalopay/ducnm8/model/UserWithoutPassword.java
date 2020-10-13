package vn.zalopay.ducnm8.model;

import vn.zalopay.ducnm8.common.mapper.DBTable;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithoutPassword {
    @DBTable(columnName = "id")
    int userId;

    @DBTable(columnName = "user_name")
    String username;

    @DBTable(columnName = "full_name")
    String fullName;

}
