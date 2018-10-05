package com.nixbyte.project.modules.slidingmenu.model;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nixbyte.project.R;
import com.nixbyte.project.activities.Authorization.LoginActivity;
import com.nixbyte.project.activities.Authorization.registration.RegistrationActivity;
import com.nixbyte.project.activities.Authorization.registration.RegistrationPresenter;
import com.nixbyte.project.activities.addrestaurants.AddRestaurantActivity;
import com.nixbyte.project.activities.maps.MapsActivity;
import com.nixbyte.project.activities.social_accounts.AccountsActivity;
import com.nixbyte.project.activities.updateuserdata.UpdateActivity;
import com.nixbyte.project.model.userpojo.User;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.slidingmenu.view.EmptySpace;
import com.nixbyte.project.modules.slidingmenu.view.Item;
import com.nixbyte.project.modules.slidingmenu.view.Stroke;
import com.nixbyte.project.modules.slidingmenu.adapter.MenuListAdapter;
import com.nixbyte.project.modules.slidingmenu.controller.MenuListener;
import com.nixbyte.project.utils.App;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by nixbyte on 28.01.15.
 */



public class MainMenu {

    private static final String TAG = MainMenu.class.getName();

    private static ArrayList<MenuItem> navDrawerItems;
    private static MenuListAdapter adapter;
    private static RecyclerView mDrawerList;
    private static DrawerLayout mDrawerLayout;
    private static TextView first_name, second_name;

    public static synchronized MainMenu createMenu(Activity activity) {
        return new MainMenu(activity);
    }

    public static void LockDrawer() {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public static void UnlockDrawer() {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }


    public static void openMenu() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    public static  void closeMenu() {
        mDrawerLayout.closeDrawer(GravityCompat.END);
    }


    private MainMenu(final Activity activity) {

        User userAuth;
        SharedPreferences preferences = activity.getSharedPreferences("authorization", MODE_PRIVATE);
        String userObject = preferences.getString("userdata", "");
        userAuth = new Gson().fromJson(userObject, User.class);
        first_name = activity.findViewById(R.id.textView_FirstName);
        second_name = activity.findViewById(R.id.textView_LastName);

        mDrawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        mDrawerList = (RecyclerView) activity.findViewById(R.id.list_slidermenu);
        navDrawerItems = new ArrayList<MenuItem>();

        mDrawerLayout.setDrawerListener(new MenuListener(activity));

        adapter = new MenuListAdapter(activity, navDrawerItems);

        mDrawerList.setLayoutManager(new LinearLayoutManager(activity));

        mDrawerList.setAdapter(adapter);

        if(userAuth != null){
            first_name.setText(userAuth.name);
            second_name.setText(userAuth.surname);
            MainMenu.addMenuItem(new EmptySpace(48*1));
            MainMenu.addMenuItem(new Item(R.drawable.options, "Options", view->{

                Intent intent = new Intent(activity, UpdateActivity.class);
                activity.startActivity(intent);
            }));
            MainMenu.addMenuItem(new Item(R.drawable.add_accounts_menu, "Add social accounts", view->{
                Intent intent = new Intent(activity, AccountsActivity.class);
                activity.startActivity(intent);
            }));

            MainMenu.addMenuItem(new Item(R.drawable.favorite, "Bookmarks", view->{
                Intent intent = new Intent(activity, AddRestaurantActivity.class);
                activity.startActivity(intent);
            }));
            MainMenu.addMenuItem(new EmptySpace(48*3));
            MainMenu.addMenuItem(new Stroke());
            MainMenu.addMenuItem(new Item(R.drawable.exit, "Log out from account", view -> {
                preferences.edit().clear().apply();
                activity.finish();
                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);
            }));

        }
        else {
            MainMenu.addMenuItem(new EmptySpace(48*3));//размер в 4 пункта меню
            MainMenu.addMenuItem(new Stroke());
        }
    }

    private static void addMenuItem(MenuItem item) {
        navDrawerItems.add(item);
        adapter.notifyDataSetChanged();
    }

    private static void addMenuItem(int index, MenuItem item) {
        if(index >= navDrawerItems.size()) {
            navDrawerItems.add(navDrawerItems.size(), item);
        }
        else {

            ArrayList<MenuItem> navItems = new ArrayList<MenuItem>();

            navItems.addAll(0,navDrawerItems.subList(0,index));
            navItems.add(item);
            navItems.addAll(navItems.size(),navDrawerItems.subList(index,navDrawerItems.size()));

            navDrawerItems = new ArrayList<>(navItems);

            adapter = new MenuListAdapter(App.getContext(), navDrawerItems);
            mDrawerList.setAdapter(adapter);
        }
    }

    private static void removeMenuItem(int index) {
        ArrayList<MenuItem> navItems = new ArrayList<MenuItem>();
        if(index < navDrawerItems.size() && index != 0) {
            navItems.addAll(0,navDrawerItems.subList(0,index-1));
            navItems.addAll(navItems.size(),navDrawerItems.subList(index,navDrawerItems.size()));
            navDrawerItems = new ArrayList<>(navItems);
        } else if(index == 0) {
            navDrawerItems.remove(0);
            navItems.addAll(navDrawerItems);
            navDrawerItems = new ArrayList<>(navItems);
        } else if(index == navDrawerItems.size()) {
            navDrawerItems.remove(navDrawerItems.size()-1);
            navItems.addAll(navDrawerItems);
            navDrawerItems = new ArrayList<>(navItems);
        } else {
            Log.e(TAG, "Index of menu item doesn't exist");
        }
        adapter = new MenuListAdapter(App.getContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);
    }

}
