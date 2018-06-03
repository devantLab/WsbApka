package pl.devant.wsbnotify.models.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

import pl.devant.wsbnotify.R;

/**
 * Created by thomas on 21.03.18.
 */

public class NotificationProperties {
    //Channel
    public static final String CHANNEL_ID = "pl.devant.app";
    public static final String CHANNEL_NAME = "devantChannel";
    public static final String CHANNEL_DESCRIPTION = "devantChannel Description";
    public static final int IMPORTANCE = NotificationManager.IMPORTANCE_DEFAULT;
    public static final int LOCKSCREEN_VISIBILITY = Notification.VISIBILITY_PRIVATE;
    public static final boolean LIGHTS = true;
    public static final boolean VIBRATION = true;
    public static final int LIGHT_COLOR = Color.RED;



    //Notification
    public static final int COLOR = Color.parseColor("#c63b38");
    public static final int LOGO = R.drawable.devant2;
    public static final int PRIORITY = NotificationCompat.PRIORITY_DEFAULT;
}
