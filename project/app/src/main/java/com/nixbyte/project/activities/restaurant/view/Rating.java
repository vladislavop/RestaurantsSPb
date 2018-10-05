package com.nixbyte.project.activities.restaurant.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.nixbyte.project.activities.restaurant.model.RestaurantItem;
import com.nixbyte.project.model.restaurant.Rate;

public class Rating extends RestaurantItem {

    public Rating(float user_rate, int five_count, int four_count, int three_count, int two_count, float middle_rate,
                  RatingBar.OnRatingBarChangeListener listener)
    {
        this.user_rate = user_rate;
        this.two_count = two_count;
        this.three_count = three_count;
        this.four_count = four_count;
        this.five_count = five_count;
        this.middle_rate = middle_rate;
        this.listener = listener;
    }

    public int two_count, three_count, four_count, five_count;

    public float middle_rate, user_rate;

    public RatingBar.OnRatingBarChangeListener listener;

    @Override
    public int getType() {
        return TYPE.RATE_ITEM.getValue();
    }

    @Override
    public ViewGroup.LayoutParams getLayoutParams() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View view) {

    }
}
