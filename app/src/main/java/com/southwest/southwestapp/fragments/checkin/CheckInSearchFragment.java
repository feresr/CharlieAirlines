package com.southwest.southwestapp.fragments.checkin;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;
import com.southwest.southwestapp.utils.AnimationGenericUtils;
import com.southwest.southwestapp.vo.PassengerVO;

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


/**
 * Created by emiliano.gudino on 02/09/2015.
 */
public class CheckInSearchFragment extends BaseFragment implements View.OnClickListener {

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
                String number = mEtConfirmationNumber.getText().toString();
                String name = mEtFirstName.getText().toString() + mEtLastName.getText().toString();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(number)) {

                    scannPassport.setVisibility(View.INVISIBLE);
                    PassengerVO[] param = {new PassengerVO(mEtFirstName.getText().toString() + " " + mEtLastName.getText().toString(), "", 0)};
                    AppHelper.userCheckInController.setConfirmationNumer(number);
                    AppHelper.userCheckInController.setPassangers(param);

                    AppHelper.screenManager.hideSoftKeyboard(getActivity());
                    AnimationGenericUtils.fadeInAnimation(mProgresSwLogo, null, AppHelper.getInstance().getBaseContext());
                    mProgresSwLogo.startAnimation(AnimationUtils.loadAnimation(AppHelper.getInstance().getBaseContext(), R.anim.pulse));
                    delay();

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


    private void delay() {
        long delay = 4000;

        showTimerTask = new TimerTask() {
            @Override
            public void run() {
                AppHelper.screenManager.showCheckInScreen(getActivity());
            }
        };

        // Start the timer
        runTimer.schedule(showTimerTask, delay);
    }

}
