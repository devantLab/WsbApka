package com.example.peethr.wsbtest.presenters;

import android.app.DownloadManager;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.adapters.CareerEventAdapter;
import com.example.peethr.wsbtest.models.adapters.PlaceAdapter;
import com.example.peethr.wsbtest.models.connection.GetCareerEventData;
import com.example.peethr.wsbtest.models.connection.GetPlaceData;
import com.example.peethr.wsbtest.models.data.events.CareerEvent;
import com.example.peethr.wsbtest.models.data.events.Event;
import com.example.peethr.wsbtest.models.data.places.Place;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.LinkedList;

public class CareerActivity extends AppCompatActivity {

    private TextView emptyRecyclerTextView;

    private LinkedList<CareerEvent> careerEvents = new LinkedList();

    private RecyclerView recyclerView;
    private SwipeRefreshLayout careerSwipeRefresh;

    private CareerEventAdapter careerEventAdapter;
    private Query mRef;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career);

        String title = "Biuro karier";
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);

        SlidrInterface slider = Slidr.attach(this);

        recyclerView = findViewById(R.id.careerRecyclerView);
        careerSwipeRefresh = findViewById(R.id.careerSwipeRefresh);
        emptyRecyclerTextView = findViewById(R.id.careerEmptyRecyclerTextView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CareerActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        careerEventAdapter = new CareerEventAdapter(careerEvents);
        mRef = FirebaseDatabase.getInstance().getReference().child("CareerEvent").limitToLast(50);

        getCareerEventData();
        refreshCareerEvent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private void getCareerEventData(){

        careerEvents.clear();

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CareerEvent careerEvent = new CareerEvent();

                careerEvent = dataSnapshot.getValue(CareerEvent.class);
                careerEvents.add(careerEvent);
                recyclerView.setAdapter(careerEventAdapter);
                refreshCareerEvent();
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

        if (careerEventAdapter.getItemCount() == 0)
        {
            emptyRecyclerTextView.setVisibility(View.VISIBLE);

        } else {

            emptyRecyclerTextView.setVisibility(View.GONE);
            recyclerView.setAdapter(careerEventAdapter);

        }
    }
    private void refreshCareerEvent(){
        careerSwipeRefresh.setColorSchemeColors(Color.BLUE, Color.GRAY, Color.CYAN);
        careerSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                getCareerEventData();
                Toast.makeText(CareerActivity.this, "Odswieżono miejsca", Toast.LENGTH_SHORT).show();
                careerSwipeRefresh.setRefreshing(false);
            }
        });
    }

}
