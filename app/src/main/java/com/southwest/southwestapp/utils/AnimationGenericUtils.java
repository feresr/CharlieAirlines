package com.southwest.southwestapp.utils;


import com.southwest.southwestapp.R;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


/**
 * Created by Created by luisalfonsobejaranosanchez on 9/1/15.
 */
public class AnimationGenericUtils {


    public static void fadeInAnimation(final View view , Context context){

        Animation fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in);

        fadeInAnimation.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0) {
                if(!view.isShown())
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

    public static void fadeOutAnimation(final View view ,Context context){

        Animation fadeOutAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_out);
        fadeOutAnimation.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0) {
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
                if(view.isShown())
                        view.setVisibility(View.GONE);

            }
        });

        view.startAnimation(fadeOutAnimation);

    }

    public static void slideRightToLeft(final View view, Context context){


        Animation fadeOutAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_right_to_left);
        fadeOutAnimation.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0) {
                if(!view.isShown())
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

    public static void transitionFadeInFadeOut(final View view , int timeToOut,final Context context){

        fadeInAnimation(view, context);

        new CountDownTimer(timeToOut, 1000) {

            public void onTick(long millisUntilFinished) {}

            public void onFinish() { fadeOutAnimation(view, context ); }

        }.start();

    }

}
