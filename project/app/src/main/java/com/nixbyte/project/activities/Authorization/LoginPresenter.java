package com.nixbyte.project.activities.Authorization;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;
import com.nixbyte.project.activities.main.MainActivity;
import com.nixbyte.project.model.userpojo.User;
import com.nixbyte.project.modules.activity.Presenter;
import com.nixbyte.project.utils.API;
import com.nixbyte.project.utils.ApiFactory;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import org.json.JSONObject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter implements Presenter<LoginView> {

    Subscription subscription;
    private final String TAG = this.getClass().getSimpleName();

    CallbackManager callbackManager;


    private SharedPreferences.Editor getSharedEditor(AppCompatActivity activity){
        SharedPreferences preferences = activity.getSharedPreferences("authorization", Context.MODE_PRIVATE);
        return preferences.edit();
    }

    private LoginView loginView;

    @Override
    public void onViewAttached(LoginView view) {
        callbackManager=CallbackManager.Factory.create();
        loginView = view;
        loginView.registerFacebook(callbackManager,setFacebookCallBack());
    }

    @Override
    public void onViewDetached() {
        loginView = null;
    }

    @Override
    public void onDestroyed() { }

    @Override
    public void startActivityForResult(Intent intent, int resposeCode) { }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode, data);
        VKSdk.onActivityResult(requestCode, resultCode, data, createVKCallBack());
    }

    public void getUser(String login, String password){
        subscription = getAPI()
                .getUser(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( this::onSubscribe, this::onError,this::onComplete);
    }


    private void getFacebookData(JSONObject object){
        String facebook_id="", login = "";
        try {
            facebook_id = object.getString("id");
            login = object.getString("email");
            Log.i(TAG, facebook_id);
        }
        catch (Exception e){
            Log.e(TAG, e.getStackTrace().toString());
        }
        subscription = getAPI()
                .getUserByFacebook(facebook_id, login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( this::onSubscribe, this::onError,this::onComplete);
    }

    private void getVkData(String id, String login){
        subscription = getAPI()
                .getUserByVk(id, login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( this::onSubscribe, this::onError,this::onComplete);
    }


    public API getAPI(){
        return ApiFactory.getRetrofit("http://192.168.1.141").create(API.class);
    }

    private FacebookCallback<LoginResult> setFacebookCallBack(){
        return new FacebookCallback<LoginResult>(){
            @Override
            public void onSuccess(LoginResult loginResult) {
                loginView.showProgressBar();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                loginView.hideProgressBar();
                                LoginManager.getInstance().logOut();
                                getFacebookData(object);
                            }
                        }
                    );
                Bundle parameters = new Bundle();
                parameters.putString("fields","id, first_name, last_name, email");
                request.setParameters(parameters);
                request.executeAsync();
            }
            
            @Override
            public void onCancel() { }

            @Override
            public void onError(FacebookException error) {
                Log.i(TAG, error.getStackTrace().toString());
            }
        };
    }

    private VKCallback<VKAccessToken> createVKCallBack(){
        return new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                getVkData(res.userId, res.email);
            }

            @Override
            public void onError(VKError error) {

            }
        };
    }


    private void onComplete(){
        loginView.hideProgressBar();
        onDestroyed();
        Intent intent = new Intent(loginView.getLoginActivity().getBaseContext(), MainActivity.class);
        loginView.getLoginActivity().startActivity(intent);
    }

    private void onSubscribe(User user){
        Gson pack = new Gson();
        SharedPreferences.Editor editor = getSharedEditor(loginView.getLoginActivity());
        editor.clear();
        editor.putString("userdata", pack.toJson(user));
        editor.commit();
    }

    private void onError(Throwable e){
        Log.e(TAG, e.getStackTrace().toString());
        loginView.hideProgressBar();
        onDestroyed();
    }


}
