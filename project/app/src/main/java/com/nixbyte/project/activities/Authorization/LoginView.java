package com.nixbyte.project.activities.Authorization;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.nixbyte.project.R;
import com.nixbyte.project.modules.activity.Viewable;

import java.util.ArrayList;
import java.util.Arrays;

public class LoginView implements Viewable<LoginActivity>{

    ProgressBar progressBar;
    private Context context;
    ProgressDialog progressDialog;

    LoginActivity loginActivity;

    private LoginButton facebookButton;



    public LoginView(LoginActivity activity) {
        loginActivity = activity;
        progressDialog = new ProgressDialog(activity, R.style.CircularProgress);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Retrieving data...");
        progressDialog.getWindow().setGravity(Gravity.CENTER);
        facebookButton = loginActivity.findViewById(R.id.facebook_login_button);
        facebookButton.setReadPermissions(Arrays.asList("public_profile","email"));
    }

    @Override
    public void onAttach(LoginActivity view) {

    }

    public void registerFacebook(CallbackManager callbackManager, FacebookCallback<LoginResult> callback){
        facebookButton.registerCallback(callbackManager, callback);
    }


    public LoginActivity getLoginActivity() {
        return loginActivity;
    }

    public void showProgressBar(){
        progressDialog.show();
    }

    public void hideProgressBar(){
        progressDialog.dismiss();
    }

}
