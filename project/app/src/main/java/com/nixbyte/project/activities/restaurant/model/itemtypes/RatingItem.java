package com.nixbyte.project.activities.restaurant.model.itemtypes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.restaurant.RestaurantPresenter;
import com.nixbyte.project.activities.restaurant.RestaurantView;
import com.nixbyte.project.modules.activity.Presenter;

public class RatingItem extends RecyclerView.ViewHolder{
    public RatingBar rate;
    public TextView middle_rate;
    public TextView[] rate_counts;

    public RatingItem(View itemView) {
        super(itemView);
        rate = itemView.findViewById(R.id.rate);
        middle_rate = itemView.findViewById(R.id.middle_rate);
        rate_counts = new TextView[4];
        rate_counts[0] = itemView.findViewById(R.id.five_count);
        rate_counts[1] = itemView.findViewById(R.id.four_count);
        rate_counts[2] = itemView.findViewById(R.id.three_count);
        rate_counts[3] = itemView.findViewById(R.id.two_count);
    }
}
