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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventFragment extends Fragment {

    private String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam molestie sapien eleifend libero aliquet molestie. Morbi et mi malesuada, posuere lectus in, pulvinar nisi. Donec a aliquet est. Morbi quis eros sed diam aliquam dictum ac non elit. Donec at nisi sed purus varius rutrum nec eget enim. Pellentesque ac ligula non lacus iaculis suscipit. ";

    Globals g = Globals.getInstance();

    private TextView emptyRecyclerTextView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private LinkedList<Event> events = new LinkedList<>();

    private RecyclerView recyclerView;
    private SwipeRefreshLayout eventSwipeRefresh;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventFragment newInstance(String param1, String param2) {
        EventFragment fragment = new EventFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        if (g.getShowNewstEvent())
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
