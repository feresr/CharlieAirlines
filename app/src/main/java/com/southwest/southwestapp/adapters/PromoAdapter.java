package com.southwest.southwestapp.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.southwest.southwestapp.fragments.information.FirstPageFragment;
import com.southwest.southwestapp.fragments.information.SecondPageFragment;
import com.southwest.southwestapp.fragments.information.ThirdPageFragment;

public class PromoAdapter extends FragmentStatePagerAdapter {

    private static final int TOTAL_PAGE = 3;

    //TODO: refactor this, there should be just ONE promo fragment with different parameters
    //(consider builder pattern)
    private FirstPageFragment firstPage;
    private SecondPageFragment secondPage;
    private ThirdPageFragment thirdPage;

    public PromoAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        firstPage = FirstPageFragment.newInstance(context);
        secondPage = SecondPageFragment.newInstance(context);
        thirdPage = ThirdPageFragment.newInstance(context);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();
        switch (position) {
            case 0:
                //TODO: refactor this as well, initializing this on the constructor is not a good idea.
                //it could get garbage collected while the app is on the background. (null pointer)
                return firstPage;
            case 1:
                return secondPage;
            case 2:
                return thirdPage;
        }
        return fragment;
    }


    public void animateAtIndex(int index) {
        switch (index) {
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
