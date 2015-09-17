package com.southwest.southwestapp;

import com.southwest.southwestapp.controllers.UserController;
import com.southwest.southwestapp.models.Contact;
import com.southwest.southwestapp.utils.DialogManager;
import com.southwest.southwestapp.utils.ScreenManager;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Fernando on 31/8/2015.
 */
public class AppHelper extends Application {

    private static AppHelper instance;
    public static final ScreenManager screenManager = new ScreenManager();
    public static final DialogManager dialogManager = new DialogManager();
    public static final UserController userController = new UserController();
    // TODO: get this data through web service
    public static List<Contact> contacts = new ArrayList<Contact>();

    public static AppHelper getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
