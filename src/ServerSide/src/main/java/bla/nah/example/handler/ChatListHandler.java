package bla.nah.example.handler;

import bla.nah.example.cache.ChatListCache;
import bla.nah.example.cache.UserCache;
import bla.nah.example.constant.Status;
import bla.nah.example.da.*;
import bla.nah.example.entity.request.*;
import bla.nah.example.entity.response.BaseResponse;
import bla.nah.example.model.Chat;
import bla.nah.example.model.ConversationMember;
import bla.nah.example.utils.JWTUtils;
import bla.nah.example.utils.JsonProtoUtils;
import bla.nah.example.utils.Tracker;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Future;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.auth.jwt.JWTAuth;
import lombok.extern.log4j.Log4j2;

import java.util.List;

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
            log.info(baseRequest.getRequestPath());
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
