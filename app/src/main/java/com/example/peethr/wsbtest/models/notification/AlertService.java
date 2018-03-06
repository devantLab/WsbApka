package com.example.peethr.wsbtest.models.notification;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

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
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        writeToLogs("Called onStartCommand() methond");
        clearTimerSchedule();
        initTask();
        timer.scheduleAtFixedRate(timerTask, 4 * 10000, 4 * 10000);
        showToast("Your service has been started");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        writeToLogs("Called onDestroy() method");
        clearTimerSchedule();
        showToast("Your service has been stopped");
        super.onDestroy();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private Toast toast;
    private Timer timer;
    private TimerTask timerTask;
    private Context context = this;
    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
//           NotificationCreator notificationCreator = new NotificationCreator(context, MainActivity.class);
           //create(String title, String text, String ticker, int smallIcon, Bitmap largeIcon)
//           notificationCreator.create(
//                   "Powiadomienie",
//                   "Prosze o doniesienie dokumentow",
//                   "Wiadomosc",
//                   android.R.drawable.ic_dialog_info,
//                   BitmapFactory.decodeResource(context.getResources(), android.R.drawable.ic_dialog_info));
//            NotificationCreator notificationCreator2 = new NotificationCreator(context, MainActivity.class);
//            //create(String title, String text, String ticker, int smallIcon, Bitmap largeIcon)
//            notificationCreator.create(
//                    "TEST",
//                    "HAHA",
//                    "Wiadomosc",
//                    android.R.drawable.ic_dialog_info,
//                    BitmapFactory.decodeResource(context.getResources(), android.R.drawable.ic_dialog_info));
        }
    }

    private void showToast(String text) {
        toast.setText(text);
        toast.show();
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
