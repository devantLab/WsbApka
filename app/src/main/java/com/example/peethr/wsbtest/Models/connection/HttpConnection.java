package com.example.peethr.wsbtest.Models.connection;

import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import com.example.peethr.wsbtest.Models.weather.CurrentWeather;
import com.example.peethr.wsbtest.Models.weather.GetCurrentDetails;
import com.example.peethr.wsbtest.Presenters.ParentActivity;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpConnection {

    private CurrentWeather currentWeather;
    private CheckInternetConnection checkInternetConnection = new CheckInternetConnection();


    public CurrentWeather darkSkyConnection(String forecastUrl, ConnectivityManager manager)
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
                            GetCurrentDetails getCurrentDetails = new GetCurrentDetails();
                           currentWeather = getCurrentDetails.getCurrentDetails(weatherData);

                           Log.d("Elo", String.valueOf(currentWeather.getTemperature()));

                        } else {

                        }
                    }
                    catch (IOException e) {
                        Log.e("HttpConnectionError", "Exception caught: ", e);
                    }
                    catch (JSONException e)
                    {
                        Log.e("HttpConnectionError", "Exception caught: ", e);
                    }
                }
            });
        } else {
            // alertUserAboutConnectionProblem();
        }
        return currentWeather;
    }
}
