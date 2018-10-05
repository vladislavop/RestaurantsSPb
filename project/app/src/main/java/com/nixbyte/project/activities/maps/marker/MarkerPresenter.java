package com.nixbyte.project.activities.maps.marker;

import android.content.Intent;
import android.util.Log;

import com.nixbyte.project.R;
import com.nixbyte.project.model.restaurant.Rate;
import com.nixbyte.project.modules.activity.Presenter;
import com.nixbyte.project.utils.API;
import com.nixbyte.project.utils.ApiFactory;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MarkerPresenter implements Presenter<MarkerFragment> {

    MarkerFragment fragment;
    private Subscription subscription;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onViewAttached(MarkerFragment view) {
        fragment = view;
    }

    @Override
    public void onViewDetached() {
        fragment = null;
    }

    public API getMyAPI(){
        return ApiFactory.getRetrofit("http://192.168.1.141").create(API.class);
    }

    public void getMyRestaurantData(int id, int id_user){
        subscription = getMyAPI()
                .getMyRestaurant(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(()->{
                    fragment.dialog.show();
                })
                .doOnCompleted(()->{
                    fragment.like_counter_text.setText("(" + fragment.like_count + ")");
                    fragment.dislike_counter_text.setText("(" + fragment.dislike_count + ")");
                    fragment.dialog.dismiss();
                    subscription.unsubscribe();
                    onViewDetached();
                })
                .subscribe(objectMyRestaurant->{
                            for(Rate i: objectMyRestaurant.rates){
                                if(id_user == i.id_user){
                                    if(i.rate>=4){
                                        fragment.like.setImageResource(R.drawable.thumb_up);
                                        fragment.like.setTag(R.drawable.thumb_up);
                                    }
                                    else {
                                        fragment.dislike.setImageResource(R.drawable.thumb_down);
                                        fragment.dislike.setTag(R.drawable.thumb_down);
                                    }
                                }
                                if(i.rate>=4){
                                    fragment.like_count++;
                                }
                                else{
                                    fragment.dislike_count++;
                                }
                            }
                        },
                        e->{Log.e(TAG, e.getStackTrace().toString());});
    }


    public void setRate(int user_id, int restaurant_id, int rate){
        subscription = getMyAPI()
                .setRateSpb(user_id, restaurant_id, rate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(()->{
                    subscription.unsubscribe();
                    onViewDetached();
                })
                .subscribe(response -> {

                }, e->{
                    Log.e(TAG, e.toString());
                });
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
