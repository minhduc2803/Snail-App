package vn.zalopay.ducnm8.entity.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BaseResponse {
    int status;
    String message;
    Object data;
    LoginResponse response;
}
