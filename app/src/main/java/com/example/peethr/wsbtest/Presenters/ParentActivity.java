package com.example.peethr.wsbtest.Presenters;

import android.animation.ObjectAnimator;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.peethr.wsbtest.R;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class ParentActivity extends AppCompatActivity {

    // variables
    private boolean ifExpanded = false;

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

    protected ExpandableRelativeLayout expandableRelativeLayout;
    private ConstraintLayout dashboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        topIconListeners();

        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Animation of arrow and expanding button in dash
                if(!ifExpanded)
                {
                    animateArrow(90f, 270f);
                    expandableRelativeLayout.toggle();
                    alertButton.setBackgroundResource(R.drawable.dashboard_alert_button_clicked);
                }
                else {
                    animateArrow(270f,90f);
                    expandableRelativeLayout.toggle();

                    // Counter to change radius of button after expanding
                    new CountDownTimer(420, 50) {
                        public void onTick(long millisUntilFinished) {
                        }

                        public void onFinish() {
                            alertButton.setBackgroundResource(R.drawable.dashboard_alert_button_unclicked);
                        }
                    }.start();
                }
            }
        });

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
        ifExpanded = !ifExpanded;
    }
}
