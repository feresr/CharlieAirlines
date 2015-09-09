package com.southwest.southwestapp.utils;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.activities.BaseActivity;
import com.southwest.southwestapp.activities.MainActivity;
import com.southwest.southwestapp.fragments.CheckInConfirmationFragment;
import com.southwest.southwestapp.fragments.CheckInFragment;
import com.southwest.southwestapp.fragments.CheckInSearchFragment;
import com.southwest.southwestapp.fragments.homepage.BigPagerHomeFragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;


/**
 * Created by emiliano.gudino on 02/09/2015.
 */
public class ScreenManager {

    public void showCheckInConfirmationScreen(FragmentActivity origin) {
        FragmentTransaction ft = origin.getSupportFragmentManager().beginTransaction();
        CheckInConfirmationFragment checkinConfirmationFragment = new CheckInConfirmationFragment();
        ft.replace(R.id.container, checkinConfirmationFragment);
        ft.commit();
    }

    public void showCheckInScreen(FragmentActivity origin) {
        FragmentTransaction ft = origin.getSupportFragmentManager().beginTransaction();
        CheckInFragment checkinFragment = new CheckInFragment();
        ft.replace(R.id.container, checkinFragment);
        ft.commit();
    }

    public void showCheckInSearchScreen(FragmentActivity origin) {
        Intent i = new Intent(origin, BaseActivity.class);
        i.putExtra(BaseActivity.FRAGMENT, CheckInSearchFragment.class);
        origin.startActivity(i);
    }

    public void showMainScreen(FragmentActivity origin) {
        FragmentTransaction ft = origin.getSupportFragmentManager().beginTransaction();
        BigPagerHomeFragment bigPagerHomeFragment = new BigPagerHomeFragment();
        ft.replace(R.id.container, bigPagerHomeFragment);
        ft.commit();
    }

    public void showMainScreenFromSplash(Activity origin) {
        Intent intent = new Intent(origin, MainActivity.class);
        origin.startActivity(intent);
    }
}
