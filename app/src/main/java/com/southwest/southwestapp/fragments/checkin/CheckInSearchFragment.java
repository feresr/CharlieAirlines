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
import android.widget.Toast;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;
import com.southwest.southwestapp.models.CheckIn;
import com.southwest.southwestapp.models.Passenger;
import com.southwest.southwestapp.network.models.ParseCheckIn;
import com.southwest.southwestapp.network.models.ParseCheckInList;
import com.southwest.southwestapp.utils.AnimationGenericUtils;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.Response;


/**
 * Created by emiliano.gudino on 02/09/2015.
 */
public class CheckInSearchFragment extends BaseFragment implements View.OnClickListener, Callback<ParseCheckInList> {

    private static final String TAG = CheckInSearchFragment.class.getSimpleName();

    private Button mBtRetrieve;
    private EditText mEtConfirmationNumber;
    private EditText mEtFirstName;
    private EditText mEtLastName;
    private Toolbar mToolbar;
    private CardView cardReservation;
    private View searchView;
    private ImageView mProgresSwLogo;

    public CheckInSearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        searchView = inflater.inflate(R.layout.fragment_checkin_search, container, false);

        setUpToolBar();

        mBtRetrieve = (Button) searchView.findViewById(R.id.btn_retrieve_reservation);
        mEtConfirmationNumber = (EditText) searchView.findViewById(R.id.edt_confirmation);
        mEtFirstName = (EditText) searchView.findViewById(R.id.edt_first_name);
        mEtLastName = (EditText) searchView.findViewById(R.id.edt_last_name);
        cardReservation = (CardView) searchView.findViewById(R.id.card_reservation);

        cardReservation.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));

        mBtRetrieve.setOnClickListener(this);

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_retrieve_reservation:

                String number = mEtConfirmationNumber.getText().toString();
                String name = mEtFirstName.getText().toString() + mEtLastName.getText().toString();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(number)) {

                    AppHelper.screenManager.hideSoftKeyboard(getActivity());
                    AnimationGenericUtils.fadeInAnimation(mProgresSwLogo, null, AppHelper.getInstance().getBaseContext());
                    mProgresSwLogo.startAnimation(AnimationUtils.loadAnimation(AppHelper.getInstance().getBaseContext(), R.anim.pulse));

                    AppHelper.parseApi.doRetrieveReservation().enqueue(this);

                } else {
                    Toast.makeText(getContext(), "No data", Toast.LENGTH_LONG).show();
                }

                break;
            default:
                break;

        }
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
        passenger.setFirstName(parseCheckIn.getFirst_name());
        passenger.setLastName(parseCheckIn.getLast_name());
        passenger.setGroup("A");
        passenger.setPosition(0);

        CheckIn checkIn = AppHelper.userCheckInController.getCheckIn();
        checkIn.setConfirmationNumber(parseCheckIn.getNumber_confirmation());
        checkIn.setFlightNumber(parseCheckIn.getFlight_number_1());
        checkIn.setGate(parseCheckIn.getGate());
        checkIn.setTravelTime(parseCheckIn.getTravel_time());
        checkIn.setArrivesCity(parseCheckIn.getArrives_city());
        checkIn.setArrivesTime(parseCheckIn.getArrives_time());
        checkIn.setCity(parseCheckIn.getCity());
        checkIn.setDateDay(parseCheckIn.getDate_day());
        checkIn.setDepartsCity(parseCheckIn.getDeparts_city());
        checkIn.setDepartsTime(parseCheckIn.getDeparts_time());
        checkIn.setMonth_date(parseCheckIn.getMonth_date());

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
        AnimationGenericUtils.fadeOutAnimation(mProgresSwLogo, null, AppHelper.getInstance().getBaseContext());
        Toast.makeText(getContext(), "Reservation not found", Toast.LENGTH_SHORT).show();
    }

}
