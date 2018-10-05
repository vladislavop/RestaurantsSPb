package com.nixbyte.project.activities.Authorization.registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.nixbyte.project.activities.main.MainActivity;
import com.nixbyte.project.model.userpojo.User;
import com.nixbyte.project.modules.activity.Presenter;
import com.nixbyte.project.utils.API;
import com.nixbyte.project.utils.ApiFactory;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegistrationPresenter implements Presenter<RegistrationView>{

    private final String TAG = this.getClass().getSimpleName();

    RegistrationView view;

    Subscription subscription;

    private API getAPI(){
        return ApiFactory.getRetrofit("http://10.0.2.2").create(API.class);
    }


    public void registUser(String email, String password, String name, String surname){
        subscription = getAPI()
                .registAccount(email, password, name, surname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((user -> {
                    if(user != null){
                        Gson pack = new Gson();
                        SharedPreferences preferences = view.getActivity().getSharedPreferences("authorization", Context.MODE_PRIVATE);
                        preferences.edit().clear()
                                .putString("userdata", pack.toJson(user))
                                .commit();
                        Intent intent = new Intent(view.getActivity(), MainActivity.class);
                        view.getActivity().startActivity(intent);
                    }
                }), e->{
                    Log.e(TAG, e.getStackTrace().toString());
                });
    }


    @Override
    public void onViewAttached(RegistrationView view) {
        this.view = view;
    }

    @Override
    public void onViewDetached() {

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
