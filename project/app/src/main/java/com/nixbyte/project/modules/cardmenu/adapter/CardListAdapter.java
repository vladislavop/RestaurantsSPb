package com.nixbyte.project.modules.cardmenu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.nixbyte.project.R;
import com.nixbyte.project.modules.cardmenu.model.CardItem;
import com.nixbyte.project.modules.cardmenu.view.Card;

import java.util.ArrayList;

public class CardListAdapter extends RecyclerView.Adapter {

    ArrayList<Card> items;
    private Context context;

    public CardListAdapter(Context context,ArrayList<Card> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.kitchen_type_card,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CardItem cardItem = (CardItem) holder;
        cardItem.title.setText(items.get(position).getName());
        cardItem.snippet.setText(items.get(position).getSnippet());
        cardItem.go.setOnClickListener(items.get(position).getListener());
        Glide.with(context).load(items.get(position).getPictype()).into(cardItem.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
