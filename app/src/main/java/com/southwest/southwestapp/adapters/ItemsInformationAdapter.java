package com.southwest.southwestapp.adapters;

import com.southwest.southwestapp.fragments.information.FirstPageFragment;
import com.southwest.southwestapp.fragments.information.SecondPageFragment;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by luisalfonsobejaranosanchez on 9/5/15.
 */
public class ItemsInformationAdapter extends FragmentPagerAdapter {

    private Context context;
    private static final int TOTAL_PAGE = 3;

    public ItemsInformationAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();
        switch(position){
            case 0:
                fragment= FirstPageFragment.newInstance(context);
                break;
            case 1:
                fragment= SecondPageFragment.newInstance(context);
                break;

            case 2:
                fragment= SecondPageFragment.newInstance(context);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return TOTAL_PAGE;
    }

}
