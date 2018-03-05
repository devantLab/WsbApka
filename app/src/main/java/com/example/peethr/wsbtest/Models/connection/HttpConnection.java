package com.example.peethr.wsbtest.Models.connection;

import android.net.ConnectivityManager;
import android.util.Log;

import com.example.peethr.wsbtest.Models.weather.CurrentWeather;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpConnection {

    private CurrentWeather currentWeather;
    private String weatherData;
    private CheckInternetConnection checkInternetConnection = new CheckInternetConnection();


    public String darkSkyConnection(String forecastUrl, ConnectivityManager manager)
    {
        if(checkInternetConnection.isNetworkAvailable(manager)) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(forecastUrl)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String weatherData = response.body().string();
                        Log.v("HttpConnection", weatherData);
                        if (response.isSuccessful()) {
                            // currentWeather = getCurrentDetails(weatherData);

                        } else {
                            // alertUserAboutError();
                        }
                    }
                    catch (IOException e) {
                        Log.e("HttpConnectionError", "Exception caught: ", e);
                    }
                }
            });
        } else {
            // alertUserAboutConnectionProblem();
        }
        return weatherData;
    }
}
