package com.nixbyte.project.utils;

import com.nixbyte.project.model.response.Geodata;
import com.nixbyte.project.model.response.NearbySearch;
import com.nixbyte.project.model.response.Response;
import com.nixbyte.project.model.response.Results;
import com.nixbyte.project.model.response.Route;
import com.nixbyte.project.model.restaurant.MyRestaurant;
import com.nixbyte.project.model.userpojo.User;
import com.nixbyte.project.model.restaurant.ObjectRestaurant;
import com.nixbyte.project.model.userpojo.UserSocialNetworks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

public interface API {

    @GET("/restaurants/user/api/login_user.php")
    Observable<User> getUser(@Query("email") String login, @Query("password") String password);

    @GET("/restaurants/user/api/facebook.php")
    Observable<User> getUserByFacebook(@Query("facebook_id")String facebook_id, @Query("login") String login);

    @GET("/restaurants/user/api/vk.php")
    Observable<User> getUserByVk(@Query("vk_id")String vk_id, @Query("login") String login);

    @GET("/restaurants/user/api/accounts_data.php")
    Observable<List<UserSocialNetworks>> getSocialAccounts(@Query("id") int user_id, @Query("social_net") String social_network);

    @GET("/restaurants/user/api/delete_accounts_data.php")
    Observable<Response> deleteUserAccount(@Query("social_id") String social_id, @Query("social_net") String social_network);

    @GET("/restaurants/user/api/create_account.php")
    Observable<User> registAccount(@Query("email") String email, @Query("password") String password,
                                       @Query("name") String name, @Query("surname") String surname);

    @Headers("Authorization: Token 536dec6b29bc9c8f615b47459d7d14504d033e0e")
    @GET("/api/v1/datasets/127/versions/1/data")
    Observable<List<ObjectRestaurant>> loadData(@Query("per_page") int per_page);


    @GET("/restaurants/user/api/update_user_data.php")
    Observable<Response> updateUser(@Query("id") int id, @Query("email") String email, @Query("password") String password,
                                    @Query("name") String name, @Query("surname") String surname);



    @GET("/maps/api/place/nearbysearch/json")
    Observable<NearbySearch> getPhotoReference(@Query("location") String location,
                                               @Query("radius") int radius, @Query("types") String type,
                                               @Query("key") String key, @Query("name") String name);


    @GET("/maps/api/directions/json")
    Observable<Geodata> getRoute(@Query("origin") String origin, @Query("destination") String destination,
                                 @Query("key") String key);

    @GET("/restaurants/restaurant/api/restaurantAPI.php")
    Observable<MyRestaurant> getMyRestaurant(@Query("spb_id") int spb_id);

    @GET("/restaurants/comments/api/ratesAPI.php")
    Observable<Response> setRateSpb(@Query("id_user") int id_user, @Query("spb_id") int spb_id,
                                    @Query("rate") float rate);

    @GET("/restaurants/restaurant/api/restaurantAPI.php")
    Observable<MyRestaurant> getMyRestauranByMyRestaurantId(@Query("restaurant_id")int restaurant_id);



}
