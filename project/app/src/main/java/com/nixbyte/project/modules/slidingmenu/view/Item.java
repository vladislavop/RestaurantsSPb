package com.nixbyte.project.modules.slidingmenu.view;

import android.view.View;
import android.view.ViewGroup;

import com.nixbyte.project.modules.slidingmenu.model.MenuItem;


/**
 * Created by nixbyte on 30.01.17.
 */

public class Item extends MenuItem {

    int icon;
    String title;
    View.OnClickListener listener;

    public Item(int icon, String title, View.OnClickListener listener) {
        this.icon = icon;
        this.title = title;
        this.listener = listener;
    }

    @Override
    public int getType() {
        return TYPE.DEFAULT_ITEM.getValue();
    }

    @Override
    public int getIcon() {
        return icon;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view);
    }

    @Override
    public ViewGroup.LayoutParams getLayoutParams() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
