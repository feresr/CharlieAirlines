package com.southwest.southwestapp.fragments.checkin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.LabeledText;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;
import com.southwest.southwestapp.models.CheckIn;
import com.southwest.southwestapp.utils.AnimationGenericUtils;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by emiliano.gudino on 02/09/2015.
 */
public class CheckInFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = CheckInFragment.class.getSimpleName();

    private Toolbar mToolbar;
    private Button mBtConfirmation;
    private View nCheckInView;
    private ImageView mProgresSwLogo;

    private Timer nRunTimer = new Timer();
    private TimerTask nShowTimerTask;

    public CheckInFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        nCheckInView = inflater.inflate(R.layout.fragment_checkin, container, false);

        setUpToolBar();

        mBtConfirmation = (Button) nCheckInView.findViewById(R.id.confirmationButton);
        mBtConfirmation.setOnClickListener(this);

        LinearLayout linearBody = (LinearLayout) nCheckInView.findViewById(R.id.checkInBodyWrapper);
        linearBody.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));

        fillView(nCheckInView);

        return nCheckInView;
    }

    private void fillView(View nCheckInView) {

        CheckIn checkIn = AppHelper.userCheckInController.getCheckIn();

        if (checkIn != null) {

            LabeledText passengerName = (LabeledText) nCheckInView.findViewById(R.id.passenger_name);
            LabeledText confirmation = (LabeledText) nCheckInView.findViewById(R.id.confirmation_number);
            LabeledText city = (LabeledText) nCheckInView.findViewById(R.id.city);
            LabeledText date = (LabeledText) nCheckInView.findViewById(R.id.date);
            LabeledText depart = (LabeledText) nCheckInView.findViewById(R.id.depart);
            LabeledText arrive = (LabeledText) nCheckInView.findViewById(R.id.arrive);
            LabeledText flight_number = (LabeledText) nCheckInView.findViewById(R.id.flight_number);
            LabeledText flight_travel_time = (LabeledText) nCheckInView.findViewById(R.id.flight_travel_time);

            passengerName.setMainText(checkIn.getPassengers().get(0).getName());
            confirmation.setMainText(checkIn.getConfirmationNumber());

            city.setMainText(checkIn.getCity());
            city.setUpperText(checkIn.getMonth_date());

            date.setMainText(checkIn.getMonth_date());
            date.setBottomText(checkIn.getDateDay());

            arrive.setMainText(checkIn.getArrivesCity());
            arrive.setBottomText(checkIn.getArrivesTime());

            depart.setMainText(checkIn.getDepartsCity());
            depart.setBottomText(checkIn.getDepartsTime());

            flight_number.setMainText(checkIn.getFlightNumber());

            flight_travel_time.setMainText(checkIn.getTravelTime());
        }
    }

    private void setUpToolBar() {
        mToolbar = (Toolbar) nCheckInView.findViewById(R.id.toolbarGeneral);
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
            case R.id.confirmationButton:
                AppHelper.screenManager.hideSoftKeyboard(getActivity());
                AnimationGenericUtils.fadeInAnimation(mProgresSwLogo, null, AppHelper.getInstance().getBaseContext());
                mProgresSwLogo.startAnimation(AnimationUtils.loadAnimation(AppHelper.getInstance().getBaseContext(), R.anim.pulse));
                delay();
                break;
        }
    }

    private void delay() {
        long delay = 4000;

        nShowTimerTask = new TimerTask() {
            @Override
            public void run() {
                AppHelper.screenManager.showEmergencyContact(getActivity());
            }
        };

        // Start the timer
        nRunTimer.schedule(nShowTimerTask, delay);
    }

}
