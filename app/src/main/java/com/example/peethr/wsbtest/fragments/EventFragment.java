package com.example.peethr.wsbtest.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.adapters.EventAdapter;
import com.example.peethr.wsbtest.models.connection.GetEventData;
import com.example.peethr.wsbtest.models.data.events.Event;
import com.example.peethr.wsbtest.models.data.weather.Globals;
import com.example.peethr.wsbtest.presenters.EventDescription;

import java.util.LinkedList;



public class EventFragment extends Fragment {

    Globals g = Globals.getInstance();

    private TextView emptyRecyclerTextView;



    private LinkedList<Event> events = new LinkedList<>();

    private RecyclerView recyclerView;
    private SwipeRefreshLayout eventSwipeRefresh;


    private OnFragmentInteractionListener mListener;

    public EventFragment() {
        // Required empty public constructor
    }


    public static EventFragment newInstance() {
        EventFragment fragment = new EventFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_event, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        eventSwipeRefresh = view.findViewById(R.id.eventSwipeRefresh);
        emptyRecyclerTextView = view.findViewById(R.id.emptyRecyclerTextView);

        // get events data and put them in recycler
        getEventsData(view);

        // check if user is redirected from dashboard's event button onclick
        checkIfRedirect();

        // Swipe to refresh configuration
        refreshEvents(view);

        return view;
    }

    private void checkIfRedirect() {
        if (g.getShowNewstEvent() && !events.isEmpty())
        {
            Intent intent = new Intent(getContext(), EventDescription.class);
            intent.putExtra("clickedEvent", events.get(0));
            startActivity(intent);
        }
    }

    private void getEventsData(View view) {
        GetEventData getEventData = new GetEventData();
        events = getEventData.getDataFromInternet();

        EventAdapter adapter = new EventAdapter(events);

        if (adapter.getItemCount() == 0)
        {
            emptyRecyclerTextView.setVisibility(View.VISIBLE);

        } else {

            emptyRecyclerTextView.setVisibility(View.GONE);

            recyclerView.setAdapter(adapter);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(layoutManager);
        }
    }

    private void refreshEvents(final View view) {
        eventSwipeRefresh.setColorSchemeColors(Color.BLUE, Color.GRAY, Color.CYAN);
        eventSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                getEventsData(view);
                Toast.makeText(view.getContext(), "Odswieżono wydarzenia", Toast.LENGTH_SHORT).show();
                eventSwipeRefresh.setRefreshing(false);
            }
        });
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
