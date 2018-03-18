package com.example.peethr.wsbtest.models.connection;

import android.app.FragmentManager;
import android.net.ConnectivityManager;
import android.util.Log;

import com.example.peethr.wsbtest.models.alerts.NoInternetDialogFragment;
import com.example.peethr.wsbtest.models.data.weather.CurrentWeather;
import com.example.peethr.wsbtest.models.data.weather.GetCurrentDetails;
import com.example.peethr.wsbtest.models.data.weather.Globals;

import org.json.JSONException;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static java.lang.Math.floor;

public class HttpConnection {



    Globals g = Globals.getInstance();
    private CurrentWeather currentWeather;
    private CheckInternetConnection checkInternetConnection = new CheckInternetConnection();
    private NoInternetDialogFragment dialog = new NoInternetDialogFragment();

    // Connect with darkSky weather API
    public CurrentWeather darkSkyConnection(String forecastUrl, ConnectivityManager manager, FragmentManager fragmentManager)
    {
        // Set global variable so we wont make multi connections
        g.setTryConnectingToDarkSky(false);

        if(checkInternetConnection.isNetworkAvailable(manager)) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(forecastUrl)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    Globals g = Globals.getInstance();
                    g.setContinueWithoutWeatherData(true);
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        // Parse json objects from API to string
                        String weatherData = response.body().string();

                        if (response.isSuccessful()) {
                            // Get only needed elements
                            GetCurrentDetails getCurrentDetails = new GetCurrentDetails();
                            currentWeather = getCurrentDetails.getCurrentDetails(weatherData);
                            // Put data in Singletone so we can access them form DashboardFragment
                            g.setTemperature((int)floor(currentWeather.getTemperature()));
                            g.setSummary(currentWeather.getSummary());
                            g.setIfWeatherUpdated(true);

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
            dialog.show(fragmentManager, "NoInternetConnection");
        }
        return currentWeather;
    }

}