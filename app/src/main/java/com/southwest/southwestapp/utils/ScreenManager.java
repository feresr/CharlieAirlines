package com.southwest.southwestapp.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.inputmethod.InputMethodManager;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.activities.BaseActivity;
import com.southwest.southwestapp.activities.MainActivity;
import com.southwest.southwestapp.activities.OCRActivity;
import com.southwest.southwestapp.fragments.BoardingPassFragment;
import com.southwest.southwestapp.fragments.LoginFragment;
import com.southwest.southwestapp.fragments.checkin.CheckInConfirmationFragment;
import com.southwest.southwestapp.fragments.checkin.CheckInFragment;
import com.southwest.southwestapp.fragments.checkin.CheckInSearchFragment;
import com.southwest.southwestapp.fragments.emergency.EmergencyContactFragment;
import com.southwest.southwestapp.fragments.emergency.EmergencyContactListFragment;
import com.southwest.southwestapp.fragments.homepage.BigPagerHomeFragment;

import android.content.Context;
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
        ft.addToBackStack("checkInConfScreen");
        CheckInConfirmationFragment checkinConfirmationFragment = new CheckInConfirmationFragment();
        ft.replace(R.id.container, checkinConfirmationFragment);
        ft.commit();
    }

    public void showCheckInScreen(FragmentActivity origin) {
        FragmentTransaction ft = origin.getSupportFragmentManager().beginTransaction();
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

    public void showEmergencyContact(FragmentActivity origin) {
        FragmentTransaction ft = origin.getSupportFragmentManager().beginTransaction();
        ft.addToBackStack("emergencyContact");
        EmergencyContactFragment emergencyContactFragment = new EmergencyContactFragment();
        ft.replace(R.id.container, emergencyContactFragment);
        ft.commit();
    }


    public void showEmergencyContactList(FragmentActivity origin) {
        FragmentTransaction ft = origin.getSupportFragmentManager().beginTransaction();
        ft.addToBackStack("emergencyContactList");
        EmergencyContactListFragment emergencyContactListFragment = new EmergencyContactListFragment();
        ft.replace(R.id.container, emergencyContactListFragment);
        ft.commit();
    }

    public void hideSoftKeyboard(Activity activity) {

        if (activity != null && !activity.isFinishing()) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null && activity.getCurrentFocus() != null && activity.getCurrentFocus().getWindowToken() != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    public void showLoginScreen(Activity origin) {
        Intent i = new Intent(origin, BaseActivity.class);
        i.putExtra(BaseActivity.FRAGMENT, LoginFragment.class);
        origin.startActivity(i);
    }

    public void showMainScreen(FragmentActivity origin) {
        FragmentTransaction ft = origin.getSupportFragmentManager().beginTransaction();
        BigPagerHomeFragment bigPagerHomeFragment = new BigPagerHomeFragment();
        ft.replace(R.id.container, bigPagerHomeFragment);
        ft.commit();
    }

    public void showBoardingPassScreen(FragmentActivity origin) {
        FragmentTransaction ft = origin.getSupportFragmentManager().beginTransaction();
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

    public void showMainScreenFromLogIn(Activity origin) {
        Intent intent = new Intent(origin, MainActivity.class);
        origin.startActivity(intent);
        origin.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void showScanPassport(Activity origin){
        Intent intent = new Intent(origin, OCRActivity.class);
        origin.startActivity(intent);
    }

    public void showBoardingAfterShaking(Context context) {
        Intent intent = new Intent(context, BaseActivity.class);
        intent.putExtra(BaseActivity.FRAGMENT, BoardingPassFragment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(BoardingPassFragment.BOARDING_PASS_SHOWN, "boardingPassShown");
        context.startActivity(intent);
    }
}
