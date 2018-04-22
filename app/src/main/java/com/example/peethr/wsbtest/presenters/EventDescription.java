package com.example.peethr.wsbtest.presenters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.events.Event;
import com.example.peethr.wsbtest.models.data.weather.Globals;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class EventDescription extends AppCompatActivity implements OnMapReadyCallback {

    private Event event;

    private TextView eventDescriptionTitle;
    private TextView eventDescriptionDescription;
    private ImageView eventDescriptionImage;
    private Button eventPageButton;
    private TextView eventDescriptionPlace;
    private TextView eventDescriptionDate;
    private TextView eventDescriptionTime;

    private MapView mapView;
    private GoogleMap mGooglemap;

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
        eventDescriptionDate = findViewById(R.id.eventDescriptionDate);
        eventDescriptionPlace = findViewById(R.id.placeDescriptionPlace);
        eventDescriptionTime = findViewById(R.id.eventDescriptionTime);
        eventDescriptionDescription = findViewById(R.id.eventDescriptionDescription);
        eventPageButton = findViewById(R.id.eventPageButton);

        mapView = findViewById(R.id.mapView);
        if(mapView!=null)
        {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }



//        eventPageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri uri = Uri.parse(event.getEventLink());
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
//            }
//        });

        Intent intent = getIntent();
        event = intent.getParcelableExtra("clickedEvent");

        updateData();
//        animateIn();

//        Log.i("LINK", event.getEventLink()+"");

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
//        String eventPlace = event.getEventCity()+","+event.getEventStreet();
        eventDescriptionTitle.setText(event.getEventTitle());
        Picasso.get()
                .load(event.getEventImage())
                .into(eventDescriptionImage);
        eventDescriptionDescription.setText(event.getEventDescription());
        if(event.getEventTimeEnd()!="0"){
            eventDescriptionTime.setText(event.getEventTimeStart()+"-"+event.getEventTimeEnd());
        }
        else {
            eventDescriptionTime.setText(event.getEventTimeStart());
        }
//        eventDescriptionPlace.setText(eventPlace);
        eventDescriptionDate.setText(event.getEventDate());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        double lati = Double.parseDouble(event.getEventLatitude());
        double longi = Double.parseDouble(event.getEventLongitude());

        MapsInitializer.initialize(this);
        mGooglemap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.addMarker(new MarkerOptions().position(
                new LatLng(lati, longi)).title(event.getEventTitle()));

        CameraPosition cameraPosition = CameraPosition.builder().
                target(new LatLng(lati, longi)).zoom(16).bearing(0).tilt(45).build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
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
