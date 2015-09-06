package com.southwest.southwestapp.fragments.information;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.adapters.InformationAdapter;
import com.southwest.southwestapp.fragments.BaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
    private InformationAdapter viewPagerAdapter;
    private Button firstBtn;
    private Button secondBtn;
    private Button thirdButton;

    public InformationFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_pager_information, container, false);

        mViewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        viewPagerAdapter = new InformationAdapter(getContext(),getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setCurrentItem(0);

        firstBtn  =(Button)rootView.findViewById(R.id.footer_information_btn1);
        secondBtn =(Button)rootView. findViewById(R.id.footer_information_btn2);
        thirdButton = (Button)rootView. findViewById(R.id.footer_information_btn3);

        return rootView;

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTab();
        firstBtn.setPressed(true);
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
                viewPagerAdapter.animateAtIndex(1);
                break;
            case 2:
                thirdButton.setPressed(true);
                secondBtn.setPressed(false);
                firstBtn.setPressed(false);
                viewPagerAdapter.animateAtIndex(2);
                break;
        }

    }


}
