package pl.devant.wsbnotify.fragments;

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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.LinkedList;

import pl.devant.wsbnotify.R;
import pl.devant.wsbnotify.models.adapters.EventAdapter;
import pl.devant.wsbnotify.models.data.events.Event;
import pl.devant.wsbnotify.models.data.weather.Globals;
import pl.devant.wsbnotify.presenters.EventDescription;


public class EventFragment extends Fragment {

    Globals g = Globals.getInstance();

    private TextView emptyRecyclerTextView;

    private LinkedList<Event> events = new LinkedList<>();

    private RecyclerView recyclerView;
    private SwipeRefreshLayout eventSwipeRefresh;

    private Query mRef;
    private EventAdapter eventAdapter;

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

        eventAdapter = new EventAdapter(events);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        mRef = FirebaseDatabase.getInstance().getReference().child("Events").limitToLast(50);
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

        private void getEventsData(final View view) {

        events.clear();

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Event event = new Event();

                event = dataSnapshot.getValue(Event.class);
                events.add(event);
                recyclerView.setAdapter(eventAdapter);
                refreshEvents(view);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                refreshEvents(view);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                refreshEvents(view);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        if (eventAdapter.getItemCount() == 0)
        {
            emptyRecyclerTextView.setVisibility(View.VISIBLE);
        } else {

            emptyRecyclerTextView.setVisibility(View.GONE);

            recyclerView.setAdapter(eventAdapter);
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
