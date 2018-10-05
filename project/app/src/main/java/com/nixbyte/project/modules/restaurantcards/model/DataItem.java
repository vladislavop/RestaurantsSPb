package com.nixbyte.project.modules.restaurantcards.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nixbyte.project.R;

public class DataItem extends RecyclerView.ViewHolder {

    public TextView title, snippet;
    public Button go;
    public ImageView image;

    View.OnClickListener listener;

    public DataItem(View itemView) {
        super(itemView);

    }
}
