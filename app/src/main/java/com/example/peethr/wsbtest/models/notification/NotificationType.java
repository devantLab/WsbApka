package com.example.peethr.wsbtest.models.notification;

/**
 * Created by thomas on 09.03.18.
 */

public enum NotificationType {
    /**
     * Types of possible notification
     */
    ALERTS_ID(0),
    EVENTS_ID(1),
    SPECIAL_ID(2);
    private final int value;

    NotificationType(int value) {
        this.value = value;
    }
}
