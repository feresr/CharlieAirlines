package com.southwest.southwestapp.fragments.homepage;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.adapters.BookingViewPagerAdapter;
import com.southwest.southwestapp.fragments.BaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by luisalfonsobejaranosanchez on 9/2/15.
 */
public class HomeViewPager extends BaseFragment {

    private final String tabTitleTextView = "TabTitleHomeViewPager";
    private ViewPager mViewPager;
    private TextView[] mArrTabTitleViews;
    private LinearLayout mTabTitleHolder;
    private BookingViewPagerAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home_view_pager, container, false);
        mTabTitleHolder = (LinearLayout) rootView.findViewById(R.id.viwTabTitleHolder);
        mViewPager = (ViewPager) rootView.findViewById(R.id.homepageManagePager);

        mAdapter = new BookingViewPagerAdapter(getChildFragmentManager(),getContext());
        mAdapter.setPageTitles(getResources().getStringArray(R.array.homepage_booking_tabs));
        mViewPager.setAdapter(mAdapter);

        generateTabTitles();

        mViewPager.addOnPageChangeListener(onPageChangeListener);

        return rootView;
    }


    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            if (mAdapter != null) {
                android.support.v4.app.Fragment f = (android.support.v4.app.Fragment) mAdapter.instantiateItem(null, position);
                if (f != null && f instanceof OnTabSelectedListener) {
                    ((OnTabSelectedListener) f).onTabSelected(position);
                }
            }
            selectedTab(position);
        }

        private void selectedTab(int tabIndex) {
            for (TextView titleView : mArrTabTitleViews) {
                titleView.setTextColor(getResources().getColor(R.color.cardview_shadow_start_color));
                titleView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
            mArrTabTitleViews[tabIndex].setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.btn_star_big_on, 0, 0, 0);
            mArrTabTitleViews[tabIndex].setTextColor(getResources().getColor(R.color.cardview_shadow_start_color));
        }

    };

    private View.OnClickListener tabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Object tag = view.getTag();
            if (tag != null) {
                if (tag.toString().startsWith(tabTitleTextView)) {
                    int index = Integer.parseInt(tag.toString().substring(tabTitleTextView.length()));
                    mViewPager.setCurrentItem(index, true);
                }
            }
        }
    };


    private void generateTabTitles() {

        PagerAdapter adapter = mViewPager.getAdapter();
        mArrTabTitleViews = new TextView[adapter.getCount()];
        int margin = getResources().getDimensionPixelOffset(R.dimen.tabs_margin);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1F);
        for (int i = 0; i < mArrTabTitleViews.length; i++) {
            mArrTabTitleViews[i] = new TextView(getActivity());
            mArrTabTitleViews[i].setGravity(Gravity.CENTER);
            mArrTabTitleViews[i].setLayoutParams(params);
            mArrTabTitleViews[i].setPadding(0, margin, 0, margin);
            mArrTabTitleViews[i].setText(adapter.getPageTitle(i));
            mArrTabTitleViews[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
            mArrTabTitleViews[i].setTag(tabTitleTextView + i);
            mArrTabTitleViews[i].setTextColor(getResources().getColor(R.color.common_signin_btn_text_dark));
            mArrTabTitleViews[i].setOnClickListener(tabClickListener);
            mTabTitleHolder.addView(mArrTabTitleViews[i]);
        }

        mArrTabTitleViews[mViewPager.getCurrentItem()].setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.btn_star_big_on, 0, 0, 0);
        mArrTabTitleViews[mViewPager.getCurrentItem()].setTextColor(getResources().getColor(R.color.common_signin_btn_text_dark));

    }

    public interface OnTabSelectedListener {
        public void onTabSelected(int position);
    }

}
