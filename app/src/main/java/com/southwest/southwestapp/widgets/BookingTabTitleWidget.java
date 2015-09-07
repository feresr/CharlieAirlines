package com.southwest.southwestapp.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.utils.AnimationGenericUtils;


/**
 * Created by luisalfonsobejaranosanchez on 9/3/15.
 */
public class BookingTabTitleWidget extends LinearLayout {

    private int animationTime;
    private String mainTitle;

    private TextView mTitle;
    private ImageView icon;

    private TypedValue iconResource;
    private boolean hasValue;


    public BookingTabTitleWidget(Context context) {
        super(context);
        initializeViews(context);
    }


    public BookingTabTitleWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttr(context, attrs);
        initializeViews(context);
    }

    public BookingTabTitleWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttr(context, attrs);
        initializeViews(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BookingTabTitleWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttr(context, attrs);
        initializeViews(context);
    }

    private void parseAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BookingTabTitleWidget, 0, 0);
        mainTitle = typedArray.getString(R.styleable.BookingTabTitleWidget_bookingTitle);
        animationTime = typedArray.getInteger(R.styleable.BookingTabTitleWidget_animationTime, 500);
        iconResource = new TypedValue();
        hasValue = typedArray.getValue(R.styleable.BookingTabTitleWidget_bookingIcon, iconResource);
        typedArray.recycle();
    }

    private void initializeViews(Context context) {
        inflate(context, R.layout.booking_tab_title, this);
        mTitle = (TextView) findViewById(R.id.booking_tab_main_title);
        mTitle.setText(mainTitle);
        icon = (ImageView) findViewById(R.id.booking_tab);
        if (hasValue) {
            icon.setBackgroundResource(iconResource.resourceId);
        }
    }


    public void setSelected() {
        AnimationGenericUtils.blinkColorAnimation(mTitle, 10, mTitle.getCurrentTextColor(), Color.BLACK);
        icon.setBackgroundResource(android.R.drawable.ic_menu_agenda);
    }

    public void setDeselected() {
        icon.setBackgroundResource(R.drawable.ic_launcher);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }


}
