package com.southwest.southwestapp.utils;


import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import java.util.List;

/**
 * Created by luisalfonsobejaranosanchez on 9/1/15.
 */
public class CascadeAnimator {

    private List<View> mViewsToAnimate;
    private Animation  mAnimation;

    public CascadeAnimator(List<View> viewsToAnimate, Animation animation){
        this.mViewsToAnimate = viewsToAnimate;
        this.mAnimation = animation;
    }

    public void startCascadeAnimation(){

        if(mAnimation != null &&  mViewsToAnimate != null && mViewsToAnimate.size() > 0){

            for(int a=0; a<mViewsToAnimate.size(); a++){
                View view = mViewsToAnimate.get(a);
                view.startAnimation(mAnimation);
            }

        }

    }


}
