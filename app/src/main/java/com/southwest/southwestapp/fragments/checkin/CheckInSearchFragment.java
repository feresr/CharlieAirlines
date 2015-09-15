package com.southwest.southwestapp.fragments.checkin;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by emiliano.gudino on 02/09/2015.
 */
public class CheckInSearchFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = CheckInSearchFragment.class.getSimpleName();

    private Button mBtRetrieve;
    private EditText mEtConfirmationNumber;
    private EditText mEtFirstName;
    private EditText mEtLastName;
    private TextView mTvEligibleTrips;
    private Toolbar mToolbar;
    private CardView cardReservation;
    private View searchView;

    public CheckInSearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        searchView = inflater.inflate(R.layout.fragment_checkin_search, container, false);

        setUpToolBar();

        mBtRetrieve = (Button)searchView.findViewById(R.id.btn_retrieve_reservation);
        mEtConfirmationNumber = (EditText)searchView.findViewById(R.id.edt_confirmation);
        mEtFirstName = (EditText)searchView.findViewById(R.id.edt_first_name);
        mEtLastName = (EditText)searchView.findViewById(R.id.edt_last_name);
        cardReservation = (CardView)searchView.findViewById(R.id.card_reservation);

        cardReservation.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));

        mBtRetrieve.setOnClickListener(this);

        return searchView;
    }

    private void setUpToolBar() {
        mToolbar = (Toolbar)searchView.findViewById(R.id.toolbarGeneral);
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
                AppHelper.screenManager.showCheckInScreen(getActivity());
                break;
            default:
                break;

        }
    }
}
