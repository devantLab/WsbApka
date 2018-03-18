package com.example.peethr.wsbtest.fragments;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.weather.Globals;
import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import static java.lang.Math.floor;


public class DashboardFragment extends Fragment {

    private ImageView arrowAlert;
    private Button alertButton;
    private TextView degrees;
    private TextView weatherMessage;
    private ExpandableRelativeLayout expandableRelativeLayout;

    private OnFragmentInteractionListener mListener;

    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance(String param1, String param2) {

        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        findViews(view);
        updateWeather();
        expandableRelativeLayout.toggle();
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Animation of arrow and expanding button in dash
                getArrowAnimation();

            }
        });

        // Inflate the layout for this fragment

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


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

        // Starts animation
    private void getArrowAnimation() {

        if(!expandableRelativeLayout.isExpanded())
        {
            // When animation starts / finishes we change radius of AlertButton
            expandableRelativeLayout.setListener(new ExpandableLayoutListener() {
                @Override
                public void onAnimationStart() {

                    if (!expandableRelativeLayout.isExpanded())
                    {
                        alertButton.setBackgroundResource(R.drawable.dashboard_alert_button_clicked);
                        animateArrow(90f, 270f);

                        // needed for shadows
                        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) alertButton.getLayoutParams();
                        params.setMargins(0,26,0,0);
                        alertButton.setLayoutParams(params);
                    } else
                    {
                        animateArrow(270f,90f);
                    }
                }

                @Override
                public void onAnimationEnd() {

                    if (!expandableRelativeLayout.isExpanded())
                    {
                        alertButton.setBackgroundResource(R.drawable.dashboard_alert_button_unclicked);

                        // needed for shadows
                        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) alertButton.getLayoutParams();
                        params.setMargins(0,26,0,26);
                        alertButton.setLayoutParams(params);
                    }
                }

                @Override
                public void onPreOpen() {
                }

                @Override
                public void onPreClose() {
                }

                @Override
                public void onOpened() {
                }

                @Override
                public void onClosed() {
                }
            });
            expandableRelativeLayout.toggle();
        }
        else {
            expandableRelativeLayout.toggle();
        }
    }

    // Animated arrow when extending button in dash
    private void animateArrow(float startPosition, float finishPosition) {

        ObjectAnimator animation = ObjectAnimator.ofFloat(arrowAlert, "rotation", startPosition, finishPosition);
        animation.setDuration(500); // 0.5 second
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
    }
    private void findViews(View view){

        alertButton = view.findViewById(R.id.newAlertButton);
        degrees = view.findViewById(R.id.degrees);
        weatherMessage = view.findViewById(R.id.weatherMessage);
        arrowAlert = view.findViewById(R.id.arrowAlert);
        expandableRelativeLayout = view.findViewById(R.id.expandableLayout1);
    }

    // Update view with variables loaded in splashScreen
    private void updateWeather() {

        Globals g = Globals.getInstance();
        // if data loaded show it on weatherFragment
        if (g.getIfWeatherUpdated())
        {
            int temp =(int) floor((g.getTemperature()-32)*5/9);
            degrees.setText(String.valueOf(temp)+ "°");
            weatherMessage.setText(g.getSummary());
        }
        // if data not loaded
        else
        {
            degrees.setText(":(");
            weatherMessage.setText("No internet connection");
        }

    }
}
