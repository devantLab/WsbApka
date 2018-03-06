package com.example.peethr.wsbtest.presenters;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.peethr.wsbtest.models.connection.HttpConnection;
import com.example.peethr.wsbtest.models.notification.AlertService;
import com.example.peethr.wsbtest.R;

public class SplashScreen extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar = findViewById(R.id.progressBar);

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        HttpConnection darkSky = new HttpConnection();
        darkSky.darkSkyConnection(
                "https://api.darksky.net/forecast/9fc1bdd31c9dec7120cde99ff7e37614/54.3889,18.5843",
                manager);

        startMyService();
        Thread loadingDataThread = new Thread(){
            @Override
            public void run(){
                try{
                    for(int i = 0; i <= 100; i++){
                        progressBar.setProgress(i);

                        sleep(10);
                    }
                    startActivity(new Intent(SplashScreen.this, ParentActivity.class));
                    finish();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        loadingDataThread.start();
    }
    private void startMyService() {
        Intent serviceIntent = new Intent(this, AlertService.class);
        startService(serviceIntent);
    }
}
