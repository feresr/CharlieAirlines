package com.southwest.southwestapp.fragments.checkin;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.LabeledText;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;
import com.southwest.southwestapp.vo.CheckInVO;
import com.southwest.southwestapp.vo.PassengerVO;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;


/**
 * Created by emiliano.gudino on 02/09/2015.
 */
public class CheckInFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = CheckInFragment.class.getSimpleName();

    private Button mBtConfirmation;
    private LinearLayout linearBody;
    private Toolbar mToolbar;
    private LabeledText passengerName;
    private LabeledText confirmation;
    private View checkInView;

    public CheckInFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        checkInView = inflater.inflate(R.layout.fragment_checkin, container, false);

        mBtConfirmation = (Button) checkInView.findViewById(R.id.confirmationButton);
        linearBody = (LinearLayout) checkInView.findViewById(R.id.checkInBodyWrapper);
        passengerName = (LabeledText) checkInView.findViewById(R.id.passenger);
        confirmation = (LabeledText) checkInView.findViewById(R.id.confirmation_one);

        if (AppHelper.userCheckInController.getCheckin() != null) {
            passengerName.setMainText(AppHelper.userCheckInController.getCheckin().getPassengers()[0].getName());
            confirmation.setMainText(AppHelper.userCheckInController.getCheckin().getConfirmationNumber());
        }

        linearBody.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));

        setUpToolBar();

        mBtConfirmation.setOnClickListener(this);

        return checkInView;
    }

    private void setUpToolBar() {
        mToolbar = (Toolbar) checkInView.findViewById(R.id.toolbarGeneral);
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
            case R.id.confirmationButton:
                AppHelper.screenManager.showEmergencyContact(getActivity());
                break;

        }
    }
}
