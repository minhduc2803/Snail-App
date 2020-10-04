package vn.zalopay.ducnm8.handler;

import vn.zalopay.ducnm8.cache.ChatListCache;
import vn.zalopay.ducnm8.da.*;
import vn.zalopay.ducnm8.entity.request.*;
import vn.zalopay.ducnm8.entity.response.BaseResponse;
import vn.zalopay.ducnm8.utils.JWTUtils;
import vn.zalopay.ducnm8.utils.JsonProtoUtils;
import vn.zalopay.ducnm8.utils.Tracker;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Future;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.auth.jwt.JWTAuth;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ChatListHandler extends BaseHandler{

    private static final String METRIC = "ChatListHandler";
    private final ChatListCache chatListCache;
    private final ChatListDA chatListDA;
    private final TransactionProvider transactionProvider;
    private final JWTAuth jwtAuth;

    public ChatListHandler(
            ChatListDA chatListDA, ChatListCache chatListCache, TransactionProvider transactionProvider, JWTAuth jwtAuth) {
        this.chatListCache = chatListCache;
        this.chatListDA = chatListDA;
        this.transactionProvider = transactionProvider;
        this.jwtAuth = jwtAuth;
    }

    @Override
    public Future<BaseResponse> handle(BaseRequest baseRequest) {

        Tracker.TrackerBuilder tracker =
                Tracker.builder().metricName(METRIC).startTime(System.currentTimeMillis());

        Future<BaseResponse> future = Future.future();
        BaseResponse.BaseResponseBuilder response = BaseResponse.builder();
        try {
            ChatListRequest request = JsonProtoUtils.parseGson(baseRequest.getPostData(), ChatListRequest.class);
            log.info("Hello ChatList Handler");
            log.info(baseRequest.getPostData());
            log.info("UserReceiveID: {}",request.getUserReceiveID());

            String token = baseRequest.getHeaders().get(HttpHeaders.AUTHORIZATION).substring("Bearer ".length()).trim();

            if(token == null){
                response.message("JWT token is missing")
                        .status(HttpResponseStatus.UNAUTHORIZED.code());
                future.complete(response.build());
                return future;
            }

            Future<Integer> UserIDAuth = JWTUtils.authenticate(jwtAuth, token);

            UserIDAuth.setHandler(UserIDRes -> {

                if(UserIDRes.succeeded()){

                    chatListDA.listChatByMember(UserIDRes.result(), request.getUserReceiveID())
                            .setHandler(chatListRes -> {

                                if(chatListRes.succeeded()){
                                    response.data(chatListRes.result())
                                            .status(HttpResponseStatus.OK.code());

                                }else{
                                    response.message("Cannot get a chat list")
                                            .status(HttpResponseStatus.BAD_REQUEST.code());
                                }
                                future.complete(response.build());
                            });

                }else{
                    response.message("JWT token is invalid")
                            .status(HttpResponseStatus.UNAUTHORIZED.code());
                    future.complete(response.build());
                }


            });
        } catch (Exception e) {
            log.error(e);
            response.message("Server has an error")
                    .status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
            future.complete(response.build());
        }
        return future;
    }
}
