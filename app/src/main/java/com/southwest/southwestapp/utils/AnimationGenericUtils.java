package com.southwest.southwestapp.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import com.ebay.flookcore.R;


/**
 * Created by luis.bejarano on 2/2/15.
 */
public class AnimationGenericUtils {

    public AnimationGenericUtils(){}

    public void fadeInAnimation(final View view , Context context){

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

    public void fadeOutAnimation(final View view ,Context context){

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


    public void transitionFadeInFadeOut(final View view , int timeToOut,final Context context){

        fadeInAnimation(view, context);

        new CountDownTimer(timeToOut, 1000) {

            public void onTick(long millisUntilFinished) {}

            public void onFinish() { fadeOutAnimation(view, context ); }

        }.start();

    }

}
