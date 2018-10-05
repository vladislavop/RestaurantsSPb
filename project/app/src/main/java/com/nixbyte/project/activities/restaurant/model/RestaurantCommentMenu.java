package com.nixbyte.project.activities.restaurant.model;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.restaurant.adapter.RestaurantCommentAdapter;
import com.nixbyte.project.activities.restaurant.adapter.RestaurantListAdapter;
import com.nixbyte.project.activities.restaurant.view.ComIt;

import java.util.ArrayList;

public class RestaurantCommentMenu {

    private ArrayList<ComIt> commentsItems;
    private RecyclerView list;
    public RestaurantCommentAdapter adapter;

    public RestaurantCommentMenu(Activity activity){
        list = activity.findViewById(R.id.comment_recycler);
        commentsItems = new ArrayList<>();
        adapter = new RestaurantCommentAdapter(activity, commentsItems);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(activity));
    }

    public void addListItem(ComIt item){
        commentsItems.add(item);
        adapter.notifyDataSetChanged();
    }

}
