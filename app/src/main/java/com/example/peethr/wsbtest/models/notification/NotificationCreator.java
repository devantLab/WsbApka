package com.example.peethr.wsbtest.models.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private Notification alert;
    private Context context;
    public NotificationCreator(Context context, Class activityClass){
        super(context);

        this.context = context;
        intent = new Intent(context, activityClass);
        pIntent = PendingIntent.getActivity(context, 0, intent, 0);

        createChannel();

    }
    public void create(String title, String text, String bigText, String ticker, int smallIcon, NotificationType type){

            alert = new NotificationCompat.Builder(context, NotificationProperties.CHANNEL_ID)
                            .setContentTitle(title)
                            .setContentText(text)
                            .setTicker(ticker)
                            .setSmallIcon(smallIcon)
                            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), NotificationProperties.LOGO))
                            .setPriority(NotificationProperties.PRIORITY)
                            .setAutoCancel(true)
                            .setContentIntent(pIntent)
                            .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(bigText))
                            .setColor(NotificationProperties.COLOR)
                            .setColorized(true)
                            .build();
            new NotificationAlertManager(context, alert, type);

    }
    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            NotificationChannel channel = new NotificationChannel(NotificationProperties.CHANNEL_ID, NotificationProperties.CHANNEL_NAME, NotificationProperties.IMPORTANCE);
            channel.setDescription(NotificationProperties.CHANNEL_DESCRIPTION);
            channel.enableLights(NotificationProperties.LIGHTS);
            channel.enableVibration(NotificationProperties.VIBRATION);
            channel.setLightColor(NotificationProperties.LIGHT_COLOR);
            channel.setLockscreenVisibility(NotificationProperties.LOCKSCREEN_VISIBILITY);
            // Register the channel with the system

            notificationManager.createNotificationChannel(channel);
        }
    }

}
