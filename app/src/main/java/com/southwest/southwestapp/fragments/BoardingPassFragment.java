package com.southwest.southwestapp.fragments;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import java.io.ByteArrayOutputStream;

/**
 * Created by emiliano.gudino on 07/09/2015.
 */
public class BoardingPassFragment extends BaseFragment implements Toolbar.OnMenuItemClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Toolbar mToolbar;
    private View boardingPassView;
    private NestedScrollView mContainer;
    private GoogleApiClient mGoogleApiClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boardingPassView = inflater.inflate(R.layout.fragment_boarding_pass, container, false);

        mContainer = (NestedScrollView) boardingPassView.findViewById(R.id.container);
        mContainer.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));

        setUpToolBar();
        buildGoogleApiClient();
        return boardingPassView;
    }


    private void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    private void sendBoardingPassToWearables() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.qr_code_example);
        Asset asset = createAssetFromBitmap(bitmap);
        PutDataMapRequest dataMap = PutDataMapRequest.create("/image");
        dataMap.getDataMap().putAsset("qrcodeImage", asset);
        PutDataRequest request = dataMap.asPutDataRequest();
        Wearable.DataApi.deleteDataItems(
                mGoogleApiClient, dataMap.getUri());

        PendingResult<DataApi.DataItemResult> pendingResult = Wearable.DataApi
                .putDataItem(mGoogleApiClient, request);
    }

    private void setUpToolBar() {
        mToolbar = (Toolbar) boardingPassView.findViewById(R.id.toolbarGeneral);
        if (mToolbar != null) {
            mToolbar.setTitle(getResources().getString(R.string.boarding_pass_title));
            mToolbar.setSubtitle(getResources().getString(R.string.boarding_pass_subtitle));

            mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });
            // Set an OnMenuItemClickListener to handle menu item clicks
            mToolbar.setOnMenuItemClickListener(this);

            // Inflate a menu to be displayed in the toolbar
            mToolbar.inflateMenu(R.menu.menu_boarding_pass);
        }
    }


    private void showAlertSaveToPhotos() {

        AppHelper.dialogManager.showDialog(getActivity(),
                getResources().getString(R.string.boarding_pass_save_dialog_title),
                getResources().getString(R.string.boarding_pass_save_dialog_message),
                getResources().getString(R.string.boarding_pass_save_dialog_yes),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //AppHelper.screenManager.showMainScreen(getActivity());
                        getActivity().finish();
                    }
                },
                getResources().getString(R.string.boarding_pass_save_dialog_no),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Complete flow
                    }
                }, false);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optionCancel:
                AppHelper.screenManager.showCheckInSearchScreen(getActivity());
                return true;
            case R.id.optionSend:
                return true;
            case R.id.optionSave:
                showAlertSaveToPhotos();
                return true;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onConnected(Bundle bundle) {
        sendBoardingPassToWearables();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e(this.getClass().getSimpleName(), "onConnectionSuspended: " + i);

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(this.getClass().getSimpleName(), connectionResult.toString());
    }

    private static Asset createAssetFromBitmap(Bitmap bitmap) {
        final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream);
        return Asset.createFromBytes(byteStream.toByteArray());
    }
}
