package com.southwest.southwestapp.fragments.information;

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(layoutId, null);
        mainInformationContainer = (ViewGroup) rootView.findViewById(R.id.mainInformationContainer);
        secondaryInformationContainer = (ViewGroup) rootView.findViewById(R.id.secondaryInformationContainer);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isFirstFragment) {
            introAnimation();
        }
    }

    public void introAnimation() {
        if (mainAnimation != null) {
            switch (mainAnimation) {
                case FADE_IN:
                    AnimationGenericUtils.fadeInAnimation(mainInformationContainer, context);
                    break;
                case SLIDE_IN_LEFT:
                    AnimationGenericUtils.slideRightToLeft(mainInformationContainer, 0, context);
            }
        }
        if (secondaryAnimation != null) {
            switch (secondaryAnimation) {
                case FADE_IN:
                    AnimationGenericUtils.fadeInAnimation(secondaryInformationContainer, context);
                    break;
                case SLIDE_IN_LEFT:
                    AnimationGenericUtils.slideRightToLeft(secondaryInformationContainer, 100, context);
            }
        }
    }
}
