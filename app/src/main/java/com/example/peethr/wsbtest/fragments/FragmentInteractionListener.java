package com.example.peethr.wsbtest.fragments;

import android.view.MenuItem;

/**
 * Created by thomas on 09.03.18.
 */

public interface FragmentInteractionListener extends DashboardFragment.OnFragmentInteractionListener, EventFragment.OnFragmentInteractionListener, InfoFragment.OnFragmentInteractionListener, UniversityFragment.OnFragmentInteractionListener
{
    @SuppressWarnings("StatementWithEmptyBody")
    boolean onNavigationItemSelected(MenuItem item);
}
