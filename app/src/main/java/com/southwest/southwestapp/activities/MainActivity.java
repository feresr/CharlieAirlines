package com.southwest.southwestapp.activities;

import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.homepage.BigPagerHomeFragment;
import com.southwest.southwestapp.fragments.homepage.TripActionsFragment;
import com.southwest.southwestapp.network.models.FlickrPhoto;
import com.southwest.southwestapp.network.models.FlickrSearchPhotoResponse;
import com.southwest.southwestapp.services.FetchAddressIntentService;
import com.squareup.picasso.Picasso;

import java.util.Random;

import retrofit.Callback;
import retrofit.Response;


public class MainActivity extends AppCompatActivity implements BigPagerHomeFragment.SlidePanelListener,
        Callback<FlickrSearchPhotoResponse>, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
    private static final int FLICK_PHOTOS = 6; //Amount of photos_id retrieved from flickr
    private static final int ENABLE_LOCATION_KEY = 1;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private TripActionsFragment tripFragment;
    private ImageView navviewHeader;

    private GoogleApiClient mGoogleApiClient;

    private int mCurrentSelectedPosition;
    private Toolbar mToolbar;

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
        buildGoogleApiClient();
    }

    private void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    //GOOGLE API CLIENT CONNECTED
    @Override
    public void onConnected(Bundle bundle) {
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

        if (mLastLocation != null) {
            startGeoCodingService(mLastLocation);
        } else {
            LocationRequest mLocationRequest = new LocationRequest();
            mLocationRequest.setNumUpdates(1);
            mLocationRequest.setInterval(0);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            //Start listening for location updates
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    startGeoCodingService(location);
                    LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
                }
            });

            showEnableGPSDialog(mLocationRequest);
        }
    }

    protected void showEnableGPSDialog(LocationRequest mLocationRequest) {

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                //final LocationSettingsStates = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(
                                    MainActivity.this,
                                    ENABLE_LOCATION_KEY); //--> OnActivityResult
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        break;
                }
            }
        });
    }

    protected void startGeoCodingService(Location location) {
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        AddressResultReceiver mResultReceiver = new AddressResultReceiver(new Handler());
        intent.putExtra(FetchAddressIntentService.RECEIVER, mResultReceiver);
        intent.putExtra(FetchAddressIntentService.LOCATION_DATA_EXTRA, location);
        startService(intent);
    }

    private void setUpNavDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer);
        navviewHeader = ((ImageView) mDrawerLayout.findViewById(R.id.drawer_background));
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        TextView text = (TextView) findViewById(R.id.passenger_name);
        text.setText(AppHelper.userController.getUserProfile().getUserName());
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                menuItem.setChecked(true);
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                switch (menuItem.getItemId()) {
                    case R.id.checkIn:
                        AppHelper.screenManager.showCheckInSearchScreen(MainActivity.this,null);
                        mCurrentSelectedPosition = 2;
                        return true;

                    case R.id.home:
                        AppHelper.screenManager.showMainScreen(MainActivity.this);
                        mCurrentSelectedPosition = 0;
                        slideTripPanelUp();
                        return true;

                    case R.id.logout:
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
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
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

    @Override
    public void onResponse(Response<FlickrSearchPhotoResponse> response) {
        Log.e(this.getClass().getSimpleName(), response.toString());
        if (response.body().photos.photo.size() > 0) {
            Random r = new Random();
            FlickrPhoto photo = response.body().photos.photo.get(r.nextInt(response.body().photos.photo.size()));

            Picasso.with(this).load(photo.getUrl("z")).placeholder(navviewHeader.getDrawable()).into(navviewHeader);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e(this.getClass().getSimpleName(), t.toString());
    }


    private void searchPhotos(String key) {
        AppHelper.flickrApi.searchPhotosByKeyword(key, FLICK_PHOTOS).enqueue(this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e(this.getClass().getSimpleName(), "onConnectionSuspended: " + i);

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(this.getClass().getSimpleName(), connectionResult.toString());
    }


    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            // Show a toast message if an address was found.
            if (resultCode == FetchAddressIntentService.SUCCESS_RESULT) {
                String country = resultData.getString(FetchAddressIntentService.RESULT_DATA_KEY_COUNTRY);
                String city = resultData.getString(FetchAddressIntentService.RESULT_DATA_KEY_CITY);
                searchPhotos(country + " " + city + " skyline");
            }

        }
    }
}
