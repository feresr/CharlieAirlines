package com.southwest.southwestapp.activities;

import android.app.Activity;


import android.hardware.Camera;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

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

    private Camera mCamera;
    private CameraView mCameraView;
    private ImageButton mRequesFocus;
    private ImageButton mImgClose;
    public static CameraBoxWidget focusBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);

        initCamera();

        focusBox = (CameraBoxWidget) findViewById(R.id.focus_box);
        mImgClose = (ImageButton) findViewById(R.id.orcImgClose);
        mRequesFocus = (ImageButton) findViewById(R.id.requesFocus);

        mImgClose.setOnClickListener(this);
        mRequesFocus.setOnClickListener(this);
        focusBox.setOnClickListener(this);

    }


    private void initCamera() {

        try {

            mCamera = Camera.open();

            if (mCamera != null) {
                mCameraView = new CameraView(this, mCamera);
                FrameLayout cameraView = (FrameLayout) findViewById(R.id.camera_view);

                cameraView.addView(mCameraView);
            }

        } catch (Exception e) {
            Log.d("ERROR", "Failed to get camera: " + e.getMessage());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
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
            case R.id.orcImgClose:
                finish();
                break;

            case R.id.requesFocus:
                mCameraView.autoFocus();
                break;


        }
    }

}
