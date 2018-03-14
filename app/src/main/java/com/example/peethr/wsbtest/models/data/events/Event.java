package com.example.peethr.wsbtest.models.data.events;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by goracy on 14.03.18.
 */

public class Event implements Parcelable{

    private String eventTitle;
    private String eventPlace;
    private int eventImage;

    public Event(String eventTitle, String eventPlace, int eventImage) {
        this.eventTitle = eventTitle;
        this.eventPlace = eventPlace;
        this.eventImage = eventImage;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public int getEventImage() {
        return eventImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(eventTitle);
        dest.writeString(eventPlace);
        dest.writeInt(eventImage);
    }

    public Event(Parcel in)
    {
        eventTitle = in.readString();
        eventPlace = in.readString();
        eventImage = in.readInt();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}
