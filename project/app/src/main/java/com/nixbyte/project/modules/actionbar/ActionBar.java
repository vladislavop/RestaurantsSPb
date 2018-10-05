package com.nixbyte.project.modules.actionbar;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nixbyte.project.R;
import com.nixbyte.project.modules.actionbar.controller.ButtonAction;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.utils.App;


/**
 * Created by nixbyte on 24.01.17.
 */

public abstract class ActionBar {

    protected static Toolbar toolbar;
    protected static AppBarLayout appbarLayout;
    private static final String TAG = ActionBar.class.getName();

    protected ButtonAction homeButtonAction;
    protected ButtonAction contextButtonAction;
    protected CoordinatorLayout.Behavior logoBehavior;


    public ActionBar(View rootView) {
        homeButtonAction = new ButtonAction();
        contextButtonAction = new ButtonAction();
        appbarLayout = (AppBarLayout) rootView.findViewById(R.id.appbar);
        toolbar = (Toolbar) rootView.findViewById(R.id.action_bar);
    }

    public abstract void setActionBar();
    public abstract boolean onCreateOptionsMenu(Menu menu);

    public void setHomeButtonBehavior(ButtonBehavior behavior) {
        homeButtonAction.setBehavior(behavior);
    }

    public void setLogoBehavior(CoordinatorLayout.Behavior logoBehavior) {
        this.logoBehavior = logoBehavior;
    }

    public void setContextButtonBehavior(ButtonBehavior behavior) {
        contextButtonAction.setBehavior(behavior);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                homeButtonAction.performAction();
                break;
            default:
                return true;
        }
        return true;
    }
}
