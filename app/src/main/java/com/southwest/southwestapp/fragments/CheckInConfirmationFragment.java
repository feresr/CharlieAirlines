package com.southwest.southwestapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;

import org.w3c.dom.Text;

/**
 * Created by armando.dominguez on 03/09/2015.
 */
public class CheckInConfirmationFragment extends BaseFragment implements View.OnClickListener {

    private TextView successText;
    private Button mBtBoardingPass;
    private CardView cardContent;
    
    public CheckInConfirmationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View confirmationView = inflater.inflate(R.layout.fragment_confirmation, container, false);

        successText = (TextView) confirmationView.findViewById(R.id.successText);
        cardContent = (CardView) confirmationView.findViewById(R.id.card_view);
        mBtBoardingPass = (Button) confirmationView.findViewById(R.id.btn_boarding_pass);
        mBtBoardingPass.setOnClickListener(this);

        successText.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.slide_in_right));
        cardContent.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.slide_in_bottom));

        return confirmationView;
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
}
