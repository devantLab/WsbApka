package com.example.peethr.wsbtest.presenters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.events.CareerEvent;
import com.squareup.picasso.Picasso;

public class CareerEventDescription extends AppCompatActivity {

    private CareerEvent careerEvent;

    private TextView careerEventDescriptionTitle;
    private TextView careerEventDescriptionDescription;
    private ImageView careerEventDescriptionImage;
    private Button careerEventPageButton;

    private float y1, y2;
    static final int MIN_DISTANCE = 150;

    private Toolbar toolbar;

//    Globals g = Globals.getInstance();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_event_description);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getBackground().setAlpha(0);


        careerEventDescriptionTitle = findViewById(R.id.careerEventDescriptionTitle);
        careerEventDescriptionImage = findViewById(R.id.careerEventDescriptionImage);
        careerEventDescriptionDescription = findViewById(R.id.careerEventDescriptionDescription);
        careerEventPageButton = findViewById(R.id.careerEventPageButton);

        Intent intent = getIntent();
        careerEvent = intent.getParcelableExtra("clickedCareerEvent");

        updateData();
        animateIn();



    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void animateIn() {

        ObjectAnimator titleAlpha = ObjectAnimator.ofFloat(careerEventDescriptionTitle, "alpha", 0, 1);
        ObjectAnimator titleMove = ObjectAnimator.ofFloat(careerEventDescriptionTitle, "translationY",  170);

        AnimatorSet animatorInTitle = new AnimatorSet();
        animatorInTitle.playTogether(titleAlpha, titleMove);
        animatorInTitle.setStartDelay(200);
        animatorInTitle.setDuration(200);

        ObjectAnimator descriptionAlpha = ObjectAnimator.ofFloat(careerEventDescriptionDescription, "alpha", 0, 1);
        ObjectAnimator descriptionMove = ObjectAnimator.ofFloat(careerEventDescriptionDescription, "translationY", 150);
        ObjectAnimator eventPageButtonMove = ObjectAnimator.ofFloat(careerEventPageButton, "translationY", 0);

        AnimatorSet animatorInDescription = new AnimatorSet();
        animatorInDescription.setDuration(100);
        animatorInDescription.playTogether(descriptionAlpha, descriptionMove, eventPageButtonMove);

        AnimatorSet animationIn = new AnimatorSet();
        animationIn.playSequentially(animatorInTitle, animatorInDescription);
        animationIn.start();

    }

    private void updateData() {
        careerEventDescriptionTitle.setText(careerEvent.getCareerEventTitle());
        Picasso.get()
                .load(careerEvent.getCareerEventImage())
                .into(careerEventDescriptionImage);
        careerEventDescriptionDescription.setText(careerEvent.getCareerEventDescription());
    }

    @Override
    protected void onPause() {
//        g.setShowNewstEvent(false);
        super.onPause();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                y2 = event.getY();
                float deltaY = y2 - y1;
                if (Math.abs(deltaY) > MIN_DISTANCE)
                {
                    onBackPressed();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
