package com.southwest.southwestapp.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.southwest.southwestapp.R;

/**
 * Created by armando.dominguez on 09/09/2015.
 */
public class CustomProgress extends ProgressDialog {

    private ImageView im;
    private AnimationDrawable ad;

    public CustomProgress(Context context) {
        super(context);
    }

    public CustomProgress(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.progress_view);

        im = (ImageView) findViewById(R.id.imageView);
        im.setBackgroundResource(R.drawable.drawable_anim);

        ad = (AnimationDrawable) im.getBackground();
    }

    @Override
    public void show() {
        super.show();
        ad.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        ad.stop();
    }
}
