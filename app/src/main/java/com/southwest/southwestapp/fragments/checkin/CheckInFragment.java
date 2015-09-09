package com.southwest.southwestapp.fragments.checkin;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by emiliano.gudino on 02/09/2015.
 */
public class CheckInFragment extends BaseFragment implements View.OnClickListener, Toolbar.OnMenuItemClickListener {

    private static final String TAG = CheckInFragment.class.getSimpleName();

    private Button mBtConfirmation;
    private Toolbar mToolbar;

    public CheckInFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View checkInView = inflater.inflate(R.layout.fragment_checkin, container, false);

        mBtConfirmation = (Button)checkInView.findViewById(R.id.confirmationButton);
        mToolbar = (Toolbar)checkInView.findViewById(R.id.toolbar);
        setUpToolBar();

        mBtConfirmation.setOnClickListener(this);

        mToolbar.setOnMenuItemClickListener(this);

        mToolbar.inflateMenu(R.menu.menu_checkin);

        return checkInView;
    }

    private void setUpToolBar() {
        mToolbar.setTitle(getResources().getString(R.string.check_in_tool_bar_title));
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
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optionCancel:
                AppHelper.screenManager.showCheckInSearchScreen(getActivity());
                return true;
            default:
                return false;
        }
    }
}
