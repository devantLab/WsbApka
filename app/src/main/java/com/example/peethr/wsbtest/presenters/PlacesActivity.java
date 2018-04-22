package com.example.peethr.wsbtest.presenters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.adapters.PlaceAdapter;
import com.example.peethr.wsbtest.models.connection.GetEventData;
import com.example.peethr.wsbtest.models.connection.GetPlaceData;
import com.example.peethr.wsbtest.models.data.places.Place;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.LinkedList;

public class PlacesActivity extends AppCompatActivity {

    private TextView emptyRecyclerTextView;

    private LinkedList<Place> places = new LinkedList();

    private RecyclerView recyclerView;
    private SwipeRefreshLayout placeSwipeRefresh;

    private PlaceAdapter placeAdapter;
    private Query mRef;

    private Toolbar toolbar;

    private SlidrInterface slidr;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        String title = "Polecane miejsca";
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);

        recyclerView = findViewById(R.id.placeRecyclerView);
        placeSwipeRefresh = findViewById(R.id.placeSwipeRefresh);
        emptyRecyclerTextView = findViewById(R.id.placeEmptyRecyclerTextView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PlacesActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        placeAdapter = new PlaceAdapter(places);
        mRef = FirebaseDatabase.getInstance().getReference().child("Places");

        slidr = Slidr.attach(this);

        getPlacesData();
        refreshPlace();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private void getPlacesData(){

        places.clear();

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Place place = new Place();
                place = dataSnapshot.getValue(Place.class);
                places.add(place);
                recyclerView.setAdapter(placeAdapter);
                refreshPlace();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (placeAdapter.getItemCount() == 0)
        {
            emptyRecyclerTextView.setVisibility(View.VISIBLE);

        } else {

            emptyRecyclerTextView.setVisibility(View.GONE);

            recyclerView.setAdapter(placeAdapter);
        }
    }
    private void refreshPlace(){
        placeSwipeRefresh.setColorSchemeColors(Color.BLUE, Color.GRAY, Color.CYAN);
        placeSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                getPlacesData();
                Toast.makeText(PlacesActivity.this, "Odswieżono miejsca", Toast.LENGTH_SHORT).show();
                placeSwipeRefresh.setRefreshing(false);
            }
        });
    }


}
