package com.example.peethr.wsbtest.models.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;


import com.example.peethr.wsbtest.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by thomas on 05.03.18.
 */

public class NotificationCreator extends ContextWrapper{
    private Intent intent;
    private PendingIntent pIntent;
    private NotificationAlertManager notificationAlertManager;
    private Notification alert;
    private Context context;
    private static final String CHANNEL_ID = "pl.devant.app";
    private static final String CHANNEL_NAME = "devantChannel";
    private static final String CHANNEL_DESCRIPTION = "devantChannel Description";
    public NotificationCreator(Context context, Class activityClass){
        super(context);

        this.context = context;
        intent = new Intent(context, activityClass);
        pIntent = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManagerCompat.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESCRIPTION);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLightColor(Color.RED);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            // Register the channel with the system

            notificationManager.createNotificationChannel(channel);
        }

    }
    public void create(String title, String text, String ticker, int smallIcon, Bitmap largeIcon, NotificationType type){

            alert = new NotificationCompat.Builder(context, CHANNEL_ID)
                            .setContentTitle(title)
                            .setContentText(text)
                            .setTicker(ticker)
                            .setSmallIcon(smallIcon)
                            .setLargeIcon(largeIcon)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setAutoCancel(true)
                            .setContentIntent(pIntent)
                            .build();

        notificationAlertManager = new NotificationAlertManager(context, alert, type);

    }

}
