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
import android.widget.Toast;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class PlaceDescription extends AppCompatActivity implements OnMapReadyCallback {

    private Place place;

    private float y1, y2;
    static final int MIN_DISTANCE = 150;

    private Toolbar toolbar;

    private TextView placeDescriptionTitle;
    private TextView placeDescriptionDescription;
    private TextView getPlaceDescriptionPlace;
    private ImageView placeDescriptionImage;

    private MapView mapView;
    private GoogleMap mGooglemap;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_description);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getBackground().setAlpha(1);

        placeDescriptionTitle = findViewById(R.id.placeDescriptionTitle);
        placeDescriptionDescription = findViewById(R.id.placeDescriptionDescription);
        placeDescriptionImage = findViewById(R.id.placeDescriptionImage);
        getPlaceDescriptionPlace = findViewById(R.id.placeDescriptionPlace);
        mapView = findViewById(R.id.mapView);
        if(mapView!=null)
        {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }


        Intent intent = getIntent();
        place = intent.getParcelableExtra("clickedPlace");

//        animateIn();
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

        AnimatorSet animatorInDescription = new AnimatorSet();
        animatorInDescription.setDuration(100);

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
        getPlaceDescriptionPlace.setText((place.getPlaceCity()+", "+place.getPlaceStreet()));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        double lati = Double.parseDouble(place.getPlaceLatitude());
        double longi = Double.parseDouble(place.getPlaceLongitude());

        MapsInitializer.initialize(this);
        mGooglemap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.addMarker(new MarkerOptions().position(
                new LatLng(lati, longi)).title(place.getPlaceTitle()));

        CameraPosition cameraPosition = CameraPosition.builder().
                target(new LatLng(lati, longi)).zoom(16).bearing(0).tilt(45).build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
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
