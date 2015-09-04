package com.southwest.southwestapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.activities.MainActivity;
import com.southwest.southwestapp.activities.SplashActivity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {


    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);


    }

    @Override
    public void onResume() {
        super.onResume();
        proceedToMainScreen();
    }

    
    private void proceedToMainScreen() {
        long delay = 5000;
        Timer RunSplash = new Timer();

        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                AppHelper.screenManager.showMainScreenFromSplash(getActivity());
            }
        };

        // Start the timer
        RunSplash.schedule(ShowSplash, delay);
    }
}