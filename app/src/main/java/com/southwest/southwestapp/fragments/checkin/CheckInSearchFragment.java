package com.southwest.southwestapp.fragments.checkin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Timer;
import java.util.TimerTask;

import com.southwest.southwestapp.AppHelper;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;
import com.southwest.southwestapp.models.CheckIn;
import com.southwest.southwestapp.models.Passenger;
import com.southwest.southwestapp.network.models.ParseCheckIn;
import com.southwest.southwestapp.network.models.ParseCheckInList;
import com.southwest.southwestapp.utils.AnimationGenericUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.Response;


/**
 * Created by emiliano.gudino on 02/09/2015.
 */
public class CheckInSearchFragment extends BaseFragment implements View.OnClickListener, Callback<ParseCheckInList> {

    private static final String TAG = CheckInSearchFragment.class.getSimpleName();

    public static final String TAG_AUTO_COMPLETE = "autocomplete";

    private Button mBtRetrieve;
    private EditText mEtConfirmationNumber;
    private EditText mEtFirstName;
    private EditText mEtLastName;

    private EditText mEtCountry;
    private TextView mTvEligibleTrips;

    private Toolbar mToolbar;
    private CardView cardReservation;
    private View searchView;
    private ImageView mProgresSwLogo;
    private Button scannPassport;


    private Timer runTimer = new Timer();
    private TimerTask showTimerTask;

    private String[] autoCompleteData;


    public CheckInSearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle params = getArguments();

        if (params != null) {
            autoCompleteData = params.getStringArray(TAG_AUTO_COMPLETE);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        searchView = inflater.inflate(R.layout.fragment_checkin_search, container, false);

        setUpToolBar();

        mBtRetrieve = (Button) searchView.findViewById(R.id.btn_retrieve_reservation);
        scannPassport = (Button) searchView.findViewById(R.id.btn_scan_passport);
        mEtConfirmationNumber = (EditText) searchView.findViewById(R.id.edt_confirmation);
        mEtFirstName = (EditText) searchView.findViewById(R.id.edt_first_name);
        mEtLastName = (EditText) searchView.findViewById(R.id.edt_last_name);
        mEtCountry = (EditText) searchView.findViewById(R.id.edt_country);
        cardReservation = (CardView) searchView.findViewById(R.id.card_reservation);

        cardReservation.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));

        mBtRetrieve.setOnClickListener(this);
        scannPassport.setOnClickListener(this);

        if (autoCompleteData != null) {
            mEtCountry.setText(autoCompleteData[0]);
            mEtLastName.setText(autoCompleteData[1]);
            mEtFirstName.setText(autoCompleteData[2]);
        }

        return searchView;
    }

    private void setUpToolBar() {
        mToolbar = (Toolbar) searchView.findViewById(R.id.toolbarGeneral);
        mProgresSwLogo = (ImageView) mToolbar.findViewById(R.id.progresSwLogo);

        if (mToolbar != null) {
            mToolbar.setTitle(getResources().getString(R.string.check_in_tool_bar_title));
            if (mToolbar.getSubtitle() != null) {
                mToolbar.setSubtitle(null);
            }
            mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_retrieve_reservation:

                String number = mEtConfirmationNumber.getText().toString().trim();
                String firstName = mEtFirstName.getText().toString().trim();
                String lastName = mEtLastName.getText().toString().trim();


                if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(number)) {

                    scannPassport.setVisibility(View.INVISIBLE);
                    AppHelper.screenManager.hideSoftKeyboard(getActivity());
                    AnimationGenericUtils.fadeInAnimation(mProgresSwLogo, null, AppHelper.getInstance().getBaseContext());
                    mProgresSwLogo.startAnimation(AnimationUtils.loadAnimation(AppHelper.getInstance().getBaseContext(), R.anim.pulse));

                    AppHelper.parseApi.doRetrieveReservation(getApiRequestQuery(number, firstName, lastName)).enqueue(this);

                } else {
                    Toast.makeText(getContext(), "No data", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.btn_scan_passport:
                AppHelper.screenManager.showScanPassport(getActivity());
                break;
            default:
                break;

        }
    }

    private String getApiRequestQuery(String number, String firstName, String lastName) {
        try {
            JSONObject json = new JSONObject();
            json.put("confirmationNumber", number);
            json.put("firstName", firstName);
            json.put("lastName", lastName);
            return URLEncoder.encode(json.toString(), "UTF-8").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void onResponse(Response<ParseCheckInList> response) {

        if (!response.isSuccess()
                || response == null
                || response.body() == null
                || response.body().getResults().isEmpty()) {

            showError();
            return;
        }

        ParseCheckIn parseCheckIn = response.body().getResults().get(0);

        fillData(parseCheckIn);

        AppHelper.screenManager.showCheckInScreen(getActivity());
    }

    private void fillData(ParseCheckIn parseCheckIn) {

        Passenger passenger = new Passenger();
        passenger.setFirstName(parseCheckIn.getFirstName());
        passenger.setLastName(parseCheckIn.getLastName());
        passenger.setGroup("A");
        passenger.setPosition(0);

        CheckIn checkIn = AppHelper.userCheckInController.getCheckIn();
        checkIn.setConfirmationNumber(parseCheckIn.getConfirmationNumber());
        checkIn.setFlightNumber(parseCheckIn.getFlightNumber());
        checkIn.setGate(parseCheckIn.getGate());
        checkIn.setTravelTime(parseCheckIn.getTravelTime());
        checkIn.setArrivesCity(parseCheckIn.getArrivesCity());
        checkIn.setArrivesTime(parseCheckIn.getArrivesTime());
        checkIn.setCity(parseCheckIn.getCity());
        checkIn.setDateDay(parseCheckIn.getDateDay());
        checkIn.setDepartsCity(parseCheckIn.getDepartsCity());
        checkIn.setDepartsTime(parseCheckIn.getDepartsTime());
        checkIn.setMonthDate(parseCheckIn.getMonthDate());

        ArrayList arrayList = new ArrayList<Passenger>();
        arrayList.add(passenger);
        checkIn.setPassengers(arrayList);
    }

    @Override
    public void onFailure(Throwable t) {
        showError();
    }

    private void showError() {
        mProgresSwLogo.clearAnimation();
        scannPassport.setVisibility(View.VISIBLE);
        AnimationGenericUtils.fadeOutAnimation(mProgresSwLogo, null, AppHelper.getInstance().getBaseContext());
        Toast.makeText(getContext(), "Reservation not found", Toast.LENGTH_SHORT).show();
    }

}
