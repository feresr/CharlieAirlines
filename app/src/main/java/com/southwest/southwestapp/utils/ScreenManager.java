package com.southwest.southwestapp.utils;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.activities.BaseActivity;
import com.southwest.southwestapp.fragments.CheckInFragment;
import com.southwest.southwestapp.fragments.CheckInSearchFragment;
import com.southwest.southwestapp.fragments.homepage.HomePageFragment;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;


/**
 * Created by emiliano.gudino on 02/09/2015.
 */
public class ScreenManager {

    public void showCheckInScreen(FragmentActivity origin) {
        FragmentTransaction ft = origin.getSupportFragmentManager().beginTransaction();
        CheckInFragment checkinFragment = new CheckInFragment();
        ft.replace(R.id.container, checkinFragment);
        ft.commit();
    }

    public void showCheckInSearchScreen(FragmentActivity origin) {
        Intent intent = new Intent(origin, BaseActivity.class);
        intent.putExtra(BaseActivity.FRAGMENT, CheckInSearchFragment.class);
        origin.startActivity(intent);
    }

    public void showMainScreen(FragmentActivity origin) {
        FragmentTransaction ft = origin.getSupportFragmentManager().beginTransaction();
        HomePageFragment homePageFragment = new HomePageFragment();
        ft.replace(R.id.container, homePageFragment);
        ft.commit();
    }

}
