package com.nixbyte.project.activities.kitchenrestaurants;

import android.content.Intent;
import android.util.Log;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.kitchenrestaurants.view.KitchRest;
import com.nixbyte.project.activities.restaurant.RestaurantActivity;
import com.nixbyte.project.model.restaurant.ObjectRestaurant;
import com.nixbyte.project.model.restaurant.Rate;
import com.nixbyte.project.modules.activity.Presenter;
import com.nixbyte.project.utils.API;
import com.nixbyte.project.utils.ApiFactory;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class KitchRestPresenter implements Presenter<KitchRestView>{

    KitchRestView view;

    @Override
    public void onViewAttached(KitchRestView view) {
        this.view = view;
    }

    @Override
    public void onViewDetached() {
        view = null;
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

    Subscription subscription;

    private API getMyAPI(){
        return ApiFactory.getRetrofit("http://192.168.1.141").create(API.class);
    }

    private API getAPI(){
        return ApiFactory.getRetrofit("http://data.gov.spb.ru").create(API.class);
    }

    String temp_str;

    public void loadData(String kitchen_type){
        subscription = getAPI()
                .loadData(1000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(()->{
                    //subscription.unsubscribe();
                    //onViewDetached();
                })
                .subscribe(restaurants -> {
                    for(ObjectRestaurant i:restaurants){
                        String[] temp_mas;
                        if(i.getRow().phone!=null){
                            temp_mas = i.getRow().phone.split("_x000D_\n");
                            for(String j:temp_mas){
                                temp_str+=j+"";
                            }
                        }
                        if(i.getRow().kitchen_en.contains(kitchen_type)) {
                            view.activity.menu.addMenuItem(new KitchRest(i.getRow().name, "Адрес: " + i.getRow().addressline,
                                    "Телефон: " + temp_str, "Сайт или почта: " + i.getRow().www, v -> {
                                String[] temp_mas_inner;
                                String temp_str_inner="";
                                if(i.getRow().phone!=null){
                                    temp_mas_inner = i.getRow().phone.split("_x000D_\n");
                                    for(String j:temp_mas_inner){
                                        temp_str_inner+=j+"";
                                    }
                                }
                                Intent intent = new Intent(view.activity, RestaurantActivity.class);
                                intent.putExtra("title", i.getRow().name);
                                intent.putExtra("restaurant_id", Integer.valueOf(i.getNum_id()));
                                intent.putExtra("dolgota", i.getRow().coord_dolgota);
                                intent.putExtra("shirota", i.getRow().coord_shirota);
                                intent.putExtra("number_phone", temp_str_inner);
                                intent.putExtra("site", i.getRow().www);
                                intent.putExtra("address", i.getRow().addressline);
                                intent.putExtra("kitchen", i.getRow().kitchen_en);
                                view.activity.startActivity(intent);
                            }));
                        }
                        temp_str="";

                    }
                }, e->{
                    Log.e("KitchenPresenter", e.toString());
                });

    }

}
