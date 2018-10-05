package com.nixbyte.project.modules.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.nixbyte.project.R;
import com.nixbyte.project.model.userpojo.User;
import com.nixbyte.project.modules.actionbar.ActionBarConstructor;


/**
 * Created by nixbyte on 26.01.17.
 */


public abstract class AbstractActivity<V> extends AppCompatActivity implements Navigator {


    User userAuth;
    SharedPreferences preferences;

    protected FragmentManager fragmentManager;
    protected Presenter presenter;
    protected V view;

    protected abstract Presenter initPresenter();
    protected abstract V initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        preferences = getPreferences(MODE_PRIVATE);
        view = initView();
        presenter = initPresenter();
    }


    public boolean userEntered(){

        SharedPreferences preferences = this.getSharedPreferences("authorization", MODE_PRIVATE);
        String userObject = preferences.getString("userdata", "");
        userAuth = new Gson().fromJson(userObject, User.class);
        if (userAuth != null){
            return true;
        }
        return false;
    }

    @Override
    protected void onStart() {
        presenter.onViewAttached(view);
        super.onStart();
    }


    @Override
    protected void onStop() {
        presenter.onViewDetached();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroyed();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    protected void setFragment(Bundle args, Class<?> fragmentClass, int containerId, boolean addToBackStack) {
        if (fragmentClass == null) {
            return;
        }

        final Fragment fragment = Fragment.instantiate(getApplicationContext(), fragmentClass.getName());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragment.setArguments(args);

        fragmentTransaction.replace(containerId, fragment);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        fragmentTransaction.commitAllowingStateLoss();

    }
    @Override
    public void onNext(Bundle args, Class<?> nextFragmentClass, boolean addToBackStack) {
        setFragment(args, nextFragmentClass, R.id.frame_container, addToBackStack);
    }

    @Override
    public void onBack() {
        fragmentManager.popBackStack();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

}
