package com.southwest.southwestapp.utils;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.southwest.southwestapp.R;


/**
 * Created by Created by luisalfonsobejaranosanchez on 9/1/15.
 */
public class AnimationGenericUtils {


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

    public static void fadeOutAnimation(final View view, Context context) {

        Animation fadeOutAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_out);
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
                    view.setVisibility(View.GONE);

            }
        });

        view.startAnimation(fadeOutAnimation);

    }

    public static void slideRightToLeft(final View view, Context context) {

        Animation fadeOutAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_right_to_left);
        fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
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

        view.startAnimation(fadeOutAnimation);

    }

    public static void fadeInBottom(final View view, @Nullable Animation.AnimationListener listener, Context context) {

        Animation fadeInBottomAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in_bottom);

        if (listener != null) {
            fadeInBottomAnimation.setAnimationListener(listener);
        }

        view.startAnimation(fadeInBottomAnimation);

    }

    public static void fadeOutScreenBottom(final View view, Context context) {


        Animation fadeOutBottomAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_out_screen_bottom);

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
                    view.setVisibility(View.GONE);
            }
        });

        view.startAnimation(fadeOutBottomAnimation);

    }


    public static void slideOutBottomWithFadeOut(final View view, Context context) {

        Animation slideOutBottomWithFadeOut = AnimationUtils.loadAnimation(context, R.anim.slide_out_bottom_with_fade_out);

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
                    view.setVisibility(View.GONE);
            }
        });

        view.startAnimation(slideOutBottomWithFadeOut);

    }

    public static void zoomIn(final View expandedImageView, Animator.AnimatorListener listener, float zoom) {

        ViewPropertyAnimator animator = expandedImageView.animate();
        animator.setListener(listener);

        animator.scaleY(zoom);
        animator.scaleX(zoom);
        animator.start();

    }

    public static void blinkColorAnimation(final TextView view, int timeToOut,final int fromColor, int toColor){

    }

    public static void transitionFadeInFadeOut(final View view, int timeToOut, final Context context) {

        fadeInAnimation(view, context);

        new CountDownTimer(timeToOut, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                fadeOutAnimation(view, context);
            }

        }.start();

    }

}
