package com.southwest.southwestapp.fragments.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;
import com.southwest.southwestapp.utils.AnimationGenericUtils;


/**
 * Created by luisalfonsobejaranosanchez on 9/1/15.
 */
public class HomePageFragment extends BaseFragment implements View.OnClickListener {

    private boolean isValidOutro = true;

    private View mDiscountContainer;
    private RelativeLayout mPreferredContainer;
    private FrameLayout mFragmentPagerContainer;

    public HomePageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mRootView = inflater.inflate(R.layout.fragment_homepage, container, false);

        mDiscountContainer = mRootView.findViewById(R.id.homepageDiscountContainer);
        mPreferredContainer = (RelativeLayout) mRootView.findViewById(R.id.homepagePreferredContainer);
        mFragmentPagerContainer = (FrameLayout) mRootView.findViewById(R.id.fragmentPagerContainer);
        mDiscountContainer.setOnClickListener(this);

        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        introAnimation();

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentPagerContainer, new TripActionsFragment()).commit();

    }

    private void introAnimation() {

        Animation.AnimationListener listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                AnimationGenericUtils.fadeInAnimation(mPreferredContainer, getContext());
            }
        };

        AnimationGenericUtils.fadeInBottom(mFragmentPagerContainer, listener, getContext());
        AnimationGenericUtils.slideRightToLeft(mDiscountContainer, 0, getContext());

    }


    private void outroAnimation() {

        AnimationGenericUtils.fadeOutScreenBottom(mFragmentPagerContainer, getContext());

        Animation.AnimationListener listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                AppHelper.screenManager.showInformationScreen(getActivity());
            }
        };

        AnimationGenericUtils.slideOutBottomWithFadeOut(mPreferredContainer, listener, getContext());

        isValidOutro = false;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.homepageDiscountContainer:
                if (isValidOutro) {
                    outroAnimation();
                }
                break;

        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }


}

