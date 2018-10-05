package com.nixbyte.project.activities.updateuserdata;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.Authorization.registration.RegistrationActivity;
import com.nixbyte.project.modules.actionbar.ActionBarConstructor;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.FinishActivity;
import com.nixbyte.project.modules.activity.Viewable;

public class UpdateView implements Viewable<UpdateActivity>{

    UpdateActivity activity;
    private ActionBarConstructor.Builder actionBarBulder;
    public ActionBarConstructor actionBar;


    public UpdateView(AppCompatActivity activity, View rootView){
        this.activity = (UpdateActivity) activity;
        this.actionBarBulder = new ActionBarConstructor.Builder(activity)
                .homeBehavior(new FinishActivity(activity))
                .icon(R.drawable.back_arrow)
                .title(R.string.update_title);
    }


    public Activity getActivity(){
        return activity;
    }

    @Override
    public void onAttach(UpdateActivity data) {
        this.actionBar = actionBarBulder.build();
        this.actionBar.setActionBar();
    }
}
