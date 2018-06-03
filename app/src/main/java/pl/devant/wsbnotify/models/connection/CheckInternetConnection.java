package pl.devant.wsbnotify.models.connection;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class CheckInternetConnection {

    // Check if there is internet connection
    public boolean isNetworkAvailable(ConnectivityManager manager) {
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }
}
