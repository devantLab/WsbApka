package com.example.peethr.wsbtest.presenters;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.adapters.PlaceAdapter;
import com.example.peethr.wsbtest.models.connection.GetEventData;
import com.example.peethr.wsbtest.models.connection.GetPlaceData;
import com.example.peethr.wsbtest.models.data.places.Place;

import java.util.LinkedList;

public class PlacesActivity extends AppCompatActivity {

    private TextView emptyRecyclerTextView;

    private LinkedList<Place> places = new LinkedList();

    private RecyclerView recyclerView;
    private SwipeRefreshLayout placeSwipeRefresh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        recyclerView = findViewById(R.id.placeRecyclerView);
        placeSwipeRefresh = findViewById(R.id.placeSwipeRefresh);
        emptyRecyclerTextView = findViewById(R.id.placeEmptyRecyclerTextView);

        getPlacesData();
        refreshPlace();
    }

    private void getPlacesData(){
        GetPlaceData getPlaceData = new GetPlaceData();
        places = getPlaceData.getDataFromInternet();

        PlaceAdapter adapter = new PlaceAdapter(places);
        if (adapter.getItemCount() == 0)
        {
            emptyRecyclerTextView.setVisibility(View.VISIBLE);

        } else {

            emptyRecyclerTextView.setVisibility(View.GONE);

            recyclerView.setAdapter(adapter);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PlacesActivity.this);
            recyclerView.setLayoutManager(layoutManager);
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
