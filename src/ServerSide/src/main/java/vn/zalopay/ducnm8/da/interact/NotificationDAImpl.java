package vn.zalopay.ducnm8.da.interact;

import io.vertx.core.Future;
import org.apache.logging.log4j.Logger;
import vn.zalopay.ducnm8.common.mapper.EntityMapper;
import vn.zalopay.ducnm8.da.BaseTransactionDA;
import vn.zalopay.ducnm8.da.Executable;
import vn.zalopay.ducnm8.model.Notification;
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
            "INSERT INTO notification (`notification_type`,`user_id`,`partner_id`,`amount`,`message`,`seen`) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_NOTIFICATION_BY_ACCOUNT_ID =
            "SELECT N.notification_type, N.user_id, N.partner_id, N.amount, N.message, N.seen,\n" +
            "C.user_name, C.full_name\n" +
            "FROM notification as N\n" +
            "INNER JOIN account as C\n" +
            "ON N.user_id = C.id\n" +
            "WHERE N.user_id = ?";
    private static final String UPDATE_SEEN_A_NOTIFICATION =
            "UPDATE notification SET unread = true WHERE id = ?";

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
                        Object[] params = {notification.getNotificationType(), notification.getUserId(), notification.getPartnerId(), notification.getAmount(), notification.getMessage(), notification.isSeen()};
                        try {
                            long id = executeWithParamsAndGetId(connection.unwrap(), INSERT_NOTIFICATION_STATEMENT, params, "insertNotification");
                            notification.setId(id);
                            future.complete(notification);
                        } catch (Exception e) {
                            future.fail(e);
                            String reason = String.format("cannot insert a notification ~ cause: %s", e.getMessage());
                            log.error(reason);
                        }
                    });
            return future;
        };
    }

    @Override
    public Executable<Notification> updateSeenNotificationById(long id) {
        log.info("update seen a notification: id={}", id);
        return connection -> {
            Future<Notification> future = Future.future();
            asyncHandler.run(
                    () -> {
                        Object[] params = {id};
                        try {
                            executeWithParamsAndGetId(connection.unwrap(), UPDATE_SEEN_A_NOTIFICATION, params, "updateNotification");
                            Notification notification = Notification.builder().id(id).build();
                            future.complete(notification);
                        } catch (Exception e) {
                            future.fail(e);
                        }
                    });
            return future;
        };
    }

    @Override
    public Future<ArrayList<Notification>> selectNotificationByAccountId(long id) {
        log.info("select a list notifications");
        Future<ArrayList<Notification>> future = Future.future();
        asyncHandler.run(
                () -> {
                    Object[] params = {id};
                    queryEntity(
                            "queryNotification",
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
