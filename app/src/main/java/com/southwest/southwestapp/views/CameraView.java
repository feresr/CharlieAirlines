package com.southwest.southwestapp.views;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.hardware.Camera;

import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.southwest.southwestapp.activities.OCRActivity;
import com.southwest.southwestapp.models.OpticalRecognitionAsyn;
import com.southwest.southwestapp.utils.OcrUtils;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


/**
 * Created by luis.bejarano on 9/23/15.
 */
@SuppressWarnings("deprecation")
public class CameraView extends SurfaceView implements SurfaceHolder.Callback,
        Camera.PictureCallback, android.hardware.Camera.ShutterCallback {

    private SurfaceHolder mHolder;
    private Camera mCamera;

    public CameraView(Context context, Camera camera) {
        super(context);

        mCamera = camera;
        mCamera.setDisplayOrientation(90);

        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);

    }

    private void doOCR(final Bitmap bitmap) {

        OpticalRecognitionAsyn opticalRecognition = new OpticalRecognitionAsyn(getContext());
        opticalRecognition.execute(bitmap);

        try {
            String result = opticalRecognition.get();

            new AlertDialog.Builder(getContext())
                    .setTitle("OCR")
                    .setMessage(result)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();


            Log.d("RESULT OCR: ", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            mCamera.setPreviewDisplay(surfaceHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d("ERROR", "Camera error on surfaceCreated " + e.getMessage());
        }
    }


    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        //before changing the application orientation, you need to stop the preview, rotate and then start it again
        if (mHolder.getSurface() == null)//check if the surface is ready to receive camera data
            return;
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            //this will happen when you are trying the camera if it's not running
        }

        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d("ERROR", "Camera error on surfaceChanged " + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mCamera.stopPreview();
        mCamera.release();
    }

    @Override
    public void onPictureTaken(byte[] bytes, android.hardware.Camera camera) {

        if (bytes == null) {
            return;
        }

        Bitmap bmp = OcrUtils.getFocusedBitmap(getContext(), camera, bytes, OCRActivity.focusBox.getBox());

        doOCR(bmp);

        mCamera.stopPreview();
        mCamera.startPreview();

    }


    @Override
    public void onShutter() {

    }

    public void takePicture() {
        mCamera.takePicture(null, null, this);
    }


    public void autoFocus() {
        mCamera.autoFocus(autoFocusCallback);
    }

    Camera.AutoFocusCallback autoFocusCallback = new android.hardware.Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, android.hardware.Camera camera) {

        }
    };

}