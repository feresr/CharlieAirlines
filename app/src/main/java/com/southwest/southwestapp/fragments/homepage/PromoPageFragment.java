package com.southwest.southwestapp.fragments.homepage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;
import com.southwest.southwestapp.utils.AnimationGenericUtils;

public class PromoPageFragment extends BaseFragment {

    private ViewGroup mainInformationContainer;
    private ViewGroup secondaryInformationContainer;

    private Context context;
    private AnimationGenericUtils.animations mainAnimation;
    private AnimationGenericUtils.animations secondaryAnimation;
    private boolean isFirstFragment = false;
    public int layoutId;

    public static PromoPageFragment newInstance(Context context, int layoutId, @Nullable AnimationGenericUtils.animations mainAnimation, @Nullable AnimationGenericUtils.animations secondaryAnimation) {
        PromoPageFragment fragment = new PromoPageFragment();
        fragment.layoutId = layoutId;
        fragment.context = context;
        fragment.mainAnimation = mainAnimation;
        fragment.secondaryAnimation = secondaryAnimation;
        return fragment;
    }

    public static PromoPageFragment newInstance(Context context, int layoutId, @Nullable AnimationGenericUtils.animations mainAnimation, @Nullable AnimationGenericUtils.animations secondaryAnimation, boolean firstPromo) {
        PromoPageFragment fragment = newInstance(context, layoutId, mainAnimation, secondaryAnimation);
        fragment.isFirstFragment = firstPromo;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            isFirstFragment = savedInstanceState.getBoolean("isFirstFragment");
            layoutId = savedInstanceState.getInt("layoutId");
        }

        ViewGroup rootView = (ViewGroup) inflater.inflate(layoutId, null);
        mainInformationContainer = (ViewGroup) rootView.findViewById(R.id.mainInformationContainer);
        mainInformationContainer.setClickable(false);
        secondaryInformationContainer = (ViewGroup) rootView.findViewById(R.id.secondaryInformationContainer);
        mainInformationContainer.setClickable(false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isFirstFragment) {
            introAnimation();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("isFirstFragment", isFirstFragment);
        outState.putInt("layoutId", layoutId);
        super.onSaveInstanceState(outState);
    }

    public void introAnimation() {
        if (mainAnimation != null) {
            animateFirstContainer(mainAnimation);
        }
        if (secondaryAnimation != null) {
            animateSecondContainer(secondaryAnimation);
        }
    }

    public void animateFirstContainer(AnimationGenericUtils.animations animation){
        switch (animation) {
            case FADE_IN:
                AnimationGenericUtils.fadeInAnimation(mainInformationContainer, null ,context);
                break;
            case SLIDE_IN_LEFT:
                AnimationGenericUtils.slideRightToLeft(mainInformationContainer, 0, context);
        }
    }

    public void animateSecondContainer(AnimationGenericUtils.animations animation){
        switch (animation) {
            case FADE_IN:
                AnimationGenericUtils.fadeInAnimation(secondaryInformationContainer,null ,context);
                break;
            case SLIDE_IN_LEFT:
                AnimationGenericUtils.slideRightToLeft(secondaryInformationContainer, 100, context);
                break;
            case FADE_OUT:
                AnimationGenericUtils.fadeOutAnimation(secondaryInformationContainer, null,context);
        }

        //for the first fragment, we don't show the secondary promo info the first time show it.
        //but we do on consecutive instances.
        if (isFirstFragment) {
            this.secondaryAnimation = animation;
        }
    }

}
