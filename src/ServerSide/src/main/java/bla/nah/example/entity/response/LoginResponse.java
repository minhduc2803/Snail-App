package bla.nah.example.entity.response;

import lombok.Builder;

@Builder
public class LoginResponse {
    String token;
    int UserID;
    String Fullname;
}
