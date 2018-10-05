package com.nixbyte.project.activities.restaurant.view;

import android.view.View;
import android.view.ViewGroup;

import com.nixbyte.project.activities.restaurant.model.RestaurantItem;

public class ComIt extends RestaurantItem{

    public String user_name;
    public String content;


    public ComIt(String user_name, String content) {
        this.user_name = user_name;
        this.content = content;
    }


    @Override
    public int getType() {
        return  TYPE.COMMENT_ITEM.getValue();
    }

    @Override
    public ViewGroup.LayoutParams getLayoutParams() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View view) { }
}
