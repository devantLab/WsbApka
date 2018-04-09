package com.example.peethr.wsbtest.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;

/**
 * Created by thomas on 09.03.18.
 */

public interface FragmentInteractionListener extends DashboardFragment.OnFragmentInteractionListener, EventFragment.OnFragmentInteractionListener, InfoFragment.OnFragmentInteractionListener, UniversityFragment.OnFragmentInteractionListener
{
    void onCheckedChanged(CompoundButton ButtonView, boolean isChecked);

    @SuppressWarnings("StatementWithEmptyBody")
    boolean onNavigationItemSelected(MenuItem item);

    Dialog onCreateDialog(Bundle savedInstanceState);
}
