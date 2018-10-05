package com.nixbyte.project.modules.restaurantcards.model;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nixbyte.project.R;
import com.nixbyte.project.modules.accountsmenu.View.Account;
import com.nixbyte.project.modules.accountsmenu.adapter.AccountsListAdapter;
import com.nixbyte.project.modules.accountsmenu.model.AccountList;

import java.util.ArrayList;

public class DataMenu {
    private ArrayList<Account> accountItems;
    private static RecyclerView accountList;
    private static AccountsListAdapter adapter;

    public static synchronized DataMenu createMenu(Activity activity, ArrayList<Account> items){
        return new DataMenu(activity, items);
    }

    private DataMenu(final Activity activity, ArrayList<Account> items){
        accountList = activity.findViewById(R.id.accounts_list);
        accountItems = items;
        adapter = new AccountsListAdapter(activity, accountItems);
        accountList.setLayoutManager(new LinearLayoutManager(activity));
        accountList.setAdapter(adapter);
    }

    private String getStrById(Context context, int identifier){
        return  context.getResources().getString(identifier);
    }

    public void Remove(Account item){
        accountItems.remove(item);
        adapter.notifyDataSetChanged();
    }

    public void addMenuItem(Account item){
        accountItems.add(item);
        adapter.notifyDataSetChanged();
    }
}
