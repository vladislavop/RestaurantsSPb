package com.nixbyte.project.modules.slidingmenu.controller;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.nixbyte.project.utils.Configurations;


/**
 * Created by nixbyte on 31.03.15.
 */
public class MenuListener implements DrawerLayout.DrawerListener {

    private Activity activity;

    public MenuListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        Configurations.hideKeyBoard(activity);
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
