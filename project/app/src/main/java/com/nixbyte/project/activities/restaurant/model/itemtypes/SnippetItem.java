package com.nixbyte.project.activities.restaurant.model.itemtypes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nixbyte.project.R;

public class SnippetItem extends RecyclerView.ViewHolder {

    public TextView description;
    public TextView infoSnippet;


    public SnippetItem(View itemView) {
        super(itemView);
        description = itemView.findViewById(R.id.describe);
        infoSnippet = itemView.findViewById(R.id.restaurant_snippet);
    }
}
