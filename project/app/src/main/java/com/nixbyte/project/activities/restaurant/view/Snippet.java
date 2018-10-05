package com.nixbyte.project.activities.restaurant.view;

import android.view.View;
import android.view.ViewGroup;

import com.nixbyte.project.activities.restaurant.model.RestaurantItem;

public class Snippet extends RestaurantItem{

    public String content;
    public String description;

    public Snippet(String description, String content){
        this.description = description;
        this.content = content;
    }

    @Override
    public int getType() {
        return TYPE.SNIPPET_ITEM.getValue();
    }

    @Override
    public ViewGroup.LayoutParams getLayoutParams() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View view) { }
}
