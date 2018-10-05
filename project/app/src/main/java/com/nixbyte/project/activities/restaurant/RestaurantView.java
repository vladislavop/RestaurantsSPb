package com.nixbyte.project.activities.restaurant;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nixbyte.project.activities.restaurant.model.RestaurantCommentMenu;
import com.nixbyte.project.activities.restaurant.model.RestaurantInfoMenu;
import com.nixbyte.project.model.restaurant.Restaurant;
import com.nixbyte.project.modules.activity.Viewable;

import java.util.Map;

public class RestaurantView implements Viewable<RestaurantInfoMenu>{

    public Activity activity;


    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    private ImageView imageView;

    RestaurantInfoMenu menu;

    public RestaurantCommentMenu commentMenu;

    @Override
    public void onAttach(RestaurantInfoMenu menu) {
        this.menu = menu;
    }

}
