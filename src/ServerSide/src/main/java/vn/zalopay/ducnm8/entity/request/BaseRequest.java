package vn.zalopay.ducnm8.entity.request;

import io.vertx.core.MultiMap;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BaseRequest {
  private final MultiMap params;
  private final MultiMap headers;
  private final String userIP;
  private final String requestPath;
  private final String postData;
}
