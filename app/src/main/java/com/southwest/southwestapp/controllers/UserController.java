package com.southwest.southwestapp.controllers;

import com.southwest.southwestapp.models.UserProfile;

/**
 * Created by luisalfonsobejaranosanchez on 9/17/15.
 */
public class UserController {

    private UserProfile mUserProfile;

    public String getProfile() {

        if (mUserProfile != null) {
            mUserProfile.getUserName();
        }

        return "";

    }

    public boolean isLogged() {

        if (mUserProfile != null) {
            return mUserProfile.isLogged();
        }

        return false;
    }


}
