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
import android.widget.LinearLayout;

/**
 * Created by luisalfonsobejaranosanchez on 9/5/15.
 */
public class FirstPageFragment extends BaseFragment {

    private LinearLayout informationContainer;

    private boolean isValidIntro = true;

    public static FirstPageFragment newInstance(Context context) {
        FirstPageFragment fragment = new FirstPageFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_first_information_item, null);
        informationContainer = (LinearLayout) rootView.findViewById(R.id.informationContainer);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        introAnimation();
    }

    public void introAnimation(){
        if(isValidIntro){
            AnimationGenericUtils.fadeInAnimation(informationContainer, AppHelper.getInstance().getApplicationContext());
            isValidIntro = false;
        }
    }

}
