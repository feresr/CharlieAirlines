package com.southwest.southwestapp.utils;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;

import com.southwest.southwestapp.R;


public class AnimationGenericUtils {

    public static enum animations {
        FADE_IN,
        FADE_OUT,
        SLIDE_IN_LEFT
    }

    public static void fadeInAnimation(final View view, Context context) {

        Animation fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in);

        fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                if (!view.isShown())
                    view.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {

            }
        });

        view.startAnimation(fadeInAnimation);

    }

    public static void fadeOutAnimation(final View view, Animation.AnimationListener listener, Context context) {

        Animation fadeOutAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_out);
        if (listener != null) {
            fadeOutAnimation.setAnimationListener(listener);
        } else {

            fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation arg0) {
                }

                @Override
                public void onAnimationRepeat(Animation arg0) {
                }

                @Override
                public void onAnimationEnd(Animation arg0) {
                    if (view.isShown())
                        view.setVisibility(View.INVISIBLE);

                }
            });
        }

        view.startAnimation(fadeOutAnimation);

    }

    public static void slideRightToLeft(final View view, int delay, Context context) {

        Animation slideRightToLeftAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_right_to_left);
        slideRightToLeftAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                if (!view.isShown())
                    view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {

            }
        });

        slideRightToLeftAnimation.setInterpolator(new BounceInterpolator());
        slideRightToLeftAnimation.setStartOffset(delay);
        view.startAnimation(slideRightToLeftAnimation);

    }

    public static void fadeInBottom(final View view, @Nullable Animation.AnimationListener listener, Context context) {

        Animation fadeInBottomAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in_bottom);

        if (listener != null) {
            fadeInBottomAnimation.setAnimationListener(listener);
        }

        view.startAnimation(fadeInBottomAnimation);

    }

    public static void fadeOutScreenBottom(final View view, Animation.AnimationListener listener ,Context context) {

        Animation fadeOutBottomAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_out_screen_bottom);

        if(listener != null){
            fadeOutBottomAnimation.setAnimationListener(listener);
        }else{
            fadeOutBottomAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation arg0) {

                }

                @Override
                public void onAnimationRepeat(Animation arg0) {
                }

                @Override
                public void onAnimationEnd(Animation arg0) {
                    if (view.isShown())
                        view.setVisibility(View.INVISIBLE);
                }
            });
        }

        view.startAnimation(fadeOutBottomAnimation);

    }


    public static void slideOutBottomWithFadeOut(final View view, Animation.AnimationListener listener, Context context) {

        Animation slideOutBottomWithFadeOut = AnimationUtils.loadAnimation(context, R.anim.slide_out_bottom_with_fade_out);

        if (listener == null) {
            slideOutBottomWithFadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation arg0) {

                }

                @Override
                public void onAnimationRepeat(Animation arg0) {
                }

                @Override
                public void onAnimationEnd(Animation arg0) {
                    if (view.isShown())
                        view.setVisibility(View.INVISIBLE);
                }
            });
        } else {
            slideOutBottomWithFadeOut.setAnimationListener(listener);
        }

        view.startAnimation(slideOutBottomWithFadeOut);

    }

    public static void zoom(final View expandedImageView, Animator.AnimatorListener listener, float zoom) {

        ViewPropertyAnimator animator = expandedImageView.animate();

        if (listener != null) {
            animator.setListener(listener);
        }

        animator.scaleY(zoom);
        animator.scaleX(zoom);
        animator.start();

    }
}
