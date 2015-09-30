package com.southwest.southwestapp.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by fernando.raviola on 30/09/2015.
 */
public class RelativeLayoutNoOverlap extends RelativeLayout {
    public RelativeLayoutNoOverlap(Context context) {
        super(context);
    }

    public RelativeLayoutNoOverlap(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RelativeLayoutNoOverlap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RelativeLayoutNoOverlap(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean hasOverlappingRendering() {
        return false;
    }
}
