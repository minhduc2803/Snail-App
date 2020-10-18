package vn.zalopay.ducnm8.entity.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import vn.zalopay.ducnm8.model.Balance;
import vn.zalopay.ducnm8.model.Chat;
import vn.zalopay.ducnm8.model.TransferHistory;

@Builder
@Setter
@Getter
public class WebsocketResponse {
    int responseType;
    Object data;

}
