package com.nixbyte.project.activities.kitchenrestaurants.model.itemtypes;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nixbyte.project.R;

public class KitchRestItem extends RecyclerView.ViewHolder {

    public TextView title, address, phone, site;
    public ImageButton trans;

    public KitchRestItem(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        address = itemView.findViewById(R.id.address);
        phone = itemView.findViewById(R.id.phone);
        site = itemView.findViewById(R.id.site);
        trans = itemView.findViewById(R.id.transaction);
    }
}
