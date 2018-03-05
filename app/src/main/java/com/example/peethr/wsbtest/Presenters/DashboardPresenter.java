package com.example.peethr.wsbtest.Presenters;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.example.peethr.wsbtest.R;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

/**
 * Created by thomas on 05.03.18.
 */

public class DashboardPresenter extends AppCompatActivity implements Presenter{
    public DashboardPresenter(){
        start();

    }

    // variables
    private boolean ifExpanded = false;

    //views
    private ImageView arrowAlert;
    private Button alertButton;
    private ExpandableRelativeLayout expandableRelativeLayout;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        findViews();
        Log.i("DashboardPresenter", "Dziala");
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Animation of arrow and expanding button in dash
                if (!ifExpanded) {
                    animateArrow(90f, 270f);
                    expandableRelativeLayout.toggle();
                    alertButton.setBackgroundResource(R.drawable.dashboard_alert_button_clicked);
                } else {
                    animateArrow(270f, 90f);
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
    private void findViews(){
        alertButton = findViewById(R.id.newAlertButton);
        arrowAlert = findViewById(R.id.arrowAlert);
        expandableRelativeLayout = findViewById(R.id.expandableLayout1);
    }
    // Animated arrow when extending button in dash
    private void animateArrow(float startPosition, float finishPosition) {

        ObjectAnimator animation = ObjectAnimator.ofFloat(arrowAlert, "rotation", startPosition, finishPosition);
        animation.setDuration(500); // 0.5 second
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
        ifExpanded = !ifExpanded;
    }

    @Override
    public void start() {

    }
}
