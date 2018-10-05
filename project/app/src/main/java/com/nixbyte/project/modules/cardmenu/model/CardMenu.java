package com.nixbyte.project.modules.cardmenu.model;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.kitchenrestaurants.KitchRestActivity;
import com.nixbyte.project.activities.maps.MapsActivity;
import com.nixbyte.project.modules.cardmenu.adapter.CardListAdapter;
import com.nixbyte.project.modules.cardmenu.controller.CardMenuListener;
import com.nixbyte.project.modules.cardmenu.view.Card;

import java.util.ArrayList;

public class CardMenu {

    private static ArrayList<Card>  cardItems;
    private static RecyclerView cardList;
    private static CardListAdapter adapter;

    public static synchronized CardMenu createMenu(Activity activity, View view){
        return new CardMenu(activity, view);
    }

    private CardMenu(final Activity activity, View view){
        cardList = view.findViewById(R.id.recylcer_view);
        cardItems = new ArrayList<>();
        adapter = new CardListAdapter(activity, cardItems);
        cardList.setLayoutManager(new LinearLayoutManager(activity));
        cardList.setAdapter(adapter);
        CardMenu.addMenuItem(new Card("Russian kitchen", getStrById(activity,R.string.Russian_kitchen), R.drawable.russian_kitchen,
                new CardMenuListener(activity, KitchRestActivity.class, "Russian")));
        CardMenu.addMenuItem(new Card("Japanese kitchen", getStrById(activity,R.string.Japanese_kitchen), R.drawable.japanese_kitchen,
                new CardMenuListener(activity, KitchRestActivity.class, "Japanese")));
        CardMenu.addMenuItem(new Card("European kitchen", getStrById(activity,R.string.European_kitchen), R.drawable.euro_kitchen,
                new CardMenuListener(activity, KitchRestActivity.class, "European")));
        CardMenu.addMenuItem(new Card("Show all on map", "You can see all restaurants on map", R.drawable.google_map_image,
                new CardMenuListener(activity, MapsActivity.class, null)));
    }

    private String getStrById(Context context, int identifier){
        return  context.getResources().getString(identifier);
    }

    private static void addMenuItem(Card item){
        cardItems.add(item);
        adapter.notifyDataSetChanged();
    }

}
