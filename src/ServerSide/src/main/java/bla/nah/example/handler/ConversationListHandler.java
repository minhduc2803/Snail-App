package bla.nah.example.handler;

import bla.nah.example.cache.ConversationListCache;
import bla.nah.example.cache.UserCache;
import bla.nah.example.constant.Status;
import bla.nah.example.da.ConversationMemberDA;
import bla.nah.example.da.Transaction;
import bla.nah.example.da.TransactionProvider;
import bla.nah.example.da.UserDA;
import bla.nah.example.entity.request.BaseRequest;
import bla.nah.example.entity.request.ConversationListRequest;
import bla.nah.example.entity.request.EchoRequest;
import bla.nah.example.entity.request.LoginRequest;
import bla.nah.example.entity.response.BaseResponse;
import bla.nah.example.entity.response.EchoResponse;
import bla.nah.example.entity.response.LoginResponse;
import bla.nah.example.model.ConversationMember;
import bla.nah.example.model.User;
import bla.nah.example.utils.JsonProtoUtils;
import bla.nah.example.utils.Tracker;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Future;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class ConversationListHandler extends BaseHandler{

    private static final String METRIC = "ConversationListHandler";
    private final ConversationListCache conversationListCache;
    private final ConversationMemberDA conversationMemberDA;
    private final TransactionProvider transactionProvider;

    public ConversationListHandler(
            ConversationMemberDA conversationMemberDA, ConversationListCache conversationListCache, TransactionProvider transactionProvider) {
        this.conversationListCache = conversationListCache;
        this.conversationMemberDA = conversationMemberDA;
        this.transactionProvider = transactionProvider;
    }

    @Override
    public Future<BaseResponse> handle(BaseRequest baseRequest) {
        log.info("IN CONVERSATION LIST HANDLER");
        Tracker.TrackerBuilder tracker =
                Tracker.builder().metricName(METRIC).startTime(System.currentTimeMillis());
        Future<List<ConversationMember>> future = Future.future();

        BaseResponse.BaseResponseBuilder response = BaseResponse.builder();

        try {
            ConversationListRequest request = JsonProtoUtils.parseGson(baseRequest.getPostData(), ConversationListRequest.class);
            Transaction transaction = transactionProvider.newTransaction();
            transaction
                    .begin()
                    .compose(next -> {
                        return conversationMemberDA.listConversationByMember(request.getUserID());
                    })
                    .setHandler(
                            rs -> {
                                if (rs.succeeded()) {

                                    List<ConversationMember> conversationMembers= rs.result();
                                    response.data(conversationMembers)
                                            .status(HttpResponseStatus.OK.code());
                                    future.complete();
                                } else {
                                    response.message("")
                                            .status(HttpResponseStatus.BAD_REQUEST.code());
                                    future.complete(null);
                                }
                                transaction.close();
                                tracker.step("handle").code("SUCCESS").build().record();
                            });

        } catch (Exception e) {
            response.message("")
                    .status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
        }
        return future.compose(u -> Future.succeededFuture(response.build()));
    }
}
