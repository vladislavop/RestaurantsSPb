package com.nixbyte.project.modules.accountsmenu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.nixbyte.project.R;
import com.nixbyte.project.modules.accountsmenu.View.Account;
import com.nixbyte.project.modules.accountsmenu.model.AccountItem;

import java.util.ArrayList;

public class AccountsListAdapter extends RecyclerView.Adapter {

    ArrayList<Account> items;
    Context context;

    public AccountsListAdapter(Context context, ArrayList<Account> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AccountItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.account_card,parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AccountItem accountItem = (AccountItem)holder;
        accountItem.userLogin.setText(items.get(position).getLogin());
        Glide.with(context).load(items.get(position).getNetwork_picture_logo()).into(accountItem.network_logo);
        accountItem.deleteButton.setOnClickListener(items.get(position).getListener());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
