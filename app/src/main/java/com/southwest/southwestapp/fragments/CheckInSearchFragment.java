package com.southwest.southwestapp.fragments;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

    public CheckInSearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_checkin_search, container, false);

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
}