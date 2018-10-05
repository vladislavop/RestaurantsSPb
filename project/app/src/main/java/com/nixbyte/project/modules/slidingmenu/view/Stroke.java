package com.nixbyte.project.modules.slidingmenu.view;

import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nixbyte.project.modules.slidingmenu.model.MenuItem;
import com.nixbyte.project.utils.Configurations;

/**
 * Created by nixbyte on 02.02.2018.
 */

public class Stroke extends MenuItem {

    int widthDp;
    int heightDp;

    public Stroke() {
        this.widthDp = ViewGroup.LayoutParams.MATCH_PARENT;
        this.heightDp = 1;
    }

    public Stroke(int widthDp, int heightDp) {
        this.widthDp = widthDp;
        this.heightDp = heightDp;
    }

    @Override
    public int getType() {
        return TYPE.STROKE_ITEM.getValue();
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
        return new ViewGroup.LayoutParams(widthDp == ViewGroup.LayoutParams.MATCH_PARENT ? widthDp : Configurations.dpToPx(widthDp), Configurations.dpToPx(heightDp));
    }
}
