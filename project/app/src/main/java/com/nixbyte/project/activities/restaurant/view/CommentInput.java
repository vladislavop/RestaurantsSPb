package com.nixbyte.project.activities.restaurant.view;

import android.view.View;
import android.view.ViewGroup;

import com.nixbyte.project.activities.restaurant.model.RestaurantItem;

public class CommentInput extends RestaurantItem {

    public View.OnClickListener listener;

    public CommentInput(View.OnClickListener listener){
        this.listener = listener;
    }


    @Override
    public int getType() {
        return TYPE.COMMENT_INPUT_ITEM.getValue();
    }

    @Override
    public ViewGroup.LayoutParams getLayoutParams() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View view) {

    }
}
