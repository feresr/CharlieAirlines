package com.southwest.southwestapp.utils;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;


/**
 * Created by emiliano.gudino on 21/09/2015.
 */
public abstract class ShakeListener implements SensorEventListener {

    private static final float ON_SHAKE_CONSTANT = 8f;

    private float mAcceleration = 0.00f; // acceleration apart from gravity
    private float mAccelerationCurrent = SensorManager.GRAVITY_EARTH; // current acceleration including gravity
    private float mAccelerationLast = SensorManager.GRAVITY_EARTH; // last acceleration including gravity

    private int mShakesCounter = 0;
    private long mShakeTimestamp;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;

    @Override
    public void onSensorChanged(SensorEvent sEvent) {

        float x = sEvent.values[0];
        float y = sEvent.values[1];
        float z = sEvent.values[2];

        mAccelerationLast = mAccelerationCurrent;
        mAccelerationCurrent = (float)Math.sqrt((double)(x * x + y * y + z * z));

        float delta = mAccelerationCurrent - mAccelerationLast;
        mAcceleration = mAcceleration * 0.9f + delta + 0.1f; // perform low-cut filter

        if (mAcceleration > ON_SHAKE_CONSTANT) {
            Log.e("Acceleration", String.valueOf(mAcceleration));
            mShakesCounter++;

            final long now = System.currentTimeMillis();

            // reset the shake count after 3 seconds of no shakes
            if (mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now) {
                mShakesCounter = 0;
            }

            if (mShakesCounter == 2) {
                onShake();
                mShakesCounter = 0;
            }

            mShakeTimestamp = now;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public abstract void onShake();
}
