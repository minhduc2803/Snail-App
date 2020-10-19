package vn.zalopay.ducnm8.handler;

import vn.zalopay.ducnm8.constant.Status;
import vn.zalopay.ducnm8.entity.request.BaseRequest;
import vn.zalopay.ducnm8.entity.request.EchoRequest;
import vn.zalopay.ducnm8.entity.response.BaseResponse;
import vn.zalopay.ducnm8.entity.response.EchoResponse;
import vn.zalopay.ducnm8.utils.JsonProtoUtils;
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
