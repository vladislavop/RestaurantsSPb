package com.nixbyte.project.modules.slidingmenu.view;

import android.view.View;
import android.view.ViewGroup;

import com.nixbyte.project.modules.slidingmenu.model.MenuItem;
import com.nixbyte.project.utils.Configurations;

/**
 * Created by nixbyte on 02.02.2018.
 */

public class EmptySpace extends MenuItem {

    int heightDp;

    public EmptySpace(int heightDp) {
        this.heightDp = heightDp;
    }

    @Override
    public int getType() {
        return TYPE.EMPTY_ITEM.getValue();
    }

    @Override
    public int getIcon() {
        return 0;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public ViewGroup.LayoutParams getLayoutParams() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Configurations.dpToPx(heightDp));
    }
}
