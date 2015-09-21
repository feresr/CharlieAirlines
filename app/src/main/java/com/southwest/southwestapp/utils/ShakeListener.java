package com.southwest.southwestapp.utils;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


/**
 * Created by emiliano.gudino on 21/09/2015.
 */
public abstract class ShakeListener implements SensorEventListener {

    private static final int ON_SHAKE_CONSTANT = 12;

    private float mAcceleration = 0.00f; // acceleration apart from gravity
    private float mAccelerationCurrent = SensorManager.GRAVITY_EARTH; // current acceleration including gravity
    private float mAccelerationLast = SensorManager.GRAVITY_EARTH; // last acceleration including gravity

    @Override
    public void onSensorChanged(SensorEvent sEvent) {

        float x = sEvent.values[0];
        float y = sEvent.values[1];
        float z = sEvent.values[2];

        mAccelerationLast = mAccelerationCurrent;
        mAccelerationCurrent = (float)Math.sqrt((double)(x * x + y * y + z * z));

        float delta = mAccelerationCurrent - mAccelerationLast;
        mAcceleration = mAcceleration * 0.9f + delta + 0.1f; // perform low-cut filter

        if (mAccelerationCurrent > ON_SHAKE_CONSTANT) {
            onShake();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public abstract void onShake();
}
