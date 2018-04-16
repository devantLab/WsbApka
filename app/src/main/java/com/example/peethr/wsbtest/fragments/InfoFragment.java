package com.example.peethr.wsbtest.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.presenters.MainActivity;
import com.example.peethr.wsbtest.presenters.PlacesActivity;
import com.example.peethr.wsbtest.presenters.RecentAppActivity;


public class InfoFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private Button placesBtn;
    private Button recappButton;

    public InfoFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        findViews(view);

        placesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlacesActivity.class);
                startActivity(intent);
            }
        });

        recappButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RecentAppActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void findViews(View view){
        placesBtn = view.findViewById(R.id.placesButton);
        recappButton = view.findViewById(R.id.recappButton);
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
