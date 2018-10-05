package com.nixbyte.project.activities.Authorization.registration;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.social_accounts.AccountsActivity;
import com.nixbyte.project.modules.actionbar.ActionBarConstructor;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.FinishActivity;
import com.nixbyte.project.modules.activity.Viewable;

public class RegistrationView implements Viewable<RegistrationActivity> {

    RegistrationActivity activity;
    private ActionBarConstructor.Builder actionBarBulder;
    public ActionBarConstructor actionBar;

    public RegistrationView(AppCompatActivity activity, View rootView){
        this.activity = (RegistrationActivity) activity;
        this.actionBarBulder = new ActionBarConstructor.Builder(activity)
                .homeBehavior(new FinishActivity(activity))
                .icon(R.drawable.back_arrow)
                .title(R.string.registration_title);
    }

    public Activity getActivity(){
        return activity;
    }

    @Override
    public void onAttach(RegistrationActivity data) {
        this.actionBar = actionBarBulder.build();
        this.actionBar.setActionBar();
    }
}
