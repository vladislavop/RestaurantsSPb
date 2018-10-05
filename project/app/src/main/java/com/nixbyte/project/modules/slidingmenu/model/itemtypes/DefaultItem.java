package com.nixbyte.project.modules.slidingmenu.model.itemtypes;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nixbyte.project.R;

/**
 * Created by nixbyte on 01.02.18.
 */

public class DefaultItem extends RecyclerView.ViewHolder  {

    public CardView cardView;
    public ImageView icon;
    public TextView title;

    public DefaultItem(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.menu_item);
        icon = (ImageView) itemView.findViewById(R.id.icon);
        title = (TextView) itemView.findViewById(R.id.title);
    }
}
