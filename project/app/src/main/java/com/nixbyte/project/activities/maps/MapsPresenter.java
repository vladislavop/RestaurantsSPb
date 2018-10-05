package com.nixbyte.project.activities.maps;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.appolica.interactiveinfowindow.InfoWindow;
import com.appolica.interactiveinfowindow.InfoWindowManager;
import com.google.android.gms.maps.model.Marker;
import com.nixbyte.project.activities.maps.marker.MarkerFragment;
import com.nixbyte.project.model.restaurant.ObjectRestaurant;
import com.nixbyte.project.modules.activity.Presenter;
import com.nixbyte.project.utils.API;
import com.nixbyte.project.utils.ApiFactory;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MapsPresenter implements Presenter<MapsView> {

    private MapsView view;

    private final String TAG = this.getClass().getSimpleName();

    private Subscription subscription;

    MarkerFragment fragment;

    private List<ObjectRestaurant> restaurants;

    public List<ObjectRestaurant> getRestaurants() {
        return restaurants;
    }

    public void getMarkersData(int per_page, String kitchen_type){
        subscription = getAPI()
                .loadData(per_page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(()->{
                    view.showProgressDialog();
                })
                .doOnCompleted(()->{
                    view.hideProgressDialog();
                    subscription.unsubscribe();
                })
                .subscribe(objectRestaurants -> {
                    restaurants = objectRestaurants;
                    Log.i(TAG, objectRestaurants.size()+" ");
                    //view.setMarkers(objectRestaurants);
                    getMarkersDataInCategory(kitchen_type);
                },
                e->{
                    view.hideProgressDialog();
                });
    }


    public API getAPI(){
        return ApiFactory.getRetrofit("http://data.gov.spb.ru").create(API.class);
    }


    private void getMarkersDataInCategory(String kitchen_type){
        ArrayList<ObjectRestaurant> temp_list = new ArrayList<>();
        if (kitchen_type.equals("All")){
            view.setMarkers(restaurants);
        }
        else{
            for(ObjectRestaurant restaurant:restaurants) {
                if (restaurant.getRow().kitchen_en.contains(kitchen_type)) {
                    temp_list.add(restaurant);
                }
            }
            view.setMarkers(temp_list);
        }
    }


    public void setInfoWindow(Marker marker, InfoWindowManager infoWindowManager){
        Bundle restaurant_data = new Bundle();
        if(marker.getTitle().equals("spb")){
            restaurant_data.putSerializable("id", marker.getSnippet());
            for(ObjectRestaurant i: restaurants){
                if(i.getNum_id() == Integer.valueOf(marker.getSnippet())){
                    restaurant_data.putSerializable("id", marker.getSnippet());
                    restaurant_data.putSerializable("name", i.getRow().name);
                    restaurant_data.putSerializable("marker_snippet", i.getRow().toString());
                    restaurant_data.putSerializable("dolgota", i.getRow().coord_dolgota+"");
                    restaurant_data.putSerializable("address", i.getRow().addressline);
                    restaurant_data.putSerializable("site", i.getRow().www);
                    restaurant_data.putSerializable("phone", i.getRow().phone);
                    restaurant_data.putSerializable("kitchen", i.getRow().kitchen_en);
                    restaurant_data.putSerializable("shirota", i.getRow().coord_shirota+"");
                }
            }
        }
        MarkerFragment markerFragment = new MarkerFragment();
        markerFragment.setArguments(restaurant_data);
        view.setInfoWindow(marker,infoWindowManager,markerFragment);
    }

    @Override
    public void onViewAttached(MapsView view) {
        this.view = view;
    }

    @Override
    public void onViewDetached() {
        view = null;
    }

    @Override
    public void onDestroyed() { }

    @Override
    public void startActivityForResult(Intent intent, int resposeCode) { }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) { }



}
