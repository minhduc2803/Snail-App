package bla.nah.example.entity.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterRequest {
    String Username;
    String Fullname;
    String Password;
}
