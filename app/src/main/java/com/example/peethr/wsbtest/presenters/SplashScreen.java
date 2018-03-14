package com.example.peethr.wsbtest.presenters;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.peethr.wsbtest.models.alerts.NoInternetDialogFragment;
import com.example.peethr.wsbtest.models.connection.CheckInternetConnection;
import com.example.peethr.wsbtest.models.connection.HttpConnection;
import com.example.peethr.wsbtest.models.data.weather.Globals;
import com.example.peethr.wsbtest.services.AlertService;
import com.example.peethr.wsbtest.R;

public class SplashScreen extends AppCompatActivity {

    Globals g = Globals.getInstance();
    private ProgressBar progressBar;

    private  NoInternetDialogFragment dialog = new NoInternetDialogFragment();
    private CheckInternetConnection checkInternetConnection = new CheckInternetConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        progressBar = findViewById(R.id.progressBar);

        if (!checkInternetConnection.isNetworkAvailable(manager))
        {
            dialog.show(getFragmentManager(), "NoInternetConnection");
        }

        Thread loadingDataThread = new Thread(){
            @Override
            public void run(){

                ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                do {
                    try{
                        for(int i = 0; i <= 100; i++){
                            progressBar.setProgress(i);

                            sleep(10);


                            HttpConnection darkSky = new HttpConnection();
                            darkSky.darkSkyConnection(
                                    "https://api.darksky.net/forecast/9fc1bdd31c9dec7120cde99ff7e37614/54.3889,18.5843",
                                    manager);

                        }

                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                } while (!g.getIfWeatherUpdated());

                startMyService();
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();

            }
        };
        loadingDataThread.start();
    }
    private void startMyService() {
        Intent serviceIntent = new Intent(this, AlertService.class);
        startService(serviceIntent);
    }
}
