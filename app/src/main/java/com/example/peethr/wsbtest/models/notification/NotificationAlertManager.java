package com.example.peethr.wsbtest.models.notification;

import android.app.Notification;
import android.app.NotificationManager;


/**
 * Created by thomas on 05.03.18.
 * Modified by thomas on 09.03.18
 * 3 possible types of notifications have been added see -> NotificationType (ENUM)
 */

public class NotificationAlertManager {

    private NotificationManager notificationManager;

    /**
     *
     * @param systemService - Service context
     * @param notification - built notification sent to the service
     * @param type - type of ( NotificationType :     ALERTS_ID(0), EVENTS_ID(1), SPECIAL_ID(2))
     *
     *
     * If a new notification with the same ID appears, the old one is deleted ( no  the same notification type SPAM)
     */
    public NotificationAlertManager(Object systemService, Notification notification, NotificationType type){
        notificationManager = (NotificationManager) systemService;

        notificationManager.notify(type.ordinal(), notification);
    }
}
