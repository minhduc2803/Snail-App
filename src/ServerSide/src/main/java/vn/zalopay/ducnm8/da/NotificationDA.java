package vn.zalopay.ducnm8.da;

import io.vertx.core.Future;
import vn.zalopay.ducnm8.model.Notification;

import java.util.ArrayList;

public interface NotificationDA {
    Executable<Notification> insert(Notification notification);

    Executable<Notification> updateSeenNotificationById(long id);

    Future<ArrayList<Notification>> selectNotificationByAccountId(long id);
}
