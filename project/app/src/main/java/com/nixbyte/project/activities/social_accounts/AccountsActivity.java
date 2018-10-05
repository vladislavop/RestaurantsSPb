package com.nixbyte.project.activities.social_accounts;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.nixbyte.project.R;
import com.nixbyte.project.model.userpojo.User;
import com.nixbyte.project.modules.accountsmenu.model.AccountList;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Presenter;

public class AccountsActivity extends AbstractActivity<AccountsView> {

    @Override
    public void onCreate(Bundle savedInstanseState){
        super.onCreate(savedInstanseState);
        setContentView(R.layout.activity_social_account);
        view.onAttach(this);
        SharedPreferences preferences = this.getSharedPreferences("authorization", MODE_PRIVATE);
        String userObject = preferences.getString("userdata", "");
        User userAuth = new Gson().fromJson(userObject, User.class);
        ((AccountsPresenter)presenter).getUserAccounts(userAuth.id,"facebook");
    }

    @Override
    protected Presenter initPresenter() {
        return new AccountsPresenter();
    }

    @Override
    protected AccountsView initView() {
        return new AccountsView(this, findViewById(android.R.id.content));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return view.actionBar.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return  view.actionBar.onOptionsItemSelected(menuItem);
    }


}
