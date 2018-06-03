package pl.devant.wsbnotify.models.notification;

/**
 * Created by thomas on 09.03.18.
 */

public enum NotificationType {
    /**
     * Types of possible notification
     */
    ALERTS_ID(3450),
    EVENTS_ID(3451),
    SPECIAL_ID(3452);
    private final int value;

    NotificationType(int value) {
        this.value = value;
    }
}
