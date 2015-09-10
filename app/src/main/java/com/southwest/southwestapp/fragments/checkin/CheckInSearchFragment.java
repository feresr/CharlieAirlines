package com.southwest.southwestapp.fragments.checkin;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    public CheckInSearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_checkin_search, container, false);

        setUpToolBar();

        mBtRetrieve = (Button) view.findViewById(R.id.btn_retrieve_reservation);
        mEtConfirmationNumber = (EditText) view.findViewById(R.id.edt_confirmation);
        mEtFirstName = (EditText) view.findViewById(R.id.edt_first_name);
        mEtLastName = (EditText) view.findViewById(R.id.edt_last_name);
        cardReservation = (CardView) view.findViewById(R.id.card_reservation);

        cardReservation.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));

        mBtRetrieve.setOnClickListener(this);

        return view;
    }

    private void setUpToolBar() {
        ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if(mActionBar != null){
            setHasOptionsMenu(true);
            mActionBar.setTitle(getResources().getString(R.string.check_in_tool_bar_title));
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                Log.d(TAG,"HOME CLICKED...");
                getActivity().finish();
                return true;
            default:
                return false;
        }
    }
}
