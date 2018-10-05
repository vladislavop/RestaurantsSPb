package com.nixbyte.project.modules.slidingmenu.model.itemtypes;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.nixbyte.project.R;

/**
 * Created by nixbyte on 01.02.18.
 */

public class StrokeItem extends RecyclerView.ViewHolder {


    public CardView cardView;
    public ImageView stroke;

    public StrokeItem(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.menu_item);
        stroke = (ImageView) itemView.findViewById(R.id.stroke);
    }
}
