package com.nixbyte.project.activities.social_accounts;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nixbyte.project.R;
import com.nixbyte.project.modules.accountsmenu.View.Account;
import com.nixbyte.project.modules.accountsmenu.model.AccountList;
import com.nixbyte.project.modules.actionbar.ActionBarConstructor;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.FinishActivity;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.OpenMainMenu;
import com.nixbyte.project.modules.activity.Viewable;

import java.util.ArrayList;

public class AccountsView implements Viewable<AccountsActivity>{

    AccountsActivity activity;

    Context context;

    AccountList accountList;

    private ActionBarConstructor.Builder actionBarBulder;

    public ActionBarConstructor actionBar;

    public AccountsView(AppCompatActivity activity, View rootView){
        this.context = rootView.getContext();
        this.activity = (AccountsActivity)activity;
        this.actionBarBulder = new ActionBarConstructor.Builder(activity)
                .homeBehavior(new FinishActivity(activity))
                .icon(R.drawable.back_arrow);
    }

    public void createList(ArrayList<Account> list){
        accountList = AccountList.createMenu(activity, list);
    }

    public void removeFromList(Account item){
        accountList.Remove(item);
    }

    @Override
    public void onAttach(AccountsActivity accountsActivity) {
        this.actionBar = actionBarBulder.build();
        this.actionBar.setActionBar();
    }

}