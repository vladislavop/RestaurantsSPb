package com.nixbyte.project.activities.social_accounts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.nixbyte.project.R;
import com.nixbyte.project.activities.main.MainActivity;
import com.nixbyte.project.model.userpojo.User;
import com.nixbyte.project.model.userpojo.UserSocialNetworks;
import com.nixbyte.project.modules.accountsmenu.View.Account;
import com.nixbyte.project.modules.activity.Presenter;
import com.nixbyte.project.utils.API;
import com.nixbyte.project.utils.ApiFactory;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AccountsPresenter implements Presenter<AccountsView> {

    private final String TAG = this.getClass().getSimpleName();
    AccountsView accountsView;
    Subscription subscription;

    private API getAPI(){
        return ApiFactory.getRetrofit("http://192.168.1.141").create(API.class);
    }

    public void getUserAccounts(int user_id, String network_name){
        subscription = getAPI()
                .getSocialAccounts(user_id, network_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( this::onSubscribe, this::onError,this::onComplete);
    }



    private void onComplete(){
        onDestroyed();
    }

    private void onSubscribe(List<UserSocialNetworks> socialNetworksData){
        ArrayList<Account> returnedList = new ArrayList<>();
        for(UserSocialNetworks data: socialNetworksData){
            if(data.social_net_name.equals("facebook")){
                returnedList.add(new Account(data.login, R.drawable.facebook_logo, (view)->{
                    deleteAccountsData(data.social_net_id,"facebook");
                    accountsView.removeFromList(returnedList.get(returnedList.size()-1));
                }));
            }
            else if(data.social_net_name.equals("vk")){
                returnedList.add(new Account(data.login, R.drawable.vk_logo, (view)->{
                    deleteAccountsData(data.social_net_id, "vk");
                    accountsView.removeFromList(returnedList.get(returnedList.size()-1));
                }));
            }
        }
        accountsView.createList(returnedList);
    }

    private void deleteAccountsData(String social_id, String social_name){
        getAPI()
                .deleteUserAccount(social_id, social_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {}, e->{});
    }

    private void onError(Throwable e){
        Log.e(TAG, e.getStackTrace().toString());
    }


    @Override
    public void onViewAttached(AccountsView view) {
        this.accountsView = view;
    }

    @Override
    public void onViewDetached() {
        accountsView = null;
    }

    @Override
    public void onDestroyed() { }

    @Override
    public void startActivityForResult(Intent intent, int resposeCode) { }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) { }


}
