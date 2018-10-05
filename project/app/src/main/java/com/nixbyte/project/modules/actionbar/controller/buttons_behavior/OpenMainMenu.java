package com.nixbyte.project.modules.actionbar.controller.buttons_behavior;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

import com.nixbyte.project.R;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.modules.slidingmenu.model.MainMenu;


/**
 * Created by nixbyte on 26.01.17.
 */

public class OpenMainMenu implements ButtonBehavior {
    private AppCompatActivity activity;

    public OpenMainMenu(AppCompatActivity activity) {
        this.activity = activity;
    }
    @Override
    public void perform() {
        MainMenu menu = MainMenu.createMenu(activity);
        MainMenu.openMenu();

    }
}
