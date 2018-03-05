package com.example.peethr.wsbtest.Models.connection;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class CheckInternetConnection {

    public boolean isNetworkAvailable(ConnectivityManager manager) {
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }
}
