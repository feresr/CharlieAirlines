package com.southwest.southwestapp.activities;

import android.app.Activity;


import android.hardware.Camera;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.views.CameraView;
import com.southwest.southwestapp.widgets.CameraBoxWidget;

import org.opencv.android.OpenCVLoader;


/**
 * Created by luis.bejarano
 */
@SuppressWarnings("deprecation")
public class OCRActivity extends Activity implements OnClickListener {

    static {
        if (!OpenCVLoader.initDebug()) {
        } else {
        }
    }

    private Camera mCamera = null;
    private CameraView mCameraView = null;
    public static CameraBoxWidget focusBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);

        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            Log.d("ERROR", "Failed to get camera: " + e.getMessage());
        }

        if (mCamera != null) {
            mCameraView = new CameraView(this, mCamera);
            FrameLayout camera_view = (FrameLayout) findViewById(R.id.camera_view);
            camera_view.addView(mCameraView);
        }

        focusBox = (CameraBoxWidget) findViewById(R.id.focus_box);

        ImageButton imgClose = (ImageButton) findViewById(R.id.imgClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCameraView.takePicture();
            }
        });

        ImageButton imgFocus = (ImageButton) findViewById(R.id.imageButton);
        imgFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCameraView.autoFocus();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {


        }
    }


}
