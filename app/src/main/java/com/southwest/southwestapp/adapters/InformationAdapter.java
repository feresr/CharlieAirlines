package com.southwest.southwestapp.adapters;

import com.southwest.southwestapp.fragments.information.FirstPageFragment;
import com.southwest.southwestapp.fragments.information.SecondPageFragment;
import com.southwest.southwestapp.fragments.information.ThirdPageFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by luisalfonsobejaranosanchez on 9/5/15.
 */
public class InformationAdapter extends FragmentPagerAdapter {

    private Context context;
    private static final int TOTAL_PAGE = 3;

    private FirstPageFragment firstPage;
    private SecondPageFragment secondPage;
    private ThirdPageFragment thirdPage;

    public InformationAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context=context;
        firstPage  = FirstPageFragment.newInstance(context);
        secondPage = SecondPageFragment.newInstance(context);
        thirdPage  = ThirdPageFragment.newInstance(context);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();
        switch(position){
            case 0:
                return firstPage;
            case 1:
               return secondPage;
            case 2:
                return thirdPage;
        }
        return fragment;
    }


    public void animateAtIndex(int index){
        switch (index){
            case 0:
                firstPage.introAnimation();
                break;

            case 1:
                secondPage.introAnimation();
                break;

            case 2:
                thirdPage.introAnimation();
                break;
        }
    }

    @Override
    public int getCount() {
        return TOTAL_PAGE;
    }

}
