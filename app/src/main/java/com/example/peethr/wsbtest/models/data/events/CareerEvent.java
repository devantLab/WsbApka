package com.example.peethr.wsbtest.models.data.events;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by goracy on 11.04.18.
 */

public class CareerEvent implements Parcelable{

    private String careerEventTitle;
    private String careerEventPlace;
    private String careerEventDescription;
    private String careerEventImage;

    public CareerEvent(String careerEventTitle, String careerEventPlace, String careerEventImage, String careerEventDescription) {
        this.careerEventTitle = careerEventTitle;
        this.careerEventPlace = careerEventPlace;
        this.careerEventImage = careerEventImage;
        this.careerEventDescription = careerEventDescription;
    }

    public String getCareerEventTitle() {
        return careerEventTitle;
    }

    public String getCareerEventPlace() {
        return careerEventPlace;
    }

    public String getCareerEventImage() {
        return careerEventImage;
    }

    public String getCareerEventDescription() {
        return careerEventDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(careerEventTitle);
        dest.writeString(careerEventPlace);
        dest.writeString(careerEventImage);
        dest.writeString(careerEventDescription);
    }

    public CareerEvent(Parcel in)
    {
        careerEventTitle = in.readString();
        careerEventPlace = in.readString();
        careerEventImage = in.readString();
        careerEventDescription = in.readString();
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
