package pl.devant.wsbnotify.models.data.places;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by goracy on 11.04.18.
 */

public class Place implements Parcelable {

    private String placeTitle;
    private String placeDescription;
    private String placeImage;
    private String placeCategory;
    private String placeCity;
    private String placeStreet;
//    private String placeId;
    private String placeLatitude;
    private String placeLongitude;
//    private String placeClicks;



    public Place(){

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

//    public String getPlaceClicks() {
//        return placeClicks;
//    }

    public String getPlaceCategory() {
        return placeCategory;
    }

    public String getPlaceCity() {
        return placeCity;
    }

    public String getPlaceStreet() {
        return placeStreet;
    }

    public String getPlaceLatitude() {
        return placeLatitude;
    }

    public String getPlaceLongitude() {
        return placeLongitude;
    }

//    public String getPlaceId() {
//        return placeId;
//    }

    protected Place(Parcel in) {
        placeTitle = in.readString();
        placeDescription = in.readString();
        placeCategory = in.readString();
        placeCity = in.readString();
        placeStreet = in.readString();
        placeImage = in.readString();
//        placeId = in.readString();
        placeLatitude = in.readString();
        placeLongitude = in.readString();
//        placeClicks = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(placeTitle);
        dest.writeString(placeDescription);
        dest.writeString(placeCategory);
        dest.writeString(placeCity);
        dest.writeString(placeStreet);
        dest.writeString(placeImage);
//        dest.writeString(placeId);
        dest.writeString(placeLatitude);
        dest.writeString(placeLongitude);
//        dest.writeString(placeClicks);

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
