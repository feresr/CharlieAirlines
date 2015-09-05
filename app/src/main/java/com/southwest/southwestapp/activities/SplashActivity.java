package com.southwest.southwestapp.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.SplashFragment;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       if (savedInstanceState == null) {
            // Show the splash screen at the beginning
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            SplashFragment splashFragment = new SplashFragment();
            fragmentTransaction.add(R.id.container, splashFragment);
            fragmentTransaction.commit();
        }
    }

}
