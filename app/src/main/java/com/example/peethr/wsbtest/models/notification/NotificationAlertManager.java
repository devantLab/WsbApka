package com.example.peethr.wsbtest.models.notification;

import android.app.Notification;
import android.app.NotificationManager;


/**
 * Created by thomas on 05.03.18.
 */

public class NotificationAlertManager {
    static int id;
    private NotificationManager notificationManager;


    public NotificationAlertManager(Object systemService, Notification alert){
        notificationManager = (NotificationManager) systemService;

        notificationManager.notify(id++, alert);
    }
}
