package com.southwest.southwestapp.fragments.information;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.adapters.ItemsInformationAdapter;
import com.southwest.southwestapp.fragments.BaseFragment;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by luisalfonsobejaranosanchez on 9/5/15.
 */
public class InformationFragment extends BaseFragment {

    private ViewPager mViewPager;
    private ItemsInformationAdapter viewPagerAdapter;
    private Button firstBtn;
    private Button secondBtn;
    private Button thirdButton;

    public InformationFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_pager_information, container, false);

        mViewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        viewPagerAdapter = new ItemsInformationAdapter(getContext(),getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setCurrentItem(0);

        firstBtn  =(Button)rootView.findViewById(R.id.footer_information_btn1);
        secondBtn =(Button)rootView. findViewById(R.id.footer_information_btn2);
        thirdButton = (Button)rootView. findViewById(R.id.footer_information_btn3);

        firstBtn.setPressed(true);

        setTab();

        return rootView;

    }

    private void setTab(){
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int position) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int position) {
                btnAction(position);
            }

        });

    }

    private void btnAction(int action){

        switch(action){
            case 0:
                firstBtn.setPressed(true);
                thirdButton.setPressed(false);
                secondBtn.setPressed(false);
                break;
            case 1:
                secondBtn.setPressed(true);
                firstBtn.setPressed(false);
                thirdButton.setPressed(false);
                break;
            case 2:
                thirdButton.setPressed(true);
                secondBtn.setPressed(false);
                firstBtn.setPressed(false);
                break;
        }

    }


}
