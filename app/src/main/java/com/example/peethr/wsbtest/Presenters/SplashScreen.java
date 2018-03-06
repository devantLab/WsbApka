package com.example.peethr.wsbtest.Presenters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.peethr.wsbtest.R;

public class SplashScreen extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar = findViewById(R.id.progressBar);
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
