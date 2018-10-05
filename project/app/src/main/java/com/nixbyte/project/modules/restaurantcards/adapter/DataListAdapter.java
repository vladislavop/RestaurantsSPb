package com.nixbyte.project.modules.restaurantcards.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.nixbyte.project.R;
import com.nixbyte.project.modules.cardmenu.model.CardItem;
import com.nixbyte.project.modules.cardmenu.view.Card;
import com.nixbyte.project.modules.restaurantcards.model.DataItem;
import com.nixbyte.project.modules.restaurantcards.view.DataCard;

import java.util.ArrayList;

public class DataListAdapter extends RecyclerView.Adapter {

    ArrayList<DataCard> items;
    private Context context;

    public DataListAdapter(Context context,ArrayList<DataCard> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DataItem dataItem = (DataItem) holder;

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
