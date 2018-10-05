package com.nixbyte.project.activities.main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nixbyte.project.R;
import com.nixbyte.project.model.main.AuthorizationData;
import com.nixbyte.project.modules.actionbar.ActionBarConstructor;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.OpenMainMenu;
import com.nixbyte.project.modules.activity.Viewable;


/**
 * Created by nixbyte on 07.09.16.
 */
public class MainView implements Viewable<AuthorizationData> {

    private Context context;

    private ActionBarConstructor.Builder actionBarBulder;

    public ActionBarConstructor actionBar;

    public MainView(AppCompatActivity activity, View rootView) {
        this.context = rootView.getContext();
        this.actionBarBulder = new ActionBarConstructor.Builder(activity)
                .homeBehavior(new OpenMainMenu(activity))
                .menu(R.menu.main);
    }

    public Context getContext() {
        return context;
    }

    public void onAttach(AuthorizationData authorizationData) {
//
//        switch (App.getContext().getSharedPreferences(MainActivity.LOGIN_STATE_BUNDLE_KEY, Context.MODE_PRIVATE)
//                .getInt(MainActivity.LOGIN_STATE_BUNDLE_KEY,0)) {
//            case 0:
//                actionBarBulder.icon(R.drawable.ic_account_circle_white_24dp);
//                actionBarBulder.menu(R.menu.);
//                actionBarBulder.homeBehavior(new OpenLoginActivityBehavior(this.activity));
//                actionBarBulder.exitBehavior(new ExitApplicationBehavior(this.activity));
//                break;
//            default:
//                actionBarBulder.menu(R.menu.menu_main);
//                actionBarBulder.icon(R.drawable.ic_menu_white_24dp);
//                actionBarBulder.homeBehavior(new OpenMainMenuBehavior(this.activity));
//                actionBarBulder.exitBehavior(new LogoutBehavior(this.activity));
//        }

        this.actionBar = actionBarBulder.build();
        this.actionBar.setActionBar();
    }



}
