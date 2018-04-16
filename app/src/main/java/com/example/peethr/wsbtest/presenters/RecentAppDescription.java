package com.example.peethr.wsbtest.presenters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import com.example.peethr.wsbtest.models.data.places.Place;
import com.example.peethr.wsbtest.models.data.recentApps.RecentApp;
import com.squareup.picasso.Picasso;

public class RecentAppDescription extends AppCompatActivity {

    private RecentApp recentApp;

    private float y1, y2;
    static final int MIN_DISTANCE = 150;

    private Toolbar toolbar;

    private TextView recentAppDescriptionTitle;
    private TextView recentAppDescriptionDescription;
    private ImageView recentAppDescriptionImage;
    private Button recentAppNavigateButton;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_app_description);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getBackground().setAlpha(1);

        recentAppDescriptionTitle = findViewById(R.id.recentAppDescriptionTitle);
        recentAppDescriptionDescription = findViewById(R.id.recentAppDescriptionDescription);
        recentAppDescriptionImage = findViewById(R.id.recentAppDescriptionImage);
        recentAppNavigateButton = findViewById(R.id.recentAppNavigateButton);

        Intent intent = getIntent();
        recentApp = intent.getParcelableExtra("clickedRecentApp");

        animateIn();
        updateData();

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void animateIn() {

        ObjectAnimator titleAlpha = ObjectAnimator.ofFloat(recentAppDescriptionTitle, "alpha", 0, 1);
        ObjectAnimator titleMove = ObjectAnimator.ofFloat(recentAppDescriptionTitle, "translationY",  170);

        AnimatorSet animatorInTitle = new AnimatorSet();
        animatorInTitle.playTogether(titleAlpha, titleMove);
        animatorInTitle.setStartDelay(200);
        animatorInTitle.setDuration(200);

        ObjectAnimator descriptionAlpha = ObjectAnimator.ofFloat(recentAppDescriptionDescription, "alpha", 0, 1);
        ObjectAnimator descriptionMove = ObjectAnimator.ofFloat(recentAppDescriptionDescription, "translationY", 150);
        ObjectAnimator eventPageButtonMove = ObjectAnimator.ofFloat(recentAppNavigateButton, "translationY", 0);

        AnimatorSet animatorInDescription = new AnimatorSet();
        animatorInDescription.setDuration(100);
        animatorInDescription.playTogether(descriptionAlpha, descriptionMove, eventPageButtonMove);

        AnimatorSet animationIn = new AnimatorSet();
        animationIn.playSequentially(animatorInTitle, animatorInDescription);
        animationIn.start();

    }

    private void updateData() {
        recentAppDescriptionTitle.setText(recentApp.getRecentAppTitle());
        Picasso.get()
                .load(recentApp.getRecentAppImage())
                .into(recentAppDescriptionImage);
        recentAppDescriptionDescription.setText(recentApp.getRecentAppDescription());
    }

    @Override
    protected void onPause() {
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
