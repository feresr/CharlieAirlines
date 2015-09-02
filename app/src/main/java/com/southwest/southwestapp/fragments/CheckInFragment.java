package com.southwest.southwestapp.fragments;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by emiliano.gudino on 02/09/2015.
 */
public class CheckInFragment extends BaseFragment implements View.OnClickListener{

    private Button checkIn;

    public CheckInFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewCheckin = inflater.inflate(R.layout.fragment_checkin, container, false);

        checkIn = (Button) viewCheckin.findViewById(R.id.buttonCheckIn);

        checkIn.setOnClickListener(this);

        return viewCheckin;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonCheckIn:
                AppHelper.screenManager.showCheckInConfirmationScreen(getActivity());
                break;

        }
    }
}
