package com.southwest.southwestapp.fragments.homepage;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.adapters.PromoAdapter;
import com.southwest.southwestapp.fragments.BaseFragment;
import com.southwest.southwestapp.utils.AnimationGenericUtils;
import com.southwest.southwestapp.views.BigPageViewPager;

public class BigPagerHomeFragment extends BaseFragment {

    private static final float ZOOM_FACTOR = 1.08f;
    private static final int FADE_OUT_FOOTER_TIME = 1500;

    private View rootView;
    private View mFooter;

    private BigPageViewPager mViewPager;
    private PromoAdapter viewPagerAdapter;

    private Button firstBtn;
    private Button secondBtn;
    private Button thirdButton;
    private SlidePanelListener slideListener;

    private CountDownTimer footerTimer;

    public BigPagerHomeFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_pager_information, container, false);

        mViewPager = (BigPageViewPager) rootView.findViewById(R.id.viewPager);

        viewPagerAdapter = new PromoAdapter(getActivity());
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setCurrentItem(0);

        firstBtn = (Button) rootView.findViewById(R.id.footer_information_btn1);
        secondBtn = (Button) rootView.findViewById(R.id.footer_information_btn2);
        thirdButton = (Button) rootView.findViewById(R.id.footer_information_btn3);

        mFooter = rootView.findViewById(R.id.footerInformation);

        mViewPager.setPagingEnabled(false);


        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enablePaging();
                slideListener.slideTripPanelDown();
            }
        });

        rootView.findViewById(R.id.close_panel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disablePaging();
                slideListener.slideTripPanelUp();
            }
        });

        return rootView;

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTab();
        firstBtn.setPressed(true);
    }

    private void setTab() {
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                viewPagerAdapter.animateAtIndex(position);
                footerTimer.cancel();
                footerTransition(FADE_OUT_FOOTER_TIME);

                switch (position) {
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
        });

    }

    private void footerTransition(int timeToOut) {

        mFooter.setVisibility(View.VISIBLE);
        footerTimer = new CountDownTimer(timeToOut, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                if (mFooter != null && getContext() != null) {
                    AnimationGenericUtils.fadeOutAnimation(mFooter, null, getContext());
                }
            }

        }.start();

    }

    public void onResume() {
        super.onResume();
    }


    public void enablePaging() {
        AnimationGenericUtils.zoom(rootView, null, ZOOM_FACTOR);
        AnimationGenericUtils.zoom(rootView, null, ZOOM_FACTOR);
        rootView.findViewById(R.id.close_panel_button).setVisibility(View.VISIBLE);
        AnimationGenericUtils.fadeInAnimation(rootView.findViewById(R.id.close_panel_button), getActivity());
        footerTransition(FADE_OUT_FOOTER_TIME);
        ((PromoPageFragment) viewPagerAdapter.getItem(mViewPager.getCurrentItem())).animateSecondContainer(AnimationGenericUtils.animations.FADE_IN);
        mViewPager.setPagingEnabled(true);
    }

    public void disablePaging() {
        AnimationGenericUtils.zoom(rootView, null, 1);
        rootView.findViewById(R.id.close_panel_button).setVisibility(View.GONE);
        ((PromoPageFragment) viewPagerAdapter.getItem(mViewPager.getCurrentItem())).animateSecondContainer(AnimationGenericUtils.animations.FADE_OUT);
        mViewPager.setPagingEnabled(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            slideListener = (SlidePanelListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.getClass().getSimpleName() + " must implement SlidePanelListener interface");
        }
    }

    public interface SlidePanelListener {
        void slideTripPanelUp();
        void slideTripPanelDown();
    }
}