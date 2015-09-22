package com.southwest.southwestapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.models.UserProfile;
import com.southwest.southwestapp.network.RestClient;
import com.southwest.southwestapp.network.models.User;
import com.southwest.southwestapp.utils.AnimationGenericUtils;

import java.util.Timer;
import java.util.TimerTask;

import retrofit.Call;
import retrofit.Callback;


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
    private ImageView mProgresSwLogo;

    private Timer runSplash = new Timer();
    private TimerTask showSplash;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mBtLogIn = (Button) view.findViewById(R.id.btn_login);
        mBtLogIn.setOnClickListener(this);

        mEtUser = (EditText) view.findViewById(R.id.et_user);
        mEtUser.setOnFocusChangeListener(this);

        mEtPass = (EditText) view.findViewById(R.id.et_pass);
        mEtPass.setOnFocusChangeListener(this);

        mLnrContainer = (LinearLayout) view.findViewById(R.id.lnr_container);

        mTvWelcome = (TextView) view.findViewById(R.id.tv_welcome);

        mProgresSwLogo = (ImageView) view.findViewById(R.id.progresSwLogo);

        mTvEnroll = (TextView) view.findViewById(R.id.tv_enroll_now);
        mTvEnroll.setOnClickListener(this);
        mTvContinueAsGuest = (TextView) view.findViewById(R.id.tv_continue_as_guest);
        mTvContinueAsGuest.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String userName = mEtUser.getText().toString();
                String userPass = mEtPass.getText().toString();

                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userPass)) {

                    AppHelper.screenManager.hideSoftKeyboard(getActivity());
                    AnimationGenericUtils.fadeInAnimation(mProgresSwLogo, null, AppHelper.getInstance().getBaseContext());
                    mProgresSwLogo.startAnimation(AnimationUtils.loadAnimation(AppHelper.getInstance().getBaseContext(), R.anim.pulse));

                    doLoginApiRequest(userName, userPass);
                }

                break;
            case R.id.tv_continue_as_guest:
                AppHelper.screenManager.showMainScreenFromLogIn(getActivity());
                break;
            default:
                break;
        }
    }

    private void delay() {
        long delay = 4000;

        showSplash = new TimerTask() {
            @Override
            public void run() {
                AppHelper.screenManager.showMainScreenFromLogIn(getActivity());
            }
        };

        // Start the timer
        runSplash.schedule(showSplash, delay);
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

    private void doLoginApiRequest(String username, String password) {

        Call<User> call = RestClient.getClient().doLogin(username, password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(retrofit.Response<User> response) {

                if (!response.isSuccess()) {
                    showLoginError();
                    return;
                }

                User user = response.body();
                AppHelper.userController.setUserProfile(new UserProfile(user.getUsername()));
                AppHelper.screenManager.showMainScreenFromLogIn(getActivity());

            }

            @Override
            public void onFailure(Throwable t) {
                showLoginError();
            }
        });
    }

    private void showLoginError() {
        mProgresSwLogo.clearAnimation();
        AnimationGenericUtils.fadeOutAnimation(mProgresSwLogo, null, AppHelper.getInstance().getBaseContext());
        Toast.makeText(getContext(), getResources().getString(R.string.log_in_invalid_username_or_password), Toast.LENGTH_SHORT).show();
    }
}
