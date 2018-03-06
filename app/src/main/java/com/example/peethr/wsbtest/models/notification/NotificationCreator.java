package com.example.peethr.wsbtest.models.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;


import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by thomas on 05.03.18.
 */

public class NotificationCreator {
    private Intent intent;
    private PendingIntent pIntent;
    private NotificationAlertManager notificationAlertManager;
    private Notification alert;
    private Context context;
    public NotificationCreator(Context context, Class activityClass){

        this.context = context;
        intent = new Intent(context, activityClass);
        pIntent = PendingIntent.getActivity(context, 0, intent, 0);

    }
    public void create(String title, String text, String ticker, int smallIcon, Bitmap largeIcon){
        alert = new NotificationAlert(context, "alert")
                        .setContentTitle(title)
                        .setContentText(text)
                        .setTicker(ticker)
                        .setSmallIcon(smallIcon)
                        .setLargeIcon(largeIcon)
                        .setAutoCancel(true)
                        .setContentIntent(pIntent)
                        .build();
        notificationAlertManager = new NotificationAlertManager(context.getSystemService(NOTIFICATION_SERVICE), alert);

    }

}
