package vn.zalopay.ducnm8.handler;

import vn.zalopay.ducnm8.cache.ChatListCache;
import vn.zalopay.ducnm8.da.*;
import vn.zalopay.ducnm8.da.interact.ChatListDA;
import vn.zalopay.ducnm8.entity.request.*;
import vn.zalopay.ducnm8.entity.response.BaseResponse;
import vn.zalopay.ducnm8.utils.JWTUtils;
import vn.zalopay.ducnm8.utils.JsonProtoUtils;
import vn.zalopay.ducnm8.utils.Tracker;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Future;
import io.vertx.core.http.HttpHeaders;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ChatListHandler extends BaseHandler {

    private static final String METRIC = "ChatListHandler";
    private final ChatListCache chatListCache;
    private final ChatListDA chatListDA;
    private final TransactionProvider transactionProvider;

    public ChatListHandler(
      ChatListDA chatListDA, ChatListCache chatListCache, TransactionProvider transactionProvider) {
        this.chatListCache = chatListCache;
        this.chatListDA = chatListDA;
        this.transactionProvider = transactionProvider;
    }

    @Override
    public Future<BaseResponse> handle(BaseRequest baseRequest) {


        Future<BaseResponse> future = Future.future();
        BaseResponse.BaseResponseBuilder response = BaseResponse.builder();
        try {
            ChatListRequest request = JsonProtoUtils.parseGson(baseRequest.getPostData(), ChatListRequest.class);

            String token = baseRequest.getHeaders().get(HttpHeaders.AUTHORIZATION).substring("Bearer ".length()).trim();

            if (token == null) {
                response.message("JWT token is missing")
                  .status(HttpResponseStatus.UNAUTHORIZED.code());
                future.complete(response.build());
                log.warn("get chat list failed ~ JWT token is missing");
                return future;
            }

            try {
                Long id = JWTUtils.authenticate(token);

                log.info("get chat list between {} and {}", id, request.getPartnerId());

                chatListDA.listChatByMember(id, request.getPartnerId())
                  .setHandler(chatListRes -> {
                      if (chatListRes.succeeded()) {
                          response.data(chatListRes.result())
                            .status(HttpResponseStatus.OK.code());

                      } else {
                          response.message("Cannot get a chat list")
                            .status(HttpResponseStatus.BAD_REQUEST.code());
                          log.warn("cannot get a chat list between {} and  {} ~ fail to do a SQL select", id, request.getPartnerId());
                      }
                      future.complete(response.build());
                  });
            } catch (Exception e) {
                response.message("JWT token is invalid")
                  .status(HttpResponseStatus.UNAUTHORIZED.code());
                future.complete(response.build());

                log.info("get a chat list failed ~ JWT token is invalid");
            }

        } catch (Exception e) {
            log.error("internal server error, cause: {}", e.getMessage());

            response.message("Server has an error")
              .status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
            future.complete(response.build());
        }
        return future;
    }
}
