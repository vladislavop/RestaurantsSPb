package com.nixbyte.project.modules.actionbar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nixbyte.project.R;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.FinishActivity;
import com.nixbyte.project.utils.Configurations;

/**
 * Created by nixbyte on 26.01.17.
 */

public class ActionBarConstructor extends ActionBar {
    private static final String TAG = ActionBarConstructor.class.getName();

    int title;
    int titleColor;
    int menuId;
    int icon;
    int logo;
    int appbarBackgroundColor;
    int appbarBackgroundImage;
    int toolbarHeight;
    int appbarHeight;

    AppCompatActivity activity;
    ButtonBehavior buttonBehavior;

    private ActionBarConstructor(AppCompatActivity activity
            ,View rootView
            ,int title
            ,int titleColor
            ,int icon
            ,int logo
            ,int menuId
            ,int toolbarHeight
            ,int appbarHeight
            ,int appbarBackgroundColor
            ,int appbarBackgroundImage
            ,ButtonBehavior buttonBehavior) {
        super(rootView);

        this.buttonBehavior = buttonBehavior;
        this.title = title;
        this.titleColor = titleColor;
        this.icon = icon;
        this.logo = logo;
        this.menuId = menuId;
        this.toolbarHeight = toolbarHeight;
        this.appbarHeight = appbarHeight;
        this.appbarBackgroundColor = appbarBackgroundColor;
        this.appbarBackgroundImage = appbarBackgroundImage;
        this.activity = activity;
        setHomeButtonBehavior(buttonBehavior);
    }

    @Override
    public void setActionBar() {
        appbarLayout.getLayoutParams().height = appbarHeight;
        appbarLayout.setBackgroundResource(appbarBackgroundColor);
        toolbar.getLayoutParams().height = toolbarHeight;
        toolbar.setNavigationIcon(icon);
        toolbar.setLogo(logo);
        toolbar.setTitle(activity.getString(title));
        toolbar.setTitleTextColor(activity.getResources().getColor(titleColor));
        activity.setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        activity.getMenuInflater().inflate(menuId,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_about:
//                activity.startActivity(new Intent(activity, AboutActivity.class));
            default:
                super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    public static class Builder {

        AppCompatActivity activity;
        View rootView;
        int title;
        int titleColor;
        int menuId;
        int icon;
        int logo;
        int toolbarHeight;
        int appbarHeight;
        int appbarBackgroundColor;
        int appbarBackgroundImage;
        ButtonBehavior homeBehavior;

        public Builder(AppCompatActivity activity){
            this.activity = activity;
            this.rootView = activity.getWindow().getDecorView().getRootView();
            this.title = R.string.app_name;
            this.titleColor = R.color.white;
            this.icon = R.drawable.ic_launcher;
            this.menuId = R.menu.empty;
            this.logo = android.R.color.transparent;
            this.appbarBackgroundColor = R.color.colorPrimary;
            this.appbarHeight = (int)rootView.getResources().getDimension(R.dimen.action_bar_size);
            this.toolbarHeight = (int)rootView.getResources().getDimension(R.dimen.action_bar_size);
            this.appbarBackgroundImage = android.R.color.transparent;
            this.homeBehavior = new FinishActivity(activity);
        }

        public Builder title(int title){
            this.title = title;
            return this;
        }

        public Builder titleColor(int titleColor){
            this.titleColor = titleColor;
            return this;
        }

        public Builder menu(int menuId) {
            this.menuId = menuId;
            return this;
        }

        public Builder homeBehavior(ButtonBehavior behavior) {
            this.homeBehavior = behavior;
            return this;
        }

        public Builder icon(int icon) {
            this.icon = icon;
            return this;
        }

        public Builder toolbarHeight(int dp) {
            this.toolbarHeight = Configurations.dpToPx(dp);
            return this;
        }

        public Builder appbarHeight(int dp) {
            this.appbarHeight = Configurations.dpToPx(dp);
            return this;
        }

        public Builder appbarBackgroundColor(int appbarBackground) {
            this.appbarBackgroundColor = appbarBackground;
            return this;
        }

        public Builder appbarBackgroundImage(int appbarBackgroundImage) {
            this.appbarBackgroundImage = appbarBackgroundImage;
            return this;
        }

        public Builder logo(int logo) {
            this.logo = logo;
            return this;
        }

        public ActionBarConstructor build(){
            return new ActionBarConstructor(activity
                    ,rootView
                    ,title
                    ,titleColor
                    ,icon
                    ,logo
                    ,menuId
                    ,toolbarHeight
                    ,appbarHeight
                    ,appbarBackgroundColor
                    ,appbarBackgroundImage
                    ,homeBehavior);
        }
    }
}

