package com.nixbyte.project.activities.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.restaurant.model.itemtypes.RatingItem;
import com.nixbyte.project.activities.restaurant.view.ComIt;
import com.nixbyte.project.activities.restaurant.view.Rating;
import com.nixbyte.project.activities.restaurant.view.Snippet;
import com.nixbyte.project.model.response.NearbySearch;
import com.nixbyte.project.model.response.Photos;
import com.nixbyte.project.model.response.Results;
import com.nixbyte.project.model.restaurant.Comment;
import com.nixbyte.project.model.restaurant.Rate;
import com.nixbyte.project.modules.activity.Presenter;
import com.nixbyte.project.utils.API;
import com.nixbyte.project.utils.ApiFactory;
import com.squareup.picasso.Picasso;

import javax.xml.transform.Result;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RestaurantPresenter implements Presenter<RestaurantView>{

    public String address="", number_phone="", site="", kitchen="";

    private static final String TAG = RestaurantPresenter.class.getSimpleName();

    private RestaurantView restaurantView;

    private API getMyAPI(){
        return ApiFactory.getRetrofit("http://192.168.1.141").create(API.class);
    }

    private API getAPI(){
        return ApiFactory.getRetrofit("http://data.gov.spb.ru").create(API.class);
    }

    private API getGoogleAPI(){
        return ApiFactory.getRetrofit("https://maps.googleapis.com").create(API.class);
    }

    private Subscription subscription, spare_subscription;

    String photo;

    public void getPhotoReference(String location, int radius, String type, String key, String name){
        spare_subscription = getGoogleAPI()
                .getPhotoReference(location,radius,type,key,name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(()->{

                })
                .doOnCompleted(()->{
                    spare_subscription.unsubscribe();
                    //onViewDetached();
                })
                .subscribe(search->{
                    for(Results i: search.results){
                        for(Photos j: i.photos){
                            Picasso.get().load("https://maps.googleapis.com/maps/api/place/photo?key=AIzaSyCl1_213Utokt_u18XpiyLEnQ9e1HwFGsU&maxwidth=772&" +
                                    "photoreference=" + j.photo_reference).fit().noFade().into(restaurantView.getImageView());
                        }
                    }

                }, e->{Log.e(TAG, "error", e);});
    }


    public void getMyRestaurantData(int spb_id, int id_user){
        subscription = getMyAPI()
                .getMyRestaurant(spb_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(()->{

                })
                .doOnCompleted(()->{
                    subscription.unsubscribe();

                })
                .subscribe(objectMyRestaurant->{
                            float temp_user_rate = 0;
                            int two_rate=0, three_rate=0, four_rate=0, five_rate=0;
                            int sum_rate=0;
                            for(Rate i:objectMyRestaurant.rates){
                                if(i.id_user == id_user)
                                    temp_user_rate = (float)i.rate;
                                if(i.rate >= 0.1 && i.rate < 3)
                                    two_rate++;
                                if(i.rate >= 3 && i.rate < 4)
                                    three_rate++;
                                if(i.rate >= 4 && i.rate < 5)
                                    four_rate++;
                                if(i.rate == 5)
                                    five_rate++;
                                sum_rate+=i.rate;
                            }
                            float middle_rate = 0;
                            if(objectMyRestaurant.rates.size()!=0){
                                middle_rate = sum_rate/objectMyRestaurant.rates.size();
                            }

                            restaurantView.menu.addListItem(new Rating(temp_user_rate, five_rate, four_rate, three_rate, two_rate, middle_rate,
                                    (ratingBar, value, fromUser)->{ setRate(id_user, spb_id, value);
                                        Intent refresh = restaurantView.activity.getIntent();
                                        restaurantView.activity.finish();
                                        restaurantView.activity.startActivity(refresh);

                                    }));

                            restaurantView.menu.addListItem(new Snippet("Address", address));
                            restaurantView.menu.addListItem(new Snippet("Phone", number_phone));
                            restaurantView.menu.addListItem(new Snippet("Site or email", site));
                            restaurantView.menu.addListItem(new Snippet("Kitchens:", kitchen));
                            for(Comment i:objectMyRestaurant.comments){
                                if(i.id_user == id_user){
                                    restaurantView.commentMenu.addListItem(new ComIt("You", i.content));
                                    continue;
                                }
                                restaurantView.commentMenu.addListItem(new ComIt(i.name+" "+i.surname, i.content));
                            }
                        },
                        e->{
                            Log.e(TAG, e.getStackTrace().toString());
                });
    }


    public void setRate(int user_id, int restaurant_id, float rate){
        subscription = getMyAPI()
                .setRateSpb(user_id, restaurant_id, rate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(()->{
                    //subscription.unsubscribe();
                    //onViewDetached();
                })
                .subscribe(response -> {
                }, e->{
                    Log.e(TAG, e.toString());
                });
    }

    @Override
    public void onViewAttached(RestaurantView view) {
        restaurantView = view;
    }

    @Override
    public void onViewDetached() {
        restaurantView = null;
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
