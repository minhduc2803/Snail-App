package vn.zalopay.ducnm8.entity.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class LoginResponse {
    String token;
    long UserID;
    String Fullname;
}
