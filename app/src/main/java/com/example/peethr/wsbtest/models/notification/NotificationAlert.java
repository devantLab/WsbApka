package com.example.peethr.wsbtest.models.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;


/**
 * Created by thomas on 05.03.18.
 */

public class NotificationAlert extends NotificationCompat.Builder{

    public NotificationAlert(@NonNull Context context, @NonNull String channelId) {
        super(context, channelId);
    }

    @Override
    public NotificationCompat.Builder setSmallIcon(int icon) {
        return super.setSmallIcon(icon);
    }

    @Override
    public NotificationCompat.Builder setContentTitle(CharSequence title) {
        return super.setContentTitle(title);
    }

    @Override
    public NotificationCompat.Builder setContentText(CharSequence text) {
        return super.setContentText(text);
    }

    @Override
    public NotificationCompat.Builder setContentIntent(PendingIntent intent) {
        return super.setContentIntent(intent);
    }

    @Override
    public NotificationCompat.Builder setTicker(CharSequence tickerText) {
        return super.setTicker(tickerText);
    }

    @Override
    public NotificationCompat.Builder setLargeIcon(Bitmap icon) {
        return super.setLargeIcon(icon);
    }

    @Override
    public NotificationCompat.Builder setAutoCancel(boolean autoCancel) {
        return super.setAutoCancel(autoCancel);
    }

    @Override
    public Notification build() {
        return super.build();
    }

}
