package com.example.peethr.wsbtest.presenters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.events.Event;
import com.example.peethr.wsbtest.models.data.weather.Globals;
import com.squareup.picasso.Picasso;

public class EventDescription extends AppCompatActivity {

    private Event event;

    private TextView eventDescriptionTitle;
    private TextView eventDescriptionDescription;
    private ImageView eventDescriptionImage;
    private Button eventPageButton;

    private float y1, y2;
    static final int MIN_DISTANCE = 150;

    private Toolbar toolbar;

    Globals g = Globals.getInstance();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getBackground().setAlpha(0);


        eventDescriptionTitle = findViewById(R.id.eventDescriptionTitle);
        eventDescriptionImage = findViewById(R.id.eventDescriptionImage);
        eventDescriptionDescription = findViewById(R.id.eventDescriptionDescription);
        eventPageButton = findViewById(R.id.eventPageButton);

        Intent intent = getIntent();
        event = intent.getParcelableExtra("clickedEvent");

        updateData();
        animateIn();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void animateIn() {

        ObjectAnimator titleAlpha = ObjectAnimator.ofFloat(eventDescriptionTitle, "alpha", 0, 1);
        ObjectAnimator titleMove = ObjectAnimator.ofFloat(eventDescriptionTitle, "translationY",  170);

        AnimatorSet animatorInTitle = new AnimatorSet();
        animatorInTitle.playTogether(titleAlpha, titleMove);
        animatorInTitle.setStartDelay(200);
        animatorInTitle.setDuration(200);

        ObjectAnimator descriptionAlpha = ObjectAnimator.ofFloat(eventDescriptionDescription, "alpha", 0, 1);
        ObjectAnimator descriptionMove = ObjectAnimator.ofFloat(eventDescriptionDescription, "translationY", 150);
        ObjectAnimator eventPageButtonMove = ObjectAnimator.ofFloat(eventPageButton, "translationY", 0);

        AnimatorSet animatorInDescription = new AnimatorSet();
        animatorInDescription.setDuration(100);
        animatorInDescription.playTogether(descriptionAlpha, descriptionMove, eventPageButtonMove);

        AnimatorSet animationIn = new AnimatorSet();
        animationIn.playSequentially(animatorInTitle, animatorInDescription);
        animationIn.start();

    }

    private void updateData() {
        eventDescriptionTitle.setText(event.getEventTitle());
        Picasso.get()
                .load(event.getEventImage())
                .into(eventDescriptionImage);
        eventDescriptionDescription.setText(event.getEventDescription());
    }

    @Override
    protected void onPause() {
        g.setShowNewstEvent(false);
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
