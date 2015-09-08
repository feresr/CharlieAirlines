package com.southwest.southwestapp.fragments.information;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;
import com.southwest.southwestapp.utils.AnimationGenericUtils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by luisalfonsobejaranosanchez on 9/5/15.
 */
public class SecondPageFragment extends BaseFragment {

    private TextView mInformation;

    private RelativeLayout titlesContainer;
    private LinearLayout informationContainer;

    private boolean isValidIntro = true;

    public static SecondPageFragment newInstance(Context context) {
        return new SecondPageFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_second_information_item, null);
        mInformation = (TextView) rootView.findViewById(R.id.secondInformation);
        mInformation.setText(Html.fromHtml(getContext().getResources().getString(R.string.secondpage_information)));

        titlesContainer = (RelativeLayout) rootView.findViewById(R.id.thirdInformationTitlesContainer);
        informationContainer = (LinearLayout) rootView.findViewById(R.id.secondInformationfoContainer);

        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void introAnimation(){
        if(isValidIntro) {
            AnimationGenericUtils.slideRightToLeft(titlesContainer, 0, AppHelper.getInstance().getApplicationContext());
            AnimationGenericUtils.slideRightToLeft(informationContainer, 300, AppHelper.getInstance().getApplicationContext());
            isValidIntro = false;
        }
    }

}
