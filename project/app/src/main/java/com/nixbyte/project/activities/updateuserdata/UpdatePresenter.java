package com.nixbyte.project.activities.updateuserdata;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nixbyte.project.activities.main.MainActivity;
import com.nixbyte.project.model.userpojo.User;
import com.nixbyte.project.modules.activity.Presenter;
import com.nixbyte.project.utils.API;
import com.nixbyte.project.utils.ApiFactory;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UpdatePresenter implements Presenter<UpdateView> {


    private final String TAG = this.getClass().getSimpleName();
    UpdateView view;
    Subscription subscription;

    private API getAPI(){
        return ApiFactory.getRetrofit("http://192.168.1.141").create(API.class);
    }


    public void updateUser(User user){
        subscription = getAPI()
                .updateUser(user.id, user.email, user.password, user.name, user.surname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(()->{
                    Toast.makeText(view.getActivity(), "Error happend, you should insert correct data", Toast.LENGTH_SHORT).show();
                })
                .subscribe((response -> {
                    if(response != null){
                        Gson pack = new Gson();
                        SharedPreferences preferences = view.getActivity().getSharedPreferences("authorization", Context.MODE_PRIVATE);
                        preferences.edit().clear()
                                .putString("userdata", pack.toJson(user))
                                .commit();
                    }
                }), e->{
                    Toast.makeText(view.getActivity(), "Error happend, you should insert correct data", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getStackTrace().toString());
                });
    }


    @Override
    public void onViewAttached(UpdateView view) {
        this.view = view;
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void onDestroyed() {

    }

    @Override
    public void startActivityForResult(Intent intent, int resposeCode) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
