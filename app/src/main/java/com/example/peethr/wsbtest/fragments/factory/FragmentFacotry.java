package com.example.peethr.wsbtest.fragments.factory;

import android.support.v4.app.Fragment;

import com.example.peethr.wsbtest.fragments.DashboardFragment;
import com.example.peethr.wsbtest.fragments.EventFragment;
import com.example.peethr.wsbtest.fragments.InfoFragment;
import com.example.peethr.wsbtest.fragments.UniversityFragment;

/**
 * Created by thomas on 09.03.18.
 */

public class FragmentFacotry extends AbstractFacotry {
    /**
     * implementation of the AbstractFactory pattern is used to create fragments
     */
    @Override
    public Fragment getFragment(int position) {
        String fragment;
        switch (position){
            case 0: fragment = "DashboardFragment";
                break;
            case 1: fragment = "UniversityFragment";
                break;
            case 2: fragment = "EventFragment";
                break;
            case 3: fragment = "InfoFragment";
                break;
                default: fragment = null;
        }

        if(fragment == null){
            return null;
        }

        if(fragment.equalsIgnoreCase("DashboardFragment")){
            return new DashboardFragment();
        }else if(fragment.equalsIgnoreCase("UniversityFragment")){
            return new UniversityFragment();
        }else if(fragment.equalsIgnoreCase("EventFragment")){
            return new EventFragment();
        }else if(fragment.equalsIgnoreCase("InfoFragment")){
            return new InfoFragment();
        }
        return null;
    }
}
