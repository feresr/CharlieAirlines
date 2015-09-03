package com.southwest.southwestapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.southwest.southwestapp.R;

/**
 * Created by Fernando on 3/9/2015.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
