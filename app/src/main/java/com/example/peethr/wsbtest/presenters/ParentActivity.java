package com.example.peethr.wsbtest.presenters;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.peethr.wsbtest.fragments.factory.FragmentFacotry;
import com.example.peethr.wsbtest.fragments.FragmentInteractionListener;
import com.example.peethr.wsbtest.fragments.dummy.DummyContent;
import com.example.peethr.wsbtest.models.alerts.NoInternetDialogFragment;
import com.example.peethr.wsbtest.models.connection.CheckInternetConnection;
import com.example.peethr.wsbtest.R;

public class ParentActivity extends AppCompatActivity implements FragmentInteractionListener

{

    // views
    private SeekBar seekbar;
    private ImageView dashTopIcon;
    private ImageView universityTopIcon;
    private ImageView eventTopIcon;
    private ImageView infoTopIcon;
    private ImageView backgroundSelectionDash;
    private ImageView backgroundSelectionWsb;
    private ImageView backgroundSelectionEvent;
    private ImageView backgroundSelectionInfo;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        checkInternetConnection();

        topIconListeners();
        chooseFragment("DashboardFragment");

    }

    // Check if there is internet connection, if not show NoInternetDialogFragment
    private void checkInternetConnection() {

        // Can't use getSystemService in class without activity so we need to pass it
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        CheckInternetConnection connection = new CheckInternetConnection();

        if(!connection.isNetworkAvailable(manager)){
            NoInternetDialogFragment dialog = new NoInternetDialogFragment();
            dialog.show(getFragmentManager(), "NoInternetDialogFragment");
        }

    }


    // Listeners for icons in Top Menu
    private void topIconListeners() {

        dashTopIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimationTopMenu(17);
                clearBackgroundSelection();
                backgroundSelectionDash.setVisibility(View.VISIBLE);
                chooseFragment("DashboardFragment");

            }
        });

        universityTopIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimationTopMenu(39);
                clearBackgroundSelection();
                backgroundSelectionWsb.setVisibility(View.VISIBLE);
                chooseFragment("UniversityFragment");


            }
        });

        eventTopIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimationTopMenu(61);
                clearBackgroundSelection();
                backgroundSelectionEvent.setVisibility(View.VISIBLE);
                chooseFragment("EventFragment");
            }
        });

        infoTopIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimationTopMenu(83);
                clearBackgroundSelection();
                backgroundSelectionInfo.setVisibility(View.VISIBLE);
                chooseFragment("InfoFragment");
            }
        });
    }

    // Initialization of components
    private void findViews() {
        seekbar = findViewById(R.id.seekBar);
        seekbar.setEnabled(false);

        dashTopIcon = findViewById(R.id.dashTopIcon);
        universityTopIcon = findViewById(R.id.universityTopIcon);
        eventTopIcon = findViewById(R.id.eventTopIcon);
        infoTopIcon = findViewById(R.id.infoTopIcon);

        backgroundSelectionDash = findViewById(R.id.backgroundSelectionDash);
        backgroundSelectionWsb = findViewById(R.id.backgroundSelectionWsb);
        backgroundSelectionEvent = findViewById(R.id.backgroundSelectionEvent);
        backgroundSelectionInfo = findViewById(R.id.backgroundSelectionInfo);


    }

    // Clear highlight from Top Menu
    private void clearBackgroundSelection() {
        backgroundSelectionDash.setVisibility(View.GONE);
        backgroundSelectionWsb.setVisibility(View.GONE);
        backgroundSelectionEvent.setVisibility(View.GONE);
        backgroundSelectionInfo.setVisibility(View.GONE);
    }

    // Animated underline in top menu
    private void startAnimationTopMenu(int progressToReach) {
        // will update the "progress" propriety of seekbar until it reaches progress
        ObjectAnimator animation = ObjectAnimator.ofInt(seekbar, "progress", progressToReach);
        animation.setDuration(200); // 0.2 second
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
    private void chooseFragment(String fragmentName) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        FragmentFacotry fragmentFacotry = new FragmentFacotry();
        Fragment fragment = fragmentFacotry.getFragment(fragmentName);
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}