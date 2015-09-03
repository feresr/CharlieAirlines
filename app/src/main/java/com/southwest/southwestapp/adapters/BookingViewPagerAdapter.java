package com.southwest.southwestapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.southwest.southwestapp.R;


/**
 * Created by luisalfonsobejaranosanchez on 9/2/15.
 */
public class BookingViewPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private String[] mPageTitles;

    public BookingViewPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTitles[position];
    }

    public void setPageTitles(String[] titles) {
        mPageTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return new ArticleFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    public static class ArticleFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_checkin, container, false);
        }
    }

}
