package vn.zalopay.ducnm8.handler;

import vn.zalopay.ducnm8.cache.ConversationListCache;
import vn.zalopay.ducnm8.da.interact.ConversationMemberDA;
import vn.zalopay.ducnm8.da.Transaction;
import vn.zalopay.ducnm8.da.TransactionProvider;
import vn.zalopay.ducnm8.entity.request.BaseRequest;
import vn.zalopay.ducnm8.entity.request.ConversationListRequest;
import vn.zalopay.ducnm8.entity.response.BaseResponse;
import vn.zalopay.ducnm8.model.ConversationMember;
import vn.zalopay.ducnm8.utils.JsonProtoUtils;
import vn.zalopay.ducnm8.utils.Tracker;
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
