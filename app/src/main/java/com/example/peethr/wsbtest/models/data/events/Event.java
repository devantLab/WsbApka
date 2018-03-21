package com.example.peethr.wsbtest.models.data.events;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by goracy on 14.03.18.
 */

public class Event implements Parcelable{

    private String eventTitle;
    private String eventPlace;
    private String eventDescription;


    private int eventImage;

    public Event(String eventTitle, String eventPlace, int eventImage, String eventDescription) {
        this.eventTitle = eventTitle;
        this.eventPlace = eventPlace;
        this.eventImage = eventImage;
        this.eventDescription = eventDescription;
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

    public String getEventDescription() {
        return eventDescription;
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
        dest.writeString(eventDescription);
    }

    public Event(Parcel in)
    {
        eventTitle = in.readString();
        eventPlace = in.readString();
        eventImage = in.readInt();
        eventDescription = in.readString();
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
