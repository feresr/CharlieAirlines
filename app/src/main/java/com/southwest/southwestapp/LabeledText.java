package com.southwest.southwestapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Fernando on 31/8/2015.
 */
public class LabeledText extends RelativeLayout {

    private String mainText;
    private String upperText;
    private String bottomText;
    private Drawable imgRight;
    private Drawable imgLeft;
    private int mainTextSize;
    private String mainTextStyle;

    private TextView mTvMain;

    public LabeledText(Context context) {
        super(context);
        initializeViews(context);
    }

    public LabeledText(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttr(context, attrs);
        initializeViews(context);
    }

    public LabeledText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttr(context, attrs);
        initializeViews(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LabeledText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttr(context, attrs);
        initializeViews(context);
    }

    private void parseAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.LabeledText);
        mainText = typedArray.getString(R.styleable.LabeledText_mainText);
        mainTextSize = typedArray.getInteger(R.styleable.LabeledText_mainTextSize, 20);
        mainTextStyle = typedArray.getString(R.styleable.LabeledText_mainTextStyle);
        upperText = typedArray.getString(R.styleable.LabeledText_upperText);
        bottomText = typedArray.getString(R.styleable.LabeledText_bottomText);
        imgRight = typedArray.getDrawable(R.styleable.LabeledText_rightImage);
        imgLeft = typedArray.getDrawable(R.styleable.LabeledText_leftImage);
        typedArray.recycle();
    }


    private void initializeViews(Context context) {
        inflate(context, R.layout.labeled_text, this);
        mTvMain = ((TextView) findViewById(R.id.main_text));

        mTvMain.setText(mainText);
        mTvMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, mainTextSize);

        if(mainTextStyle != null){
            mTvMain.setTypeface(Typeface.DEFAULT);
        } else {
            mTvMain.setTypeface(Typeface.DEFAULT_BOLD);
        }

        ((TextView) findViewById(R.id.above)).setText(upperText);
        if (bottomText != null && !bottomText.isEmpty()) {
            ((TextView) findViewById(R.id.below)).setText(bottomText);
        } else {
            (findViewById(R.id.below)).setVisibility(GONE);
        }
        if (imgRight != null) {
            ((ImageView) findViewById(R.id.img_right)).setImageDrawable(imgRight);
        } else {
            (findViewById(R.id.img_right)).setVisibility(GONE);
        }
        if (imgLeft != null) {
            ((ImageView) findViewById(R.id.img_left)).setImageDrawable(imgLeft);
        } else {
            (findViewById(R.id.img_left)).setVisibility(GONE);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}
