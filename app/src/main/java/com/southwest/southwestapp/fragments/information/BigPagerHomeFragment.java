package com.southwest.southwestapp.fragments.information;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.adapters.PromoAdapter;
import com.southwest.southwestapp.fragments.BaseFragment;
import com.southwest.southwestapp.fragments.homepage.TripActionsFragment;
import com.southwest.southwestapp.utils.AnimationGenericUtils;


/**
 * Created by luisalfonsobejaranosanchez on 9/5/15.
 */
public class BigPagerHomeFragment extends BaseFragment implements View.OnClickListener {

    private static final float ZOOM_FACTOR = 1.03f;

    private View rootView;
    private View mFooter;

    private ViewPager mViewPager;
    private PromoAdapter viewPagerAdapter;
    private FrameLayout bookinMenuContainer;

    private Button firstBtn;
    private Button secondBtn;
    private Button thirdButton;

    private ImageButton mMenuAction;

    public BigPagerHomeFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_pager_information, container, false);

        mViewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        viewPagerAdapter = new PromoAdapter(getContext(), getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setCurrentItem(0);

        firstBtn = (Button) rootView.findViewById(R.id.footer_information_btn1);
        secondBtn = (Button) rootView.findViewById(R.id.footer_information_btn2);
        thirdButton = (Button) rootView.findViewById(R.id.footer_information_btn3);

        bookinMenuContainer = (FrameLayout) rootView.findViewById(R.id.bookinMenuContainer);

        mFooter = rootView.findViewById(R.id.footerInformation);

        mMenuAction = (ImageButton) rootView.findViewById(R.id.menuActionInformation);
        mMenuAction.setOnClickListener(this);

        return rootView;

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTab();
        introAnimation();
        firstBtn.setPressed(true);

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.bookinMenuContainer, new TripActionsFragment()).commit();

    }

    private void setTab() {
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

    private void introAnimation() {
        AnimationGenericUtils.zoomOut(rootView, null, ZOOM_FACTOR);
    }

    private void btnAction(int action) {

        switch (action) {
            case 0:
                firstBtn.setPressed(true);
                thirdButton.setPressed(false);
                secondBtn.setPressed(false);
                hideMenuAnimation();
                break;
            case 1:
                secondBtn.setPressed(true);
                firstBtn.setPressed(false);
                thirdButton.setPressed(false);
                viewPagerAdapter.animateAtIndex(1);
                hideMenuAnimation();
                break;
            case 2:
                thirdButton.setPressed(true);
                secondBtn.setPressed(false);
                firstBtn.setPressed(false);
                viewPagerAdapter.animateAtIndex(2);
                hideMenuAnimation();
                break;
        }

    }

    public void showMenuAnimation() {

        AnimationGenericUtils.fadeOutAnimation(mFooter, null, getContext());

        Animation.AnimationListener listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                bookinMenuContainer.setVisibility(View.VISIBLE);
                mMenuAction.setVisibility(View.INVISIBLE);
                AnimationGenericUtils.fadeInBottom(bookinMenuContainer, null, getContext());
            }
        };

        AnimationGenericUtils.fadeOutAnimation(mMenuAction, listener, getContext());

    }

    public void hideMenuAnimation() {

        if (bookinMenuContainer.getVisibility() == View.VISIBLE) {
            Animation.AnimationListener listener = new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation arg0) {

                }

                @Override
                public void onAnimationRepeat(Animation arg0) {
                }

                @Override
                public void onAnimationEnd(Animation arg0) {
                    AnimationGenericUtils.fadeInAnimation(mFooter, getContext());
                    AnimationGenericUtils.fadeInAnimation(mMenuAction, getContext());
                    bookinMenuContainer.setVisibility(View.GONE);
                }
            };
            AnimationGenericUtils.slideOutBottomWithFadeOut(bookinMenuContainer, listener, getContext());
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.menuActionInformation:
                showMenuAnimation();
                break;
        }

    }

}
