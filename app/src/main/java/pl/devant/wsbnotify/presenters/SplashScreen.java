package pl.devant.wsbnotify.presenters;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

import pl.devant.wsbnotify.R;
import pl.devant.wsbnotify.models.connection.HttpConnection;
import pl.devant.wsbnotify.models.data.preferences.ManageSharedPreferences;
import pl.devant.wsbnotify.models.data.weather.Globals;
import pl.devant.wsbnotify.services.AlertService;


public class SplashScreen extends AppCompatActivity {

    Globals g = Globals.getInstance();
    private ProgressBar progressBar;

    private ManageSharedPreferences manageSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // needed to check if there is internet and to show alert about no internet connection
        final ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final FragmentManager fragmentManager = getFragmentManager();

        progressBar = findViewById(R.id.progressBar);

        // set language or start tutorial on first run
        if (checkLanguage()) {


            Toast.makeText(SplashScreen.this,
                    "Wybrany język: " + manageSharedPreferences.getLanguage(),
                    Toast.LENGTH_LONG)
                    .show();

            // async loading weather data
            Thread loadingDataThread = new Thread(){
                @Override
                public void run(){

                    do {
                        // check if first connection or user wants to try again
                        if(g.getTryConnectingToDarkSky())
                        {
                            HttpConnection darkSky = new HttpConnection();
                            darkSky.darkSkyConnection(
                                    "https://api.darksky.net/forecast/9fc1bdd31c9dec7120cde99ff7e37614/54.3889,18.5843",
                                    connectivityManager, fragmentManager);
                        }

                        try {
                            sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // check if weather was updated or user want to continue without data
                    } while (!g.getIfWeatherUpdated() && !g.getContinueWithoutWeatherData());

                    startMyService();
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();

                }
            };
            loadingDataThread.start();

        } else
        {
            Intent intent = new Intent(this, SelectLanguage.class);
            startActivity(intent);
        }

    }

    private boolean checkLanguage() {
        manageSharedPreferences = new ManageSharedPreferences(this);

        // check if its first run of app - if yes tutorial
        return (manageSharedPreferences.checkLanguage());

    }

    private void startMyService() {
        Intent serviceIntent = new Intent(this, AlertService.class);
        startService(serviceIntent);

    }
}
