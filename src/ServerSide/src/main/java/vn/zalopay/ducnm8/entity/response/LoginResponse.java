package vn.zalopay.ducnm8.entity.response;

import lombok.Builder;

@Builder
public class LoginResponse {
    String token;
    int UserID;
    String Fullname;
}
