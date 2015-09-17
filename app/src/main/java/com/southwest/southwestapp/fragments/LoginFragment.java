package com.southwest.southwestapp.fragments;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.utils.AnimationGenericUtils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by emiliano.gudino on 09/09/2015.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener, View.OnFocusChangeListener {

    private Button mBtLogIn;
    private EditText mEtUser;
    private EditText mEtPass;
    private LinearLayout mLnrContainer;
    private TextView mTvWelcome;
    private TextView mTvEnroll;
    private TextView mTvContinueAsGuest;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mBtLogIn = (Button)view.findViewById(R.id.btn_login);
        mBtLogIn.setOnClickListener(this);

        mEtUser = (EditText)view.findViewById(R.id.et_user);
        mEtUser.setOnFocusChangeListener(this);

        mEtPass = (EditText)view.findViewById(R.id.et_pass);
        mEtPass.setOnFocusChangeListener(this);

        mLnrContainer = (LinearLayout)view.findViewById(R.id.lnr_container);

        mTvWelcome = (TextView)view.findViewById(R.id.tv_welcome);

        mTvEnroll = (TextView)view.findViewById(R.id.tv_enroll_now);
        mTvEnroll.setOnClickListener(this);
        mTvContinueAsGuest = (TextView)view.findViewById(R.id.tv_continue_as_guest);
        mTvContinueAsGuest.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String userName = mEtUser.getText().toString();
                String userPass = mEtPass.getText().toString();
                
                if(!TextUtils.isEmpty(userName)){

                }
                //AppHelper.screenManager.showMainScreenFromLogIn(getActivity());
                break;
            case R.id.tv_continue_as_guest:
                AppHelper.screenManager.showMainScreenFromLogIn(getActivity());
                break;
            default:
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.et_user:
                if (hasFocus) {
                    if (mTvWelcome.isShown()) {
                        AnimationGenericUtils.fadeOutAnimationLogIn(mTvWelcome, null, getActivity());
                        AnimationGenericUtils.fadeInBottomLogInContainer(mLnrContainer, null, getActivity());
                        mTvWelcome.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.et_pass:
                if (hasFocus) {
                    if (mTvWelcome.isShown()) {
                        AnimationGenericUtils.fadeOutAnimationLogIn(mTvWelcome, null, getActivity());
                        AnimationGenericUtils.fadeInBottomLogInContainer(mLnrContainer, null, getActivity());
                        mTvWelcome.setVisibility(View.GONE);
                    }
                }
                break;
            default:
                break;
        }
    }
}
