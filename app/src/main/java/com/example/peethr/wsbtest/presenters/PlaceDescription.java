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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.places.Place;
import com.squareup.picasso.Picasso;

public class PlaceDescription extends AppCompatActivity {

    private Place place;

    private Toolbar toolbar;

    private TextView placeDescriptionTitle;
    private TextView placeDescriptionDescription;
    private ImageView placeDescriptionImage;
    private Button placeNavigateButton;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_description);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getBackground().setAlpha(0);

        placeDescriptionTitle = findViewById(R.id.placeDescriptionTitle);
        placeDescriptionDescription = findViewById(R.id.placeDescriptionDescription);
        placeDescriptionImage = findViewById(R.id.placeDescriptionImage);
        placeNavigateButton = findViewById(R.id.placeNavigateButton);

        Intent intent = getIntent();
        place = intent.getParcelableExtra("clickedPlace");

        animateIn();
        updateData();

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void animateIn() {

        ObjectAnimator titleAlpha = ObjectAnimator.ofFloat(placeDescriptionTitle, "alpha", 0, 1);
        ObjectAnimator titleMove = ObjectAnimator.ofFloat(placeDescriptionTitle, "translationY",  170);

        AnimatorSet animatorInTitle = new AnimatorSet();
        animatorInTitle.playTogether(titleAlpha, titleMove);
        animatorInTitle.setStartDelay(200);
        animatorInTitle.setDuration(200);

        ObjectAnimator descriptionAlpha = ObjectAnimator.ofFloat(placeDescriptionDescription, "alpha", 0, 1);
        ObjectAnimator descriptionMove = ObjectAnimator.ofFloat(placeDescriptionDescription, "translationY", 150);
        ObjectAnimator eventPageButtonMove = ObjectAnimator.ofFloat(placeNavigateButton, "translationY", 0);

        AnimatorSet animatorInDescription = new AnimatorSet();
        animatorInDescription.setDuration(100);
        animatorInDescription.playTogether(descriptionAlpha, descriptionMove, eventPageButtonMove);

        AnimatorSet animationIn = new AnimatorSet();
        animationIn.playSequentially(animatorInTitle, animatorInDescription);
        animationIn.start();

    }

    private void updateData() {
        placeDescriptionTitle.setText(place.getPlaceTitle());
        Picasso.get()
                .load(place.getPlaceImage())
                .into(placeDescriptionImage);
        placeDescriptionDescription.setText(place.getPlaceDescription());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
