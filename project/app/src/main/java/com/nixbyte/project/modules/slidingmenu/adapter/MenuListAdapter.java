package com.nixbyte.project.modules.slidingmenu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nixbyte.project.R;
import com.nixbyte.project.modules.slidingmenu.model.MenuItem;
import com.nixbyte.project.modules.slidingmenu.model.itemtypes.DefaultItem;
import com.nixbyte.project.modules.slidingmenu.model.itemtypes.EmptyItem;
import com.nixbyte.project.modules.slidingmenu.model.itemtypes.StrokeItem;
import com.nixbyte.project.utils.App;

import java.util.ArrayList;


/**
 * Created by nixbyte on 16.12.14.
 */
public class MenuListAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<MenuItem> items;

    public MenuListAdapter(Context context, ArrayList<MenuItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MenuItem.TYPE type = MenuItem.TYPE.getType(viewType);
        switch (type) {
            case DEFAULT_ITEM:
                return new DefaultItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.default_menu_item,parent,false));
            case EMPTY_ITEM:
                return new EmptyItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_menu_item,parent,false));
            case STROKE_ITEM:
                return new StrokeItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.stroke_menu_item,parent,false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MenuItem.TYPE type = MenuItem.TYPE.getType(holder.getItemViewType());

        switch (type) {
            case DEFAULT_ITEM:
                DefaultItem defaultItem = (DefaultItem) holder;
                defaultItem.cardView.setLayoutParams(items.get(position).getLayoutParams());
                defaultItem.icon.setImageResource(items.get(position).getIcon());
                defaultItem.title.setText(items.get(position).getTitle());
                defaultItem.cardView.setOnClickListener(items.get(position));
                break;
            case EMPTY_ITEM:
                EmptyItem emptyItem = (EmptyItem) holder;
                emptyItem.cardView.setLayoutParams(items.get(position).getLayoutParams());
                break;
            case STROKE_ITEM:
                StrokeItem strokeItem = (StrokeItem) holder;
                strokeItem.stroke.getLayoutParams().width = items.get(position).getLayoutParams().width;
                strokeItem.stroke.getLayoutParams().height = items.get(position).getLayoutParams().height;
                strokeItem.stroke.requestLayout();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

