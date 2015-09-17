package com.southwest.southwestapp.controllers;

import com.southwest.southwestapp.models.UserProfile;

/**
 * Created by luisalfonsobejaranosanchez on 9/17/15.
 */
public class UserController {

    private UserProfile mUserProfile;

    public UserController() {
        mUserProfile = new UserProfile();
    }

    public String getUserName() {

        if (mUserProfile != null) {
            mUserProfile.getUserName();
        }

        return "";

    }

    public UserProfile getUserProfile(){
        return this.mUserProfile;
    }

    public void setUserProfile(UserProfile mUserProfile) {
        this.mUserProfile = mUserProfile;
    }

    public boolean isLogged() {

        if (mUserProfile != null) {
            return mUserProfile.isLogged();
        }

        return false;
    }


}
