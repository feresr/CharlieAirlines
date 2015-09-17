package com.southwest.southwestapp.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;
import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.homepage.BigPagerHomeFragment;
import com.southwest.southwestapp.fragments.homepage.TripActionsFragment;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements BigPagerHomeFragment.SlidePanelListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private TripActionsFragment tripFragment;
    private int mCurrentSelectedPosition;
    private Toolbar mToolbar;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolBar();
        setUpNavDrawer();

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
        } else {
            AppHelper.screenManager.showMainScreen(this);
            slideTripPanelUp();
        }

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    private void setUpNavDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                menuItem.setChecked(true);
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                switch (menuItem.getItemId()) {
                    case R.id.checkIn:
                        AppHelper.screenManager.showCheckInSearchScreen(MainActivity.this);
                        mCurrentSelectedPosition = 2;
                        return true;

                    case R.id.home:
                        AppHelper.screenManager.showMainScreen(MainActivity.this);
                        mCurrentSelectedPosition = 0;
                        slideTripPanelUp();
                        return true;

                    case R.id.logout:
                        AppHelper.screenManager.showLoginScreen(MainActivity.this);
                        return true;

                    default:
                        //Event not handled: return false.
                        return false;
                }
            }
        });
    }

    private void setUpToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        /*Call syncState() from your Activity's onPostCreate to synchronize the indicator with the
         state of the linked DrawerLayout after onRestoreInstanceState has occurred.*/
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //This method should always be called by your Activity's onConfigurationChanged method.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION, 0);
        Menu menu = mNavigationView.getMenu();
        menu.getItem(mCurrentSelectedPosition).setChecked(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void slideTripPanelUp() {
        tripFragment = TripActionsFragment.newInstance(true);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in_bottom, R.anim.slide_out_bottom_with_fade_out);
        ft.replace(R.id.panel_container, tripFragment);
        ft.commit();
    }

    @Override
    public void slideTripPanelDown() {
        if (tripFragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.fade_in_bottom, R.anim.slide_out_bottom_with_fade_out);
            ft.remove(tripFragment);
            ft.commit();
        }
    }

    //Google places API callbacks
    @Override
    public void onConnected(Bundle bundle) {
        // Get a PlacePhotoMetadataResult containing metadata for the first 10 photos.
        Places.GeoDataApi.getPlacePhotos(mGoogleApiClient, "ChIJrTLr-GyuEmsRBfy61i59si0").setResultCallback(new ResultCallback<PlacePhotoMetadataResult>() {
            @Override
            public void onResult(PlacePhotoMetadataResult result) {
                if (result != null && result.getStatus().isSuccess()) {
                    PlacePhotoMetadataBuffer photoMetadataBuffer = result.getPhotoMetadata();

                    Random r = new Random();

                    // Get the first photo in the list.
                    PlacePhotoMetadata photo = photoMetadataBuffer.get(r.nextInt(photoMetadataBuffer.getCount()));
                    // Get a full-size bitmap for the photo.
                    photo.getPhoto(mGoogleApiClient).setResultCallback(new ResultCallback<PlacePhotoResult>() {
                        @Override
                        public void onResult(PlacePhotoResult placePhotoResult) {

                            ((ImageView) mNavigationView.findViewById(R.id.header_image)).setImageBitmap(
                                    placePhotoResult.getBitmap());
                        }
                    });

                    photoMetadataBuffer.release();
                }
            }
        });
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
