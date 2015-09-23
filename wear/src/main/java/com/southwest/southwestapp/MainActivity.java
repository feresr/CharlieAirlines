package com.southwest.southwestapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private ImageView qrcodeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, CodeListenerService.class));
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
                                             @Override
                                             public void onLayoutInflated(WatchViewStub stub) {

                                                 qrcodeImageView = (ImageView) stub.findViewById(R.id.qrcode);
                                                 if (CodeListenerService.mbitmap != null) {
                                                     qrcodeImageView.setImageBitmap(CodeListenerService.mbitmap);
                                                 }
                                             }
                                         }
        );
    }

}
