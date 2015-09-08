package com.southwest.southwestapp.fragments.checkin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;

/**
 * Created by armando.dominguez on 03/09/2015.
 */
public class CheckInConfirmationFragment extends BaseFragment implements View.OnClickListener,Toolbar.OnMenuItemClickListener {

    private static final String TAG = CheckInConfirmationFragment.class.getSimpleName();

    private TextView successText;
    private Button mBtBoardingPass;
    private CardView cardContent;
    private Toolbar confToolBar;
    
    public CheckInConfirmationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View confirmationView = inflater.inflate(R.layout.fragment_confirmation, container, false);

        successText = (TextView) confirmationView.findViewById(R.id.successText);
        cardContent = (CardView) confirmationView.findViewById(R.id.card_view);
        mBtBoardingPass = (Button) confirmationView.findViewById(R.id.btn_boarding_pass);
        confToolBar = (Toolbar) confirmationView.findViewById(R.id.toolbarConfirmation);

        setUpToolBar();

        mBtBoardingPass.setOnClickListener(this);

        successText.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right));
        cardContent.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.slide_in_bottom));

        confToolBar.setOnMenuItemClickListener(this);

        confToolBar.inflateMenu(R.menu.menu_confirmation);
        return confirmationView;
    }

    private void setUpToolBar() {
        confToolBar.setTitle(getResources().getString(R.string.check_in_tool_bar_title));
    }

      @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_boarding_pass:
                AppHelper.screenManager.showBoardingPassScreen(getActivity());
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.confirmationMenu:
                getActivity().finish();
                return true;
            default:
                return false;
        }
    }
}
