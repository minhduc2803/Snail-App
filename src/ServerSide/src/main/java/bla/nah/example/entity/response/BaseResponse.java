package bla.nah.example.entity.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BaseResponse {
    int status;
    String message;
    Object data;
}
