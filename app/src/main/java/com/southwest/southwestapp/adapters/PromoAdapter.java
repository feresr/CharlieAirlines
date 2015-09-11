package com.southwest.southwestapp.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.homepage.PromoPageFragment;
import com.southwest.southwestapp.utils.AnimationGenericUtils;

import java.util.ArrayList;

public class PromoAdapter extends FragmentStatePagerAdapter {


    private ArrayList<PromoPageFragment> promos;

    public PromoAdapter(FragmentActivity activity) {
        super(activity.getSupportFragmentManager());
        promos = new ArrayList<>();
        promos.add(PromoPageFragment.newInstance(activity, R.layout.fragment_first_information_item, AnimationGenericUtils.animations.SLIDE_IN_LEFT, null, true));
        promos.add(PromoPageFragment.newInstance(activity, R.layout.fragment_second_information_item, AnimationGenericUtils.animations.SLIDE_IN_LEFT, AnimationGenericUtils.animations.SLIDE_IN_LEFT));
        promos.add(PromoPageFragment.newInstance(activity, R.layout.fragment_third_information_item, AnimationGenericUtils.animations.FADE_IN, AnimationGenericUtils.animations.FADE_IN));
    }

    @Override
    public Fragment getItem(int position) {
        return promos.get(position);
    }

    public void animateAtIndex(int index) {
        promos.get(index).introAnimation();
    }


    @Override
    public int getCount() {
        return promos.size();
    }

}
