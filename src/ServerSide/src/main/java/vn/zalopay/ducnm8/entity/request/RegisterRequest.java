package vn.zalopay.ducnm8.entity.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterRequest {
  String username;
  String fullName;
  String password;
}
