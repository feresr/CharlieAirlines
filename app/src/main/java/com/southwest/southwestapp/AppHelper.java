package com.southwest.southwestapp;

import android.app.Application;

import com.southwest.southwestapp.utils.ScreenManager;

/**
 * Created by Fernando on 31/8/2015.
 */
public class AppHelper extends Application {
    private static AppHelper instance;
    public static final ScreenManager screenManager = new ScreenManager();

    public static AppHelper getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
