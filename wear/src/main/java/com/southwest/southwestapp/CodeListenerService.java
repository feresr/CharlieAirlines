package com.southwest.southwestapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import java.io.InputStream;

/**
 * Created by Fernando on 22/9/2015.
 */
public class CodeListenerService extends WearableListenerService implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public static Bitmap mbitmap;
    private final String TAG = CodeListenerService.class.getSimpleName();
    private int mNotificationId = 1;
    public static final String ACTION_DISMISS = "de.peterfriese.notificationwithopenactivityonwearableaction.DISMISS";
    private GoogleApiClient googleApiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        super.onDataChanged(dataEvents);
        for (DataEvent event : dataEvents) {
            if (event.getType() == DataEvent.TYPE_CHANGED &&
                    event.getDataItem().getUri().getPath().equals("/image")) {
                DataMapItem dataMapItem = DataMapItem.fromDataItem(event.getDataItem());
                Asset profileAsset = dataMapItem.getDataMap().getAsset("qrcodeImage");


                // Do something with the bitmap
                mbitmap = loadBitmapFromAsset(profileAsset, dataMapItem.getUri());
                Intent resultIntent = new Intent(this, MainActivity.class);

                PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(
                                this,
                                0,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );

                resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.mipmap.ic_launcher_charlie)
                                .setAutoCancel(true)
                                .setContentTitle(getString(R.string.boardingpass_notification_title))
                                .setContentText(getString(R.string.boardingpass_notification_message))
                                .setContentIntent(resultPendingIntent);


                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
                notificationManagerCompat.notify(mNotificationId++, mBuilder.build());


            }
        }
    }


    public Bitmap loadBitmapFromAsset(Asset asset, Uri uri) {
        if (asset == null) {
            throw new IllegalArgumentException("Asset must be non-null");
        }


        ConnectionResult result =
                googleApiClient.blockingConnect();
        if (!result.isSuccess()) {
            return null;
        }
        // convert asset into a file descriptor and block until it's ready
        InputStream assetInputStream = Wearable.DataApi.getFdForAsset(
                googleApiClient, asset).await().getInputStream();


        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "Deleting Uri: " + uri.toString());
        }

        Wearable.DataApi.deleteDataItems(
                googleApiClient, uri);


        if (assetInputStream == null) {
            Log.w(TAG, "Requested an unknown Asset.");
            return null;
        }
        // decode the stream into a bitmap
        return BitmapFactory.decodeStream(assetInputStream);
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
