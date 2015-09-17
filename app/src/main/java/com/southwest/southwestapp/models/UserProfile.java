package com.southwest.southwestapp.models;

/**
 * Created by luisalfonsobejaranosanchez on 9/17/15.
 */
public class UserProfile {

    private String userName;
    private boolean isLogged;

    public UserProfile(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

}
