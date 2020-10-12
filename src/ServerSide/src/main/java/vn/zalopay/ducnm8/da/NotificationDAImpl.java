package vn.zalopay.ducnm8.da;

import io.vertx.core.Future;
import org.apache.logging.log4j.Logger;
import vn.zalopay.ducnm8.common.mapper.EntityMapper;
import vn.zalopay.ducnm8.model.Notification;
import vn.zalopay.ducnm8.model.TransferHistory;
import vn.zalopay.ducnm8.utils.AsyncHandler;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationDAImpl extends BaseTransactionDA implements NotificationDA{
    private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(AccountDAImpl.class);
    private final DataSource dataSource;
    private final AsyncHandler asyncHandler;
    private static final String INSERT_NOTIFICATION_STATEMENT =
            "INSERT INTO notification (`notification_type`,`user_id`,`partner_id`,`amount`,`message`,`unread`) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_NOTIFICATION_BY_ACCOUNT_ID =
            "SELECT H.transfer_id, H.user_id, H.partner_id, H.transfer_type, H.balance,\n" +
                    "T.amount, T.amount, T.message, T.transfer_time,\n" +
                    "C.user_name, C.full_name\n" +
                    "FROM transfer_history as H\n" +
                    "INNER JOIN transfer as T\n" +
                    "ON H.transfer_id = T.id\n" +
                    "INNER JOIN account as C\n" +
                    "ON H.user_id = C.id\n" +
                    "WHERE H.user_id = ?";
    private static final String UPDATE_SEEN_A_NOTIFICATION =
            "UPDATE notification SET unread = true, last_time_update_balance = ? WHERE id = ?";

    public NotificationDAImpl(DataSource dataSource, AsyncHandler asyncHandler) {
        this.dataSource = dataSource;
        this.asyncHandler = asyncHandler;
    }
    @Override
    public Executable<Notification> insert(Notification notification) {
        log.info("insert a new notification for user_id {}",notification.getUserId());
        return connection -> {
            Future<Notification> future = Future.future();
            asyncHandler.run(
                    () -> {
                        Object[] params = {notification.getNotificationType(), notification.getUserId(), notification.getPartnerId(), notification.getAmount(), notification.getMessage(), notification.isUnread()};
                        try {
                            executeWithParams(future, connection.unwrap(), INSERT_NOTIFICATION_STATEMENT, params, "insertTransferHistory");
                        } catch (SQLException e) {
                            future.fail(e);
                        }
                    });
            return future;
        };
    }

    @Override
    public Executable<Notification> updateSeenNotificationById(long id) {
        return null;
    }

    @Override
    public Future<ArrayList<Notification>> selectNotificationByAccountId(long id) {
        log.info("select a list notification");
        Future<ArrayList<Notification>> future = Future.future();
        asyncHandler.run(
                () -> {
                    Object[] params = {id};
                    queryEntity(
                            "queryTransferHistory",
                            future,
                            SELECT_NOTIFICATION_BY_ACCOUNT_ID,
                            params,
                            this::mapRs2EntityNotification,
                            dataSource::getConnection,
                            false);
                });

        return future;
    }

    private ArrayList<Notification> mapRs2EntityNotification(ResultSet resultSet) throws Exception {
        Notification notification = null;
        ArrayList<Notification> notificationList = new ArrayList<>();
        while (resultSet.next()) {
            notification = new Notification();
            EntityMapper.getInstance().loadResultSetIntoObject(resultSet, notification);
            notificationList.add(notification);
        }

        return notificationList;
    }
}
