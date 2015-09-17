package com.southwest.southwestapp.fragments;


import android.app.Fragment;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.utils.AnimationGenericUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {


    private LinearLayout mProgressContainer;
    private LinearLayout mLogoContainer;

    private ImageView mSwLogo;
    private ProgressBar mProgres;
    private Timer runSplash = new Timer();
    private TimerTask showSplash;

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
        mLogoContainer = (LinearLayout) rootView.findViewById(R.id.logoContainer);
        mProgressContainer = (LinearLayout) rootView.findViewById(R.id.progressContainer);
        mProgres = (ProgressBar) rootView.findViewById(R.id.progressBar);
        mSwLogo = (ImageView) rootView.findViewById(R.id.splashLogo);
        mProgres.setMax(100);
        introAnimate();
        return rootView;
    }


    private void introAnimate() {

        final Animation.AnimationListener progresListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                if (!mProgressContainer.isShown())
                    mProgressContainer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                mSwLogo.startAnimation(AnimationUtils.loadAnimation(AppHelper.getInstance().getBaseContext(), R.anim.pulse));
            }
        };

        Animation.AnimationListener logoListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                if (!mLogoContainer.isShown())
                    mLogoContainer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                AnimationGenericUtils.fadeInAnimation(mProgressContainer, progresListener, AppHelper.getInstance().getBaseContext());
                proceedToMainScreen();
            }
        };

        AnimationGenericUtils.fadeInAnimation(mLogoContainer, logoListener, AppHelper.getInstance().getBaseContext());

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    private void proceedToMainScreen() {
        long delay = 5000;

        showSplash = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    mProgres.setProgress(i);
                }
                AppHelper.screenManager.showLoginScreen(getActivity());
            }
        };

        // Start the timer
        runSplash.schedule(showSplash, delay);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (runSplash != null)
            runSplash.cancel();
    }
}
