package com.example.peethr.wsbtest.presenters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
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

    Globals g = Globals.getInstance();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description);

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
}
