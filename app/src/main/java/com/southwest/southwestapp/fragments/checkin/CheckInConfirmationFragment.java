package com.southwest.southwestapp.fragments.checkin;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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
import android.widget.TextView;


/**
 * Created by armando.dominguez on 03/09/2015.
 */
public class CheckInConfirmationFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = CheckInConfirmationFragment.class.getSimpleName();

    private TextView mSuccessText;
    private Button mBtBoardingPass;
    private CardView mCardContent;
    private LinearLayout faqContainer;

    public CheckInConfirmationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View confirmationView = inflater.inflate(R.layout.fragment_confirmation, container, false);

        mSuccessText = (TextView) confirmationView.findViewById(R.id.successText);
        mCardContent = (CardView) confirmationView.findViewById(R.id.card_view);
        mBtBoardingPass = (Button) confirmationView.findViewById(R.id.btn_boarding_pass);
        faqContainer = (LinearLayout) confirmationView.findViewById(R.id.faqWrapper);

        setUpToolBar();

        mBtBoardingPass.setOnClickListener(this);

        mSuccessText.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right));
        mCardContent.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));
        faqContainer.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));


        return confirmationView;
    }

    private void setUpToolBar() {
        ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if(mActionBar != null){
            setHasOptionsMenu(true);
            mActionBar.setTitle(getResources().getString(R.string.check_in_tool_bar_title));
            if(mActionBar.getSubtitle() != null){
                mActionBar.setSubtitle(null);
            }
            mActionBar.setDisplayHomeAsUpEnabled(false);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_confirmation,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.confirmationMenu:
                getActivity().finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
