package com.southwest.southwestapp.fragments.checkin;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    public CheckInFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View checkInView = inflater.inflate(R.layout.fragment_checkin, container, false);

        mBtConfirmation = (Button) checkInView.findViewById(R.id.confirmationButton);
        linearBody = (LinearLayout) checkInView.findViewById(R.id.checkInBodyWrapper);

        linearBody.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));

        setUpToolBar();

        mBtConfirmation.setOnClickListener(this);

        return checkInView;
    }

    private void setUpToolBar() {
        ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if(mActionBar != null){
            setHasOptionsMenu(true);
            mActionBar.setTitle(getResources().getString(R.string.check_in_tool_bar_title));
            mActionBar.setDisplayHomeAsUpEnabled(false);
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
                AppHelper.screenManager.showCheckInConfirmationScreen(getActivity());
                break;

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_checkin,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.optionCancel:
                AppHelper.screenManager.showCheckInSearchScreen(getActivity());
                return true;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
