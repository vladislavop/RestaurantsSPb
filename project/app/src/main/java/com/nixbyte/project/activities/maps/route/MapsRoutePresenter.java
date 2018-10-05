package com.nixbyte.project.activities.maps.route;

import android.content.Intent;
import android.util.Log;

import com.nixbyte.project.model.response.Photos;
import com.nixbyte.project.model.response.Results;
import com.nixbyte.project.modules.activity.Presenter;
import com.nixbyte.project.utils.API;
import com.nixbyte.project.utils.ApiFactory;
import com.squareup.picasso.Picasso;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MapsRoutePresenter implements Presenter<MapsRouteView> {

    MapsRouteView view;

    @Override
    public void onViewAttached(MapsRouteView view) {
        this.view = view;
    }

    @Override
    public void onViewDetached() {
        view = null;
    }

    @Override
    public void onDestroyed() {

    }

    private API getGoogleAPI(){
        return ApiFactory.getRetrofit("https://maps.googleapis.com").create(API.class);
    }

    Subscription subscription;

    public void CreateRoute(String origin, String destination){
        subscription = getGoogleAPI()
                .getRoute(origin, destination, "AIzaSyAnXXwPCOgi3FmjAwgohFRtheh-9NpR6CI")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(()->{

                })
                .doOnCompleted(()->{
                    subscription.unsubscribe();
                    Log.i("Test", "here");
                })
                .subscribe(route->{
                    Log.i("Test", route.routes.get(0).overview_polyline.points+"ldada");
                    view.drawRoute(view.decodeOverviewPolyLinePonts(route.routes.get(0).overview_polyline.points));

                }, e->{
                    Log.i("Test", e.toString());
                });
    }

    @Override
    public void startActivityForResult(Intent intent, int resposeCode) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
