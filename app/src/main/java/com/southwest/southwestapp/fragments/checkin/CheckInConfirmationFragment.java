package com.southwest.southwestapp.fragments.checkin;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.adapters.CheckInRecyclerAdapter;
import com.southwest.southwestapp.fragments.BaseFragment;
import com.southwest.southwestapp.models.CheckInVO;
import com.southwest.southwestapp.models.PassengerVO;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by armando.dominguez on 03/09/2015.
 */
public class CheckInConfirmationFragment extends BaseFragment implements View.OnClickListener, Toolbar.OnMenuItemClickListener {

    private static final String TAG = CheckInConfirmationFragment.class.getSimpleName();

    private TextView mSuccessText;
    private LinearLayout faqContainer;
    private RecyclerView mRecycler;
    private Toolbar mToolbar;
    private View confirmationView;

    public CheckInConfirmationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        confirmationView = inflater.inflate(R.layout.fragment_confirmation, container, false);

        mSuccessText = (TextView) confirmationView.findViewById(R.id.successText);
        faqContainer = (LinearLayout) confirmationView.findViewById(R.id.faqWrapper);
        mRecycler = (RecyclerView) confirmationView.findViewById(R.id.recycler_flights);


        setUpToolBar();

        mSuccessText.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right));
        mRecycler.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));
        faqContainer.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(llm);


        if (AppHelper.userCheckInController.getCheckin() != null && AppHelper.userCheckInController.getCheckin().getPassengers().length != 0) {
            AppHelper.userCheckInController.getCheckin().getPassengers()[0].setGroup("A");
            AppHelper.userCheckInController.getCheckin().getPassengers()[0].setPosition(22);
            AppHelper.userCheckInController.getCheckin().setFlightNumber("4852");
            AppHelper.userCheckInController.getCheckin().setGate("F5");
            AppHelper.userCheckInController.getCheckin().setTravelTime("3h 23m");
            CheckInVO[] arrayCheck = {AppHelper.userCheckInController.getCheckin()};
            mRecycler.setAdapter(new CheckInRecyclerAdapter(arrayCheck, getActivity()));
        } else {
            PassengerVO[] arrayPass = {new PassengerVO("Homer Thompson", "A", 11),
                    new PassengerVO("Marge Thompson", "B", 19),
                    new PassengerVO("Lisa Thompson", "B", 31),};


            CheckInVO[] arrayCheck = {new CheckInVO("666", "1h 10m", "A1", "MCX456Q1", arrayPass),
                    new CheckInVO("999", "1h 35m", "B1", "MXC4576F", arrayPass)};

            mRecycler.setAdapter(new CheckInRecyclerAdapter(arrayCheck, getActivity()));
        }

        return confirmationView;
    }

    private void setUpToolBar() {
        mToolbar = (Toolbar) confirmationView.findViewById(R.id.toolbarGeneral);
        if (mToolbar != null) {
            mToolbar.setTitle(getResources().getString(R.string.check_in_tool_bar_title));
            if (mToolbar.getSubtitle() != null) {
                mToolbar.setSubtitle(null);
            }
            mToolbar.inflateMenu(R.menu.menu_confirmation);

            mToolbar.setOnMenuItemClickListener(this);


        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_boarding_pass:
                AppHelper.screenManager.showBoardingPassScreen(getActivity());
                break;
            default:
                break;
        }
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.confirmationMenu:
                getActivity().finish();
                return true;
            default:
                return false;
        }
    }
}
