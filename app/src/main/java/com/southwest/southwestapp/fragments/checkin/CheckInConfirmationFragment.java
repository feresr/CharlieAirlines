package com.southwest.southwestapp.fragments.checkin;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by armando.dominguez on 03/09/2015.
 */
public class CheckInConfirmationFragment extends BaseFragment implements View.OnClickListener, Toolbar.OnMenuItemClickListener {

    private static final String TAG = CheckInConfirmationFragment.class.getSimpleName();

    private TextView mSuccessText;
    private Button mBtBoardingPass;
    private CardView mCardContent;
    private LinearLayout faqContainer;
    private Toolbar mToolbar;
    private View confirmationView;

    public CheckInConfirmationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        confirmationView = inflater.inflate(R.layout.fragment_confirmation, container, false);

        mSuccessText = (TextView)confirmationView.findViewById(R.id.successText);
        mCardContent = (CardView)confirmationView.findViewById(R.id.card_view);
        mBtBoardingPass = (Button)confirmationView.findViewById(R.id.btn_boarding_pass);
        faqContainer = (LinearLayout)confirmationView.findViewById(R.id.faqWrapper);

        setUpToolBar();

        mBtBoardingPass.setOnClickListener(this);

        mSuccessText.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right));
        mCardContent.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));
        faqContainer.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));

        return confirmationView;
    }

    private void setUpToolBar() {
        mToolbar = (Toolbar)confirmationView.findViewById(R.id.toolbarGeneral);
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
            // Set an OnMenuItemClickListener to handle menu item clicks
            mToolbar.setOnMenuItemClickListener(this);

            // Inflate a menu to be displayed in the toolbar
            mToolbar.inflateMenu(R.menu.menu_confirmation);
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
        // Handle the menu item
        switch (item.getItemId()) {
            case R.id.confirmationMenu:
                break;
            default:
                break;
        }
        return true;
    }
}
