package pl.devant.wsbnotify.models.data.recentApps;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by goracy on 16.04.18.
 */

public class RecentApp implements Parcelable {

    private String recentAppTitle;
    private String recentAppDescription;
    private String recentAppImage;

    public RecentApp(String recentAppTitle, String recentAppDescription, String recentAppImage) {
        this.recentAppTitle = recentAppTitle;
        this.recentAppDescription = recentAppDescription;
        this.recentAppImage = recentAppImage;
    }

    public String getRecentAppTitle() {
        return recentAppTitle;
    }

    public String getRecentAppDescription() {
        return recentAppDescription;
    }

    public String getRecentAppImage() {
        return recentAppImage;
    }

    protected RecentApp(Parcel in) {
        recentAppTitle = in.readString();
        recentAppDescription = in.readString();
        recentAppImage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recentAppTitle);
        dest.writeString(recentAppDescription);
        dest.writeString(recentAppImage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<RecentApp> CREATOR = new Parcelable.Creator<RecentApp>() {
        @Override
        public RecentApp createFromParcel(Parcel in) {
            return new RecentApp(in);
        }

        @Override
        public RecentApp[] newArray(int size) {
            return new RecentApp[size];
        }
    };
}
