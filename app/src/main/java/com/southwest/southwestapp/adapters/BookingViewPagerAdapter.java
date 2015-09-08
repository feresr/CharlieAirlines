package com.southwest.southwestapp.adapters;

import android.content.Context;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.utils.AnimationGenericUtils;



/**
 * Created by luisalfonsobejaranosanchez on 9/2/15.
 */
public class BookingViewPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private String[] mPageTitles;

    private BookTripsItemsFragment bookTrips;
    private ManageTripsItemsFragment manageTrips;

    public BookingViewPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.context = context;

        int bookIcons[] = new int[3];
        bookIcons[0] = R.drawable.home_book_flight_icon;
        bookIcons[1] = R.drawable.home_car_icon;
        bookIcons[2] = R.drawable.home_checkin_icon;

        int manageIcons[] = new int[3];
        manageIcons[0] = R.drawable.home_checkin_icon;
        manageIcons[1] = R.drawable.home_flight_status_icon;
        manageIcons[2] = R.drawable.home_book_flight_icon;

        bookTrips   =  BookTripsItemsFragment.newInstance(context.getResources().getStringArray(R.array.booking_adapter_trips_titles), bookIcons  );
        manageTrips =  ManageTripsItemsFragment.newInstance(context.getResources().getStringArray(R.array.booking_adapter_manage_titles), manageIcons  );

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

        switch (position){
            case 0:
                return bookTrips;
            case 1:
                return manageTrips;
        }

        return null;

    }

    public void animateAtIndex(int index){

        switch (index){
            case 0:
                bookTrips.introAnimate();
                break;

            case 1:
                manageTrips.introAnimate();
                break;

        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    public static class BookTripsItemsFragment extends Fragment {

        protected RelativeLayout[] mItems;
        protected LinearLayout mLinearContainer;

        protected String[] titles;
        protected int[] drawables;

        protected static final String TITLE_TAG = "TITLES";
        protected static final String DRAWABLE_TAG = "DRAWABLE";

        public static  BookTripsItemsFragment newInstance(String[] titles, int[] drawables)
        {
            BookTripsItemsFragment fragment = new BookTripsItemsFragment();
            Bundle bundle = new Bundle(2);
            bundle.putStringArray(TITLE_TAG, titles);
            bundle.putIntArray(DRAWABLE_TAG, drawables);
            fragment.setArguments(bundle);
            return fragment;
        }


        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            titles = getArguments().getStringArray(TITLE_TAG);
            drawables = getArguments().getIntArray(DRAWABLE_TAG);
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

            View rootView     = inflater.inflate(R.layout.items_homepage_booking, container, false);
            mLinearContainer  = (LinearLayout) rootView.findViewById(R.id.items_homepage_booking_container);

            initializeChilds();

            return rootView;

        }


        protected void initializeChilds(){
            int childCount = mLinearContainer.getChildCount();
            mItems = new RelativeLayout[childCount];
            for(int b=0; b<childCount; b++){
                mItems[b] = (RelativeLayout) mLinearContainer.getChildAt(b);
                ( (TextView)mItems[b].findViewById(R.id.item_homepage_first_title)).setText(titles[b]);
                ( (ImageView) mItems[b].findViewById(R.id.item_homepage_first_icon)).setBackgroundResource(drawables[b]);
            }
        }

        public void introAnimate(){

        }

        protected int getAnimationDelay(int position) {
            return position * 200;
        }

    }

    public static class ManageTripsItemsFragment extends BookTripsItemsFragment {

        public static  ManageTripsItemsFragment newInstance(String[] titles, int[] drawables)
        {
            ManageTripsItemsFragment fragment = new ManageTripsItemsFragment();
            Bundle bundle = new Bundle(2);
            bundle.putStringArray(TITLE_TAG, titles);
            bundle.putIntArray(DRAWABLE_TAG, drawables);
            fragment.setArguments(bundle);
            return fragment;
        }


        protected void setupListeners(){

        }

        public void introAnimate(){

            int childCount = mLinearContainer.getChildCount();
            mItems = new RelativeLayout[childCount];

            for(int b=0; b<childCount; b++){
                mItems[b] = (RelativeLayout) mLinearContainer.getChildAt(b);
                AnimationGenericUtils.slideRightToLeft(mItems[b],getAnimationDelay(b) , getContext());
            }

        }

    }


}
