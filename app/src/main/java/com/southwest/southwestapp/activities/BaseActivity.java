package com.southwest.southwestapp.activities;

import com.southwest.southwestapp.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


/**
 * Created by Fernando on 3/9/2015.
 */
public class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    public static final String FRAGMENT = "fragment";
    public static final String FRAGMENT_PARAMS = "fragment_params";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_base);

        if (getIntent().getExtras() != null) {
            Class<?> fragment = (Class<?>)getIntent().getExtras().getSerializable(FRAGMENT);
            if (savedInstanceState == null && fragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                try {
                    Fragment f = (Fragment)fragment.newInstance();
                    Bundle b = getIntent().getExtras().getBundle(FRAGMENT_PARAMS);
                    if (b != null) {
                        f.setArguments(b);
                    }
                    ft.replace(R.id.container, f, f.getClass().getName());
                    ft.commit();
                } catch (InstantiationException e) {
                    Log.e(TAG, "ERROR NAVIGATION [" + e + "]");
                } catch (IllegalAccessException e) {
                    Log.e(TAG, "ERROR NAVIGATION [" + e + "]");
                }
            }
        }
    }
}
