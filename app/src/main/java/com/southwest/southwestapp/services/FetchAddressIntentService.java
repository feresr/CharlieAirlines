package com.southwest.southwestapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import com.southwest.southwestapp.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by fernando.raviola on 21/09/2015.
 */
public class FetchAddressIntentService extends IntentService {

    protected ResultReceiver mReceiver;

    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;
    public static final String PACKAGE_NAME =
            "com.southwest.southwestapp";
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String RESULT_DATA_KEY_COUNTRY = PACKAGE_NAME +
            ".RESULT_DATA_KEY_COUNTRY";
    public static final String RESULT_DATA_KEY_CITY = PACKAGE_NAME +
            ".RESULT_DATA_KEY_CYTI";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME +
            ".LOCATION_DATA_EXTRA";
    public static final String TAG = FetchAddressIntentService.class.getSimpleName();


    public FetchAddressIntentService() {
        super("FetchAddressIntentService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public FetchAddressIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String errorMessage = "";

        // Get the location passed to this service through an extra.
        Location location = intent.getParcelableExtra(
                LOCATION_DATA_EXTRA);

        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);

        List<Address> addresses = null;

        mReceiver = intent.getParcelableExtra(RECEIVER);

        try {
            addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1);
        } catch (IOException ioException) {
            // Catch network or other I/O problems.
            errorMessage = getString(R.string.service_not_available);
            Log.e(TAG, errorMessage, ioException);
        } catch (IllegalArgumentException illegalArgumentException) {
            // Catch invalid latitude or longitude values.
            errorMessage = getString(R.string.invalid_lat_long_used);
            Log.e(TAG, errorMessage + ". " +
                    "Latitude = " + location.getLatitude() +
                    ", Longitude = " +
                    location.getLongitude(), illegalArgumentException);
        }

        // Handle case where no address was found.
        if (addresses == null || addresses.size()  == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = getString(R.string.no_address_found);
                Log.e(TAG, errorMessage);
            }
            deliverResultToReceiver(FAILURE_RESULT, errorMessage);
        } else {
            Address address = addresses.get(0);
            Log.i(TAG, getString(R.string.address_found));
            deliverResultToReceiver(SUCCESS_RESULT, address.getCountryName(), address.getLocality());
        }
    }

    private void deliverResultToReceiver(int resultCode, String countryName, String cityName) {
        Bundle bundle = new Bundle();
        bundle.putString(RESULT_DATA_KEY_COUNTRY, countryName);
        bundle.putString(RESULT_DATA_KEY_CITY, cityName);
        mReceiver.send(resultCode, bundle);
    }

    private void deliverResultToReceiver(int resultCode, String errorMessage) {
        Bundle bundle = new Bundle();
        bundle.putString(RESULT_DATA_KEY_COUNTRY, errorMessage);
        mReceiver.send(resultCode, bundle);
    }
}
