package com.southwest.southwestapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;


/**
 * Created by emiliano.gudino on 02/09/2015.
 */
public class CheckInFragment extends BaseFragment implements View.OnClickListener{

    private Button buttonConfirmation;

    public CheckInFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View checkInView = inflater.inflate(R.layout.fragment_checkin, container, false);

        buttonConfirmation = (Button) checkInView.findViewById(R.id.confirmationButton);

        buttonConfirmation.setOnClickListener(this);

        return checkInView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirmationButton:
                AppHelper.screenManager.showCheckInConfirmationScreen(getActivity());
                break;

        }
    }
}
