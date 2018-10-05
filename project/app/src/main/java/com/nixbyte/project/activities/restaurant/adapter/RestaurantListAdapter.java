package com.nixbyte.project.activities.restaurant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.restaurant.RestaurantPresenter;
import com.nixbyte.project.activities.restaurant.model.RestaurantItem;
import com.nixbyte.project.activities.restaurant.model.itemtypes.CommentInputItem;
import com.nixbyte.project.activities.restaurant.model.itemtypes.CommentsItem;
import com.nixbyte.project.activities.restaurant.model.itemtypes.RatingItem;
import com.nixbyte.project.activities.restaurant.model.itemtypes.SnippetItem;
import com.nixbyte.project.activities.restaurant.view.ComIt;
import com.nixbyte.project.activities.restaurant.view.CommentInput;
import com.nixbyte.project.activities.restaurant.view.Rating;
import com.nixbyte.project.activities.restaurant.view.Snippet;
import com.nixbyte.project.model.restaurant.Restaurant;
import com.nixbyte.project.modules.slidingmenu.model.MenuItem;

import java.util.ArrayList;

public class RestaurantListAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<RestaurantItem> items;
    private RestaurantPresenter presenter;
    private int user_id, restaurant_id;

    public RestaurantListAdapter(Context context, ArrayList<RestaurantItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position){
        return items.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RestaurantItem.TYPE type = RestaurantItem.TYPE.getType(viewType);
        switch (type){
            case COMMENT_ITEM:
                return new CommentsItem(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.restaurant_comment_card,parent, false));
            case RATE_ITEM:
                return new RatingItem(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.restaurant_rate_card,parent, false));
            case SNIPPET_ITEM:
                return new SnippetItem(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.restaurant_info_card,parent, false));
            //case COMMENT_INPUT_ITEM:
                //return new CommentInputItem(LayoutInflater.from(parent.getContext())
                //        .inflate(R.layout.comment_input_card,parent,false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RestaurantItem.TYPE type = RestaurantItem.TYPE.getType(holder.getItemViewType());
        switch (type){
            case COMMENT_ITEM:
                CommentsItem commentsItem = (CommentsItem) holder;
                commentsItem.user_name.setText(((ComIt)items.get(position)).user_name);
                commentsItem.comment.setText(((ComIt)items.get(position)).content);
                break;
            case RATE_ITEM:
                RatingItem ratingItem = (RatingItem)holder;
                ratingItem.middle_rate.setText(String.valueOf(((Rating)items.get(position)).middle_rate));
                ratingItem.rate.setRating(((Rating)items.get(position)).user_rate);
                ratingItem.rate.setOnRatingBarChangeListener(((Rating) items.get(position)).listener);
                ratingItem.rate_counts[0].setText(String.valueOf(((Rating)items.get(position)).five_count));
                ratingItem.rate_counts[1].setText(String.valueOf(((Rating)items.get(position)).four_count));
                ratingItem.rate_counts[2].setText(String.valueOf(((Rating)items.get(position)).three_count));
                ratingItem.rate_counts[3].setText(String.valueOf(((Rating)items.get(position)).two_count));
                break;
            case SNIPPET_ITEM:
                SnippetItem snippetItem = (SnippetItem)holder;
                snippetItem.description.setText(((Snippet)items.get(position)).description);
                snippetItem.infoSnippet.setText(((Snippet)items.get(position)).content);
            //case COMMENT_INPUT_ITEM:
                //CommentInputItem commentInputItem = (CommentInputItem)holder;

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
