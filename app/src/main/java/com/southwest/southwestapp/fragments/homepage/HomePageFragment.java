package com.southwest.southwestapp.fragments.homepage;


import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;
import com.southwest.southwestapp.utils.AnimationGenericUtils;


/**
 * Created by luisalfonsobejaranosanchez on 9/1/15.
 */
public class HomePageFragment extends BaseFragment implements View.OnClickListener {

    private static final float ZOOM_FACTOR = 1.03f;
    private boolean isValidOutro = true;

    private FrameLayout mRoot;
    private View mDiscountContainer;
    private RelativeLayout mPreferredContainer;
    private FrameLayout mFragmentPagerContainer;
    private ViewPager mViewPromoPager;


    public HomePageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_homepage, container, false);

        mRoot = (FrameLayout) view.findViewById(R.id.homepageDiscountRoot);

        mDiscountContainer = view.findViewById(R.id.homepageDiscountContainer);
        mPreferredContainer = (RelativeLayout) view.findViewById(R.id.homepagePreferredContainer);
        mFragmentPagerContainer = (FrameLayout) view.findViewById(R.id.fragmentPagerContainer);
        mViewPromoPager = (ViewPager) view.findViewById(R.id.homepageManagePromoPager);


        mDiscountContainer.setOnClickListener(this);


        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        introAnimation();

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentPagerContainer, new HomeViewPager()).commit();
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
        AnimationGenericUtils.slideRightToLeft(mDiscountContainer,0 , getContext());

    }


    private void outroAnimation() {

        AnimationGenericUtils.fadeOutScreenBottom(mFragmentPagerContainer, getContext());

        AnimationGenericUtils.slideOutBottomWithFadeOut(mPreferredContainer, getContext());

        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                AnimationGenericUtils.fadeInAnimation(mViewPromoPager, getContext());
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        };

        AnimationGenericUtils.zoomOut(mRoot, animatorListener, ZOOM_FACTOR);

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

