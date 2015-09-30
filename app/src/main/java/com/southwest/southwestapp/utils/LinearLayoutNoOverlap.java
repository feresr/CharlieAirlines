package com.southwest.southwestapp.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by fernando.raviola on 30/09/2015.
 */
public class LinearLayoutNoOverlap extends LinearLayout {
    public LinearLayoutNoOverlap(Context context) {
        super(context);
    }

    public LinearLayoutNoOverlap(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearLayoutNoOverlap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LinearLayoutNoOverlap(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //Do not use the hardware layer to gain performance (for more info: https://www.youtube.com/watch?list=PLWz5rJ2EKKc9CBxr3BVjPTPoDPLdPIFCE&v=wIy8g8yNhNk)
    @Override
    public boolean hasOverlappingRendering() {
        return false;
    }
}
