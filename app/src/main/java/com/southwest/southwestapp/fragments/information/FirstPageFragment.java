package com.southwest.southwestapp.fragments.information;

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
public class FirstPageFragment extends BaseFragment {

    private RelativeLayout informationContainer;

    public static FirstPageFragment newInstance(Context context) {
        FirstPageFragment f = new FirstPageFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_first_information_item, null);

        informationContainer = (RelativeLayout) rootView.findViewById(R.id.secondInformationTitlesContainer);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AnimationGenericUtils.fadeInAnimation(informationContainer,getContext());

    }

}
