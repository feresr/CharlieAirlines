package com.southwest.southwestapp.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {

    private ImageView mSwLogo;
    private Timer runSplash = new Timer();

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_splash, container, false);
        mSwLogo = (ImageView) rootView.findViewById(R.id.splashLogo);
        mSwLogo.startAnimation(AnimationUtils.loadAnimation(AppHelper.getInstance().getBaseContext(), R.anim.pulse));
        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();
        proceedToMainScreen();
    }


    private void proceedToMainScreen() {
        long delay = 9000;

        TimerTask showSplash = new TimerTask() {
            @Override
            public void run() {
                AppHelper.screenManager.showLoginScreen(getActivity());
            }
        };

        // Start the timer
        runSplash.schedule(showSplash, delay);
    }

    @Override
    public void onPause() {
        super.onPause();
        runSplash.cancel();
    }
}
