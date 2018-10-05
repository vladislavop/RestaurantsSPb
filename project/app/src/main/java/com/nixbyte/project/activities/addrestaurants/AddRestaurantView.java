package com.nixbyte.project.activities.addrestaurants;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.nixbyte.project.R;
import com.nixbyte.project.modules.actionbar.ActionBarConstructor;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.FinishActivity;
import com.nixbyte.project.modules.activity.Viewable;

public class AddRestaurantView implements Viewable<AddRestaurantActivity> {
    AppCompatActivity activity;

    public ActionBarConstructor actionBar;
    private ActionBarConstructor.Builder actionBarBulder;

    public AddRestaurantView(AppCompatActivity activity, View rootView) {
        this.activity = activity;
        this.actionBarBulder = new ActionBarConstructor.Builder(activity)
                .homeBehavior(new FinishActivity(activity))
                .icon(R.drawable.back_arrow)
                .menu(R.menu.main);
    }

    @Override
    public void onAttach(AddRestaurantActivity data) {
        this.actionBar = actionBarBulder.build();
        this.actionBar.setActionBar();
    }

}
