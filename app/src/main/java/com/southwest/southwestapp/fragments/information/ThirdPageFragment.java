package com.southwest.southwestapp.fragments.information;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;
import com.southwest.southwestapp.utils.AnimationGenericUtils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


/**
 * Created by luisalfonsobejaranosanchez on 9/5/15.
 */
public class ThirdPageFragment extends BaseFragment {

    private RelativeLayout titlesContainer;

    private boolean isValidIntro = true;

    public static ThirdPageFragment newInstance(Context context) {
        return new ThirdPageFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_third_information_item, null);
        titlesContainer = (RelativeLayout)rootView.findViewById(R.id.thirdInformationTitlesContainer);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void introAnimation(){
        if(isValidIntro) {
            AnimationGenericUtils.slideRightToLeft(titlesContainer, 0, AppHelper.getInstance().getApplicationContext());

            isValidIntro = false;
        }
    }

}
