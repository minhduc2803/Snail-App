package vn.zalopay.ducnm8.entity.request;

import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
public class ChatListRequest {
  long partnerId;
  long offset;
}
