package bla.nah.example.handler;

import bla.nah.example.constant.Status;
import bla.nah.example.entity.request.BaseRequest;
import bla.nah.example.entity.request.EchoRequest;
import bla.nah.example.entity.response.BaseResponse;
import bla.nah.example.entity.response.EchoResponse;
import bla.nah.example.utils.JsonProtoUtils;
import io.vertx.core.Future;

public class EchoHandler extends BaseHandler {

  @Override
  public Future<BaseResponse> handle(BaseRequest baseRequest) {
    BaseResponse.BaseResponseBuilder response = BaseResponse.builder();
    Status status = Status.SUCCESS;
    try {
      EchoRequest request = JsonProtoUtils.parseGson(baseRequest.getPostData(), EchoRequest.class);
      response.data(EchoResponse.builder().message("echo: " + request.getMessage()).build());
    } catch (Exception e) {
      status = Status.INVALID_ARGUMENT;
    }
    return Future.succeededFuture(response.status(status.ordinal()).build());
  }
}
