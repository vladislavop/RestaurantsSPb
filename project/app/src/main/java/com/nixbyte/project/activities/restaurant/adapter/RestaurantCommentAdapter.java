package com.nixbyte.project.activities.restaurant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.restaurant.model.RestaurantItem;
import com.nixbyte.project.activities.restaurant.model.itemtypes.CommentsItem;
import com.nixbyte.project.activities.restaurant.view.ComIt;

import java.util.ArrayList;

public class RestaurantCommentAdapter extends RecyclerView.Adapter {

    private ArrayList<ComIt> items;
    Context context;


    public RestaurantCommentAdapter(Context context, ArrayList<ComIt> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentsItem(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_comment_card,parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommentsItem commentsItem = (CommentsItem) holder;
        commentsItem.user_name.setText((items.get(position)).user_name);
        commentsItem.comment.setText((items.get(position)).content);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
