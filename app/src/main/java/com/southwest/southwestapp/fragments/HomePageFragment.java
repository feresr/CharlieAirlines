package com.southwest.southwestapp.fragments;


import com.southwest.southwestapp.R;
import com.southwest.southwestapp.utils.AnimationGenericUtils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;


/**
 * Created by luisalfonsobejaranosanchez on 9/1/15.
 */
public class HomePageFragment extends Fragment{

    private View mDiscountContainer;
    private RelativeLayout mPreferredContainer;
    private ViewPager mViewPager;

    public HomePageFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_homepage, container, false);

        mDiscountContainer = view.findViewById(R.id.homepageDiscountContainer);
        mPreferredContainer = (RelativeLayout) view.findViewById(R.id.homepagePreferredContainer);
        mViewPager = (ViewPager) view.findViewById(R.id.homepageManagePager);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        animate();
    }

    private void animate(){

        Animation mDiscountAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_right_to_left);

        Animation mListAnimation     = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_bottom);
        mListAnimation.setAnimationListener(new Animation.AnimationListener(){
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
        });

        mViewPager.setAnimation(mListAnimation);
        mDiscountContainer.setAnimation(mDiscountAnimation);

        mViewPager.animate();
        mDiscountContainer.animate();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

}

