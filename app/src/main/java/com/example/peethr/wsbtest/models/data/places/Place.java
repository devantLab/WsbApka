package com.example.peethr.wsbtest.models.data.places;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by goracy on 11.04.18.
 */

public class Place implements Parcelable {

    private String placeTitle;
    private String placeDescription;
    private String placeImage;

    public Place(String placeTitle, String placeDescription, String placeImage){
        this.placeTitle = placeTitle;
        this.placeDescription = placeDescription;
        this.placeImage = placeImage;
    }

    public String getPlaceTitle() {
        return placeTitle;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public String getPlaceImage() {
        return placeImage;
    }

    protected Place(Parcel in) {
        placeTitle = in.readString();
        placeDescription = in.readString();
        placeImage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(placeTitle);
        dest.writeString(placeDescription);
        dest.writeString(placeImage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
}
