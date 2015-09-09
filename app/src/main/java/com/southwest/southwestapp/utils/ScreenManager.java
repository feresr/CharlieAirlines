package com.southwest.southwestapp.utils;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.activities.BaseActivity;
import com.southwest.southwestapp.activities.MainActivity;
import com.southwest.southwestapp.fragments.BoardingPassFragment;
import com.southwest.southwestapp.fragments.checkin.CheckInConfirmationFragment;
import com.southwest.southwestapp.fragments.checkin.CheckInFragment;
import com.southwest.southwestapp.fragments.checkin.CheckInSearchFragment;
import com.southwest.southwestapp.fragments.homepage.BigPagerHomeFragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;


/**
 * Created by emiliano.gudino on 02/09/2015.
 */
public class ScreenManager {

    protected void setDefaultAnim(FragmentTransaction ft) {
        ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, android.R.anim.slide_in_left,
                               android.R.anim.slide_out_right);
    }

    public void showCheckInConfirmationScreen(FragmentActivity origin) {
        FragmentTransaction ft = origin.getSupportFragmentManager().beginTransaction();
        setDefaultAnim(ft);
        ft.addToBackStack("checkInConfScreen");
        CheckInConfirmationFragment checkinConfirmationFragment = new CheckInConfirmationFragment();
        ft.replace(R.id.container, checkinConfirmationFragment);
        ft.commit();
    }

    public void showCheckInScreen(FragmentActivity origin) {
        FragmentTransaction ft = origin.getSupportFragmentManager().beginTransaction();
        setDefaultAnim(ft);
        ft.addToBackStack("checkInScreen");
        CheckInFragment checkinFragment = new CheckInFragment();
        ft.replace(R.id.container, checkinFragment);
        ft.commit();
    }

    public void showCheckInSearchScreen(FragmentActivity origin) {
        Intent i = new Intent(origin, BaseActivity.class);
        i.putExtra(BaseActivity.FRAGMENT, CheckInSearchFragment.class);
        origin.startActivity(i);
    }

    public BigPagerHomeFragment showMainScreen(FragmentActivity origin) {
        FragmentTransaction ft = origin.getSupportFragmentManager().beginTransaction();
        BigPagerHomeFragment bigPagerHomeFragment = new BigPagerHomeFragment();
        ft.replace(R.id.container, bigPagerHomeFragment);
        ft.commit();
        return bigPagerHomeFragment;
    }

    public void showBoardingPassScreen(FragmentActivity origin) {
        FragmentTransaction ft = origin.getSupportFragmentManager().beginTransaction();
        setDefaultAnim(ft);
        ft.addToBackStack("boardingPassScreen");
        BoardingPassFragment boardingPassFragment = new BoardingPassFragment();
        ft.replace(R.id.container, boardingPassFragment);
        ft.commit();
    }

    public void showInformationScreen(FragmentActivity origin) {
        FragmentTransaction ft = origin.getSupportFragmentManager().beginTransaction();
        BigPagerHomeFragment homePageFragment = new BigPagerHomeFragment();
        ft.replace(R.id.container, homePageFragment);
        ft.commit();
    }

    public void showMainScreenFromSplash(Activity origin) {
        Intent intent = new Intent(origin, MainActivity.class);
        origin.startActivity(intent);
    }
}
