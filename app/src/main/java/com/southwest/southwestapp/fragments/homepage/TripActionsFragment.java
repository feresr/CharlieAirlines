package com.southwest.southwestapp.fragments.homepage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.adapters.BookingViewPagerAdapter;
import com.southwest.southwestapp.fragments.BaseFragment;
import com.southwest.southwestapp.utils.AnimationGenericUtils;
import com.southwest.southwestapp.widgets.BookingTabTitleWidget;


/**
 * Created by luisalfonsobejaranosanchez on 9/2/15.
 */
public class TripActionsFragment extends BaseFragment {

    private final String WIDGET_TAG = "TabTitleHomeViewPager";

    private ViewPager mViewPager;
    private BookingTabTitleWidget mBookingTrips;
    private BookingTabTitleWidget mManageTrips;
    private BookingViewPagerAdapter mAdapter;
    private RelativeLayout mPreferredContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_trips_actions, container, false);

        //Initialize views
        mViewPager = (ViewPager) rootView.findViewById(R.id.homepageManagePager);
        mPreferredContainer = (RelativeLayout) rootView.findViewById(R.id.homepagePreferredContainer);

        mBookingTrips = (BookingTabTitleWidget) rootView.findViewById(R.id.book_trips);
        mManageTrips = (BookingTabTitleWidget) rootView.findViewById(R.id.manage_trips);

        //Create/set viewpager adapter
        Context context = AppHelper.getInstance().getApplicationContext();
        mAdapter = new BookingViewPagerAdapter(getChildFragmentManager(),
                                               context.getResources().getStringArray(R.array.homepage_booking_tabs),context);
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(onPageChangeListener);

        mBookingTrips.setTag(WIDGET_TAG + "0");
        mManageTrips.setTag(WIDGET_TAG + "1");

        mManageTrips.setOnClickListener(tabClickListener);
        mBookingTrips.setOnClickListener(tabClickListener);
        mBookingTrips.setSelected();

        return rootView;
    }

    public void showPreferredInfo(){
        AnimationGenericUtils.fadeInAnimation(mPreferredContainer, getContext());
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
            switch (tabIndex) {
                case 0:
                    mBookingTrips.setSelected();
                    mManageTrips.setDeselected();
                    mAdapter.animateAtIndex(0);
                    break;

                case 1:
                    mManageTrips.setSelected();
                    mBookingTrips.setDeselected();
                    mAdapter.animateAtIndex(1);
                    break;
            }

        }

    };

    private View.OnClickListener tabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Object tag = view.getTag();
            if (tag != null) {
                if (tag.toString().startsWith(WIDGET_TAG)) {
                    int index = Integer.parseInt(tag.toString().substring(WIDGET_TAG.length()));
                    mViewPager.setCurrentItem(index, true);
                }
            }
        }
    };


    public interface OnTabSelectedListener {
        void onTabSelected(int position);
    }

    public interface Slideable {
        void slideTripPanelUp();
        void slideTripPanelDown();
    }

}
