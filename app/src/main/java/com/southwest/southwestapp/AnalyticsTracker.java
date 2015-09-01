package com.southwest.southwestapp;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by Fernando on 31/8/2015.
 */
public class AnalyticsTracker {
    private static AnalyticsTracker instance = new AnalyticsTracker();
    private Tracker mTracker;

    private AnalyticsTracker() {
    }

    public static AnalyticsTracker getInstance() {
        return instance;
    }

    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     *
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(AppHelper.getInstance());
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }
}
