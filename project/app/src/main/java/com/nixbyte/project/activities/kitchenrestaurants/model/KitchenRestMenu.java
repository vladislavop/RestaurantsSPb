package com.nixbyte.project.activities.kitchenrestaurants.model;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.kitchenrestaurants.adapter.KitchRestAdapter;
import com.nixbyte.project.activities.kitchenrestaurants.model.itemtypes.KitchRestItem;
import com.nixbyte.project.activities.kitchenrestaurants.view.KitchRest;

import java.util.ArrayList;

public class KitchenRestMenu {

    private KitchRestAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<KitchRest> restList;

    public KitchenRestMenu(Activity activity){
        recyclerView = activity.findViewById(R.id.restaurants_list);
        restList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new KitchRestAdapter(activity, restList);
        recyclerView.setAdapter(adapter);
    }

    public void addMenuItem(KitchRest item){
        restList.add(item);
        adapter.notifyDataSetChanged();
    }

}
