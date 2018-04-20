package com.example.peethr.wsbtest.models.data.events;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by goracy on 14.03.18.
 */

public class Event implements Parcelable{

    private int eventId;
    private String eventTitle;
    private String eventDescription;
    private String eventCity;
    private String eventStreet;
    private String eventTimeStart;
    private String eventTimeEnd;
    private String eventLink;
//    private double eventLatitude;
//    private double eventLongitude;
    private String eventDate;
    private String eventClicks;
    private boolean eventIsWSB;
    private String eventImage;
    private int eventDay;
    private int eventMonth;

    public Event(){}

//    public Event(int eventId, String eventTitle, String eventDescription, String eventCity,
//                 String eventStreet, String eventTimeStart, String eventTimeEnd, String eventLink,
//                 String eventDate, String eventClicks,
//                 boolean eventIsWSB, String eventImage) {
//
//        this.eventId = eventId;
//        this.eventTitle = eventTitle;
//        this.eventDescription = eventDescription;
//        this.eventCity = eventCity;
//        this.eventStreet = eventStreet;
//        this.eventTimeStart = eventTimeStart;
//        this.eventTimeEnd = eventTimeEnd;
//        this.eventLink = eventLink;
//        this.eventLatitude = eventLatitude;
//        this.eventLongitude = eventLongitude;
//        this.eventDate = eventDate;
//        this.eventClicks = eventClicks;
//        this.eventIsWSB = eventIsWSB;
//        this.eventImage = eventImage;
//    }

    private int separateDay(String eventDate){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date eDate = null;
        try {
            eDate = format.parse(eventDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        eventDay = eDate.getDay();
        Log.i("Gówno", eventDay+"");
        return eventDay;
    }

    private int separateMonth(String eventDate){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date eDate = null;
        try {
            eDate = format.parse(eventDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        eventMonth = eDate.getMonth();
        Log.i("Gówno", eventMonth+"");
        return eventMonth;
    }

    public int getEventId() {
        return eventId;
    }

    public int getEventDay() {
        return separateDay(eventDate);
    }

    public int getEventMonth() {
        return separateMonth(eventDate);
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getEventCity() {
        return eventCity;
    }

    public String getEventStreet() {
        return eventStreet;
    }

    public String getEventTimeStart() {
        return eventTimeStart;
    }

    public String getEventTimeEnd() {
        return eventTimeEnd;
    }

    public String getEventLink() {
        return eventLink;
    }

//    public double getEventLatitude() {
//        return eventLatitude;
//    }
//
//    public double getEventLongitude() {
//        return eventLongitude;
//    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventClicks() {
        return eventClicks;
    }

    public boolean isEventIsWSB() {
        return eventIsWSB;
    }

    public String getEventImage() {
        return eventImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(eventId);
        dest.writeString(eventTitle);
        dest.writeString(eventDescription);
        dest.writeString(eventCity);
        dest.writeString(eventStreet);
        dest.writeString(eventImage);
        dest.writeString(eventDate);
        dest.writeString(eventLink);
        dest.writeString(eventTimeStart);
        dest.writeString(eventTimeEnd);
//        dest.writeDouble(eventLatitude);
//        dest.writeDouble(eventLongitude);
    }


    public Event(Parcel in)
    {
        eventId = in.readInt();
        eventTitle = in.readString();
        eventDescription = in.readString();
        eventCity = in.readString();
        eventStreet = in.readString();
        eventImage = in.readString();
        eventDate = in.readString();
        eventLink = in.readString();
        eventTimeStart = in.readString();
        eventTimeEnd = in.readString();
//        eventLatitude = in.readDouble();
//        eventLongitude = in.readDouble();
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
