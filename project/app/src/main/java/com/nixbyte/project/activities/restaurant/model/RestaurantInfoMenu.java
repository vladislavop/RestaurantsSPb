package com.nixbyte.project.activities.restaurant.model;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.restaurant.adapter.RestaurantListAdapter;
import com.nixbyte.project.modules.slidingmenu.model.MenuItem;

import java.util.ArrayList;

public class RestaurantInfoMenu {

    private final String TAG = RestaurantInfoMenu.class.getSimpleName();
    private ArrayList<RestaurantItem> infoItems;
    private RecyclerView list;
    public RestaurantListAdapter adapter;

    public RestaurantInfoMenu(Activity activity){
        list = activity.findViewById(R.id.restaurant_recycler);
        infoItems = new ArrayList<>();
        adapter = new RestaurantListAdapter(activity, infoItems);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(activity));
    }

    public RestaurantItem getLastInsertedItem(){
        return infoItems.get(infoItems.size()-1);
    }

    public void addListItem(RestaurantItem item){
        infoItems.add(item);
        adapter.notifyDataSetChanged();
    }

}
