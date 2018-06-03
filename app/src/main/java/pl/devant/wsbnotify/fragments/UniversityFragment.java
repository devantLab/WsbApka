package pl.devant.wsbnotify.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pl.devant.wsbnotify.R;
import pl.devant.wsbnotify.presenters.CareerActivity;
import pl.devant.wsbnotify.presenters.HoursActivity;
import pl.devant.wsbnotify.presenters.MapActivity;


public class UniversityFragment extends Fragment implements View.OnClickListener {



    private OnFragmentInteractionListener mListener;

    public UniversityFragment() {
        // Required empty public constructor
    }

    private Button alertUBtn;
    private Button hoursbtn;
    private Button mapBtn;
    private Button careerBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_university, container, false);

        alertUBtn = view.findViewById(R.id.alertUButton);
        alertUBtn.setOnClickListener(this);

        hoursbtn = view.findViewById(R.id.hoursButton);
        hoursbtn.setOnClickListener(this);

        mapBtn = view.findViewById(R.id.mapButton);
        mapBtn.setOnClickListener(this);

        careerBtn = view.findViewById(R.id.careerButton);
        careerBtn.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.alertUButton:
                onAlertButtonClick(view);
                break;
            case R.id.hoursButton:
                onHoursButtonClick(view);
                break;
            case R.id.mapButton:
                onMapButtonClick(view);
                break;
            case R.id.careerButton:
                onCareerButtonClick(view);
                break;
        }
    }

    private void onAlertButtonClick(View view)
    {

    }
    private void onHoursButtonClick(View view)
    {
        Intent intent = new Intent(getContext(), HoursActivity.class);
        startActivity(intent);
    }
    private void onMapButtonClick(View view)
    {
        Intent intent = new Intent(getContext(), MapActivity.class);
        startActivity(intent);
    }
    private void onCareerButtonClick(View view)
    {
        Intent intent = new Intent(getContext(), CareerActivity.class);
        startActivity(intent);
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
