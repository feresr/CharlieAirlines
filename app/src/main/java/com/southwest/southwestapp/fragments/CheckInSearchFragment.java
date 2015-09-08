package com.southwest.southwestapp.fragments;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by emiliano.gudino on 02/09/2015.
 */
public class CheckInSearchFragment extends BaseFragment implements View.OnClickListener {

    private Button mBtRetrieve;
    private EditText mEtConfirmationNumber;
    private EditText mEtFirstName;
    private EditText mEtLastName;
    private TextView mTvEligibleTrips;
    private Toolbar mToolbar;

    public CheckInSearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_checkin_search, container, false);

        mToolbar = (android.support.v7.widget.Toolbar)view.findViewById(R.id.toolbar);
        setUpToolBar();

        mBtRetrieve = (Button)view.findViewById(R.id.btn_retrieve_reservation);
        mEtConfirmationNumber = (EditText)view.findViewById(R.id.edt_confirmation);
        mEtFirstName = (EditText)view.findViewById(R.id.edt_first_name);
        mEtLastName = (EditText)view.findViewById(R.id.edt_last_name);
        mTvEligibleTrips = (TextView)view.findViewById(R.id.tv_eligible_trips);

        mBtRetrieve.setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_retrieve_reservation:
                AppHelper.screenManager.showCheckInScreen(getActivity());
                break;

        }
    }

    private void setUpToolBar() {
        //TODO Add assets
        /*mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_icon_nav_andr_back_arrow));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });*/
        mToolbar.setTitle(getResources().getString(R.string.check_in_tool_bar_title));
        mToolbar.setTitleTextColor(getResources().getColor(R.color.neutral_white));
        mToolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }
}
