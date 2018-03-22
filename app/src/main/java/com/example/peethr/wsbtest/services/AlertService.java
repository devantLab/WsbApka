package com.example.peethr.wsbtest.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.notification.NotificationCreator;
import com.example.peethr.wsbtest.models.data.weather.Globals;
import com.example.peethr.wsbtest.models.notification.NotificationType;
import com.example.peethr.wsbtest.presenters.MainActivity;


import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by thomas on 05.03.18.
 */

public class AlertService extends Service {

    private void writeToLogs(String message) {
        Log.d("HelloServices", message);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        writeToLogs("Called onCreate() method.");
        timer = new Timer();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        writeToLogs("Called onStartCommand() methond");
        clearTimerSchedule();
        initTask();
        timer.scheduleAtFixedRate(timerTask, 0, 3600000);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        writeToLogs("Called onDestroy() method");
        clearTimerSchedule();
        super.onDestroy();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Timer timer;
    private TimerTask timerTask;
    private Context context = this;
    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
           NotificationCreator notificationCreator = new NotificationCreator(context, MainActivity.class);
           //create(String title, String text, String ticker, int smallIcon, Bitmap largeIcon)

           notificationCreator.create(
                   "WSBApp",
                   "Nie dostarczono dokumentów :(",
                   "Przypominamy o dostarczeniu polskiego adresu do Działu zagranicznego A316 (Uczelnia -> Plan uczelni), do 30 kwietnia",
                   "Info",
                   R.drawable.ic_info_outline_white_36dp,
                   NotificationType.ALERTS_ID);

        }
    }

    private void clearTimerSchedule() {
        if(timerTask != null) {
            timerTask.cancel();
            timer.purge();
        }
    }

    private void initTask() {
        timerTask = new MyTimerTask();
    }

}
