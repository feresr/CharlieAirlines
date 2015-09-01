package com.southwest.southwestapp.fragments;


import com.southwest.southwestapp.R;
import com.southwest.southwestapp.utils.CascadeAnimator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by luisalfonsobejaranosanchez on 9/1/15.
 */
public class HomePageFragment extends Fragment{

    private View mDiscountContainer;
    private RelativeLayout mPreferredContainer;
    private RelativeLayout mBookManageContainer;
    private ImageView mList;

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
        mBookManageContainer = (RelativeLayout) view.findViewById(R.id.homepageBookManageContainer);
        mList = (ImageView) view.findViewById(R.id.viewDummy);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<View> viewsToAnimate = new ArrayList<View>();
        viewsToAnimate.add(mList);
        viewsToAnimate.add(mBookManageContainer);
        viewsToAnimate.add(mPreferredContainer);
        viewsToAnimate.add(mDiscountContainer);

        Animation mListAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_bottom);

        CascadeAnimator animator = new CascadeAnimator(viewsToAnimate,mListAnimation);
        animator.startCascadeAnimation();


    }

    @Override
    public void onResume() {
        super.onResume();
    }

}

