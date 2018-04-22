package com.example.peethr.wsbtest.models.data.events;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by goracy on 11.04.18.
 */

public class CareerEvent implements Parcelable{

    private String careerEventTitle;
    private String careerEventDateAdd;
    private String careerEventDescription;
    private String careerEventLink;
//    private String careerEventClicks;
    private String careerEventImage;
    private String careerEventType;
//    private String careerEventId;



    public CareerEvent() {

    }

    public String getCareerEventTitle() {
        return careerEventTitle;
    }

    public String getCareerEventDateAdd() {
        return careerEventDateAdd;
    }

    public String getCareerEventDescription() {
        return careerEventDescription;
    }

    public String getCareerEventLink() {
        return careerEventLink;
    }

//    public String getCareerEventClicks() {
//        return careerEventClicks;
//    }

    public String getCareerEventImage() {
        return careerEventImage;
    }

    public String getCareerEventType() {
        return careerEventType;
    }

//    public String getCareerEventId() {
//        return careerEventId;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(careerEventTitle);
//        dest.writeString(careerEventClicks);
        dest.writeString(careerEventImage);
        dest.writeString(careerEventDescription);
        dest.writeString(careerEventDateAdd);
//        dest.writeString(careerEventId);
        dest.writeString(careerEventLink);
        dest.writeString(careerEventType);
    }

    public CareerEvent(Parcel in)
    {
        careerEventTitle = in.readString();
//        careerEventClicks = in.readString();
        careerEventImage = in.readString();
//        careerEventId = in.readString();
        careerEventDateAdd = in.readString();
        careerEventDescription = in.readString();
        careerEventLink = in.readString();
        careerEventType = in.readString();
    }

    public static final Parcelable.Creator<CareerEvent> CREATOR = new Parcelable.Creator<CareerEvent>() {
        @Override
        public CareerEvent createFromParcel(Parcel source) {
            return new CareerEvent(source);
        }

        @Override
        public CareerEvent[] newArray(int size) {
            return new CareerEvent[size];
        }
    };
}
