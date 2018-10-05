package com.nixbyte.project.activities.restaurant.model.itemtypes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nixbyte.project.R;

public class CommentsItem extends RecyclerView.ViewHolder{
    public TextView user_name;
    public TextView comment;


    public CommentsItem(View itemView) {
        super(itemView);
        user_name = itemView.findViewById(R.id.user_name);
        comment = itemView.findViewById(R.id.comment);
    }
}
