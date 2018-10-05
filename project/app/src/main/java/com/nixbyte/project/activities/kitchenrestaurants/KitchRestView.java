package com.nixbyte.project.activities.kitchenrestaurants;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nixbyte.project.R;
import com.nixbyte.project.modules.actionbar.ActionBarConstructor;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.FinishActivity;
import com.nixbyte.project.modules.activity.Viewable;

public class KitchRestView implements Viewable<KitchRestActivity>{

    KitchRestActivity activity;

    private Context context;

    private ActionBarConstructor.Builder actionBarBulder;

    public ActionBarConstructor actionBar;


    public KitchRestView(AppCompatActivity activity,View rootView) {
        this.context = rootView.getContext();
        this.actionBarBulder = new ActionBarConstructor.Builder(activity)
                .homeBehavior(new FinishActivity(activity))
                .icon(R.drawable.back_arrow)
                .menu(R.menu.main);
    }


    @Override
    public void onAttach(KitchRestActivity data) {
        activity = data;
        this.actionBar = actionBarBulder.build();
        this.actionBar.setActionBar();
    }
}
