package com.nixbyte.project.activities.kitchenrestaurants.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.kitchenrestaurants.model.itemtypes.KitchRestItem;
import com.nixbyte.project.activities.kitchenrestaurants.view.KitchRest;

import java.util.ArrayList;

public class KitchRestAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<KitchRest> items;

    public KitchRestAdapter(Context context, ArrayList<KitchRest> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new KitchRestItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        KitchRestItem kitchRestItem = (KitchRestItem) holder;
        kitchRestItem.title.setText(items.get(position).title);
        kitchRestItem.address.setText(items.get(position).address);
        kitchRestItem.phone.setText(items.get(position).phone);
        kitchRestItem.site.setText(items.get(position).site);
        kitchRestItem.trans.setOnClickListener(items.get(position).transListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
