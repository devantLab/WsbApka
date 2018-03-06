package com.example.peethr.wsbtest.presenters;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peethr.wsbtest.models.alerts.NoInternetDialogFragment;
import com.example.peethr.wsbtest.models.connection.CheckInternetConnection;
import com.example.peethr.wsbtest.models.connection.HttpConnection;
import com.example.peethr.wsbtest.models.weather.CurrentWeather;
import com.example.peethr.wsbtest.models.weather.Globals;
import com.example.peethr.wsbtest.R;
import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import static java.lang.Math.floor;

public class ParentActivity extends AppCompatActivity {



    // variables
    CurrentWeather currentWeather = new CurrentWeather();

    // views
    private SeekBar seekbar;
    private ImageView dashTopIcon;
    private ImageView universityTopIcon;
    private ImageView eventTopIcon;
    private ImageView infoTopIcon;
    private ImageView backgroundSelectionDash;
    private ImageView backgroundSelectionWsb;
    private ImageView backgroundSelectionEvent;
    private ImageView backgroundSelectionInfo;
    private ImageView arrowAlert;

    private Button alertButton;
    private Button weatherButton;

    public TextView degrees;
    public TextView weatherMessage;

    protected ExpandableRelativeLayout expandableRelativeLayout;
    private ConstraintLayout dashboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        updateWeather();

        checkInternetConnection();

        // Expand layout on first run - it got collapsed
        expandableRelativeLayout.toggle();

        topIconListeners();


        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Animation of arrow and expanding button in dash
                getArrowAnimation();

            }
        });


    }

    // Update view with variables loaded in splashScreen
    private void updateWeather() {
        Globals g = Globals.getInstance();
        int temp =(int) floor((g.getTemperature()-32)*5/9);
        degrees.setText(String.valueOf(temp)+ "°");
        weatherMessage.setText(g.getSummary());
    }

    // Check if there is internet connection, if not show NoInternetDialogFragment
    private void checkInternetConnection() {

        // Can't use getSystemService in class without activity so we need to pass it
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        CheckInternetConnection connection = new CheckInternetConnection();

        if(!connection.isNetworkAvailable(manager)){
            NoInternetDialogFragment dialog = new NoInternetDialogFragment();
            dialog.show(getFragmentManager(), "NoInternetDialogFragment");
        }

    }

    // Starts animation
    private void getArrowAnimation() {
        if(!expandableRelativeLayout.isExpanded())
        {
            expandableRelativeLayout.setListener(new ExpandableLayoutListener() {
                @Override
                public void onAnimationStart() {
                    if (!expandableRelativeLayout.isExpanded())
                    {
                        alertButton.setBackgroundResource(R.drawable.dashboard_alert_button_clicked);
                        animateArrow(90f, 270f);
                    } else
                    {
                        animateArrow(270f,90f);
                    }
                }

                @Override
                public void onAnimationEnd() {

                    if (!expandableRelativeLayout.isExpanded())
                    {
                        alertButton.setBackgroundResource(R.drawable.dashboard_alert_button_unclicked);
                    }
                }

                // You can get notification that your expandable layout is going to open or close.
                // So, you can set the animation synchronized with expanding animation.
                @Override
                public void onPreOpen() {
                }

                @Override
                public void onPreClose() {
                }

                @Override
                public void onOpened() {
                }

                @Override
                public void onClosed() {
                }
            });
            expandableRelativeLayout.toggle();
        }
        else {
            expandableRelativeLayout.toggle();
        }
    }

    // Listeners for icons in Top Menu
    private void topIconListeners() {

        dashTopIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimationTopMenu(17);
                clearBackgroundSelection();
                backgroundSelectionDash.setVisibility(View.VISIBLE);
                dashboard.setVisibility(View.VISIBLE);
            }
        });

        universityTopIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimationTopMenu(39);
                clearBackgroundSelection();
                dashboard.setVisibility(View.GONE);
                backgroundSelectionWsb.setVisibility(View.VISIBLE);


            }
        });

        eventTopIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimationTopMenu(61);
                clearBackgroundSelection();
                backgroundSelectionEvent.setVisibility(View.VISIBLE);
            }
        });

        infoTopIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimationTopMenu(83);
                clearBackgroundSelection();
                backgroundSelectionInfo.setVisibility(View.VISIBLE);
            }
        });
    }

    // Initialization of components
    private void findViews() {
        seekbar = findViewById(R.id.seekBar);
        seekbar.setEnabled(false);

        dashTopIcon = findViewById(R.id.dashTopIcon);
        universityTopIcon = findViewById(R.id.universityTopIcon);
        eventTopIcon = findViewById(R.id.eventTopIcon);
        infoTopIcon = findViewById(R.id.infoTopIcon);

        backgroundSelectionDash = findViewById(R.id.backgroundSelectionDash);
        backgroundSelectionWsb = findViewById(R.id.backgroundSelectionWsb);
        backgroundSelectionEvent = findViewById(R.id.backgroundSelectionEvent);
        backgroundSelectionInfo = findViewById(R.id.backgroundSelectionInfo);

        alertButton = findViewById(R.id.newAlertButton);
        weatherButton = findViewById(R.id.weatherButton);

        degrees = findViewById(R.id.degrees);
        weatherMessage = findViewById(R.id.weatherMessage);

        arrowAlert = findViewById(R.id.arrowAlert);

        expandableRelativeLayout = findViewById(R.id.expandableLayout1);
        dashboard = findViewById(R.id.dashboard);
    }

    // Clear highlight from Top Menu
    private void clearBackgroundSelection() {
        backgroundSelectionDash.setVisibility(View.GONE);
        backgroundSelectionWsb.setVisibility(View.GONE);
        backgroundSelectionEvent.setVisibility(View.GONE);
        backgroundSelectionInfo.setVisibility(View.GONE);
    }

    // Animated underline in top menu
    private void startAnimationTopMenu(int progressToReach) {
        // will update the "progress" propriety of seekbar until it reaches progress
        ObjectAnimator animation = ObjectAnimator.ofInt(seekbar, "progress", progressToReach);
        animation.setDuration(200); // 0.2 second
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
    }

    // Animated arrow when extending button in dash
    private void animateArrow(float startPosition, float finishPosition) {

        ObjectAnimator animation = ObjectAnimator.ofFloat(arrowAlert, "rotation", startPosition, finishPosition);
        animation.setDuration(500); // 0.5 second
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
    }
}