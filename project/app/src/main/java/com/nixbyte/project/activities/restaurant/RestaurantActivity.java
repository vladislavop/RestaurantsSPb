package com.nixbyte.project.activities.restaurant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Observable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.RatingBar;

import com.google.gson.Gson;
import com.nixbyte.project.R;
import com.nixbyte.project.activities.maps.MapsActivity;
import com.nixbyte.project.activities.maps.route.MapsRouteActivity;
import com.nixbyte.project.activities.maps.route.MapsRoutePresenter;
import com.nixbyte.project.activities.restaurant.model.RestaurantCommentMenu;
import com.nixbyte.project.activities.restaurant.model.RestaurantInfoMenu;
import com.nixbyte.project.model.userpojo.User;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Presenter;

public class RestaurantActivity extends AbstractActivity<RestaurantView>{

    private int restaurant_id;

    RestaurantInfoMenu menu;
    RestaurantCommentMenu commentMenu;
    private User userAuth;

    FloatingActionButton floatButton;
    AppBarLayout actionBar;

    Toolbar toolbar;

    String dolgota, shirota;
    @Override
    public void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.activity_about_restaurant);

        super.onCreate(savedInstanceState);

        String  address="", number_phone="", site="", kitchen="";
        toolbar = findViewById(R.id.main_toolbar);
        actionBar = findViewById(R.id.main_appbar);
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        shirota = null;
        dolgota = null;
        String title = null;
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            restaurant_id = bundle.getInt("restaurant_id");
            dolgota = bundle.getString("dolgota");
            shirota = bundle.getString("shirota");
            title = bundle.getString("title");
            address = bundle.getString("address");
            number_phone = bundle.getString("number_phone");
            site = bundle.getString("site");
            kitchen = bundle.getString("kitchen");
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            Log.i(RestaurantActivity.class.getSimpleName(), restaurant_id+" ");
        }

        floatButton = findViewById(R.id.create_route);
        floatButton.setOnClickListener(v->{
            Intent intent = new Intent(this, MapsRouteActivity.class);
            intent.putExtra("shirota", shirota);
            intent.putExtra("dolgota", dolgota);
            intent.putExtra("restaurant_coord",shirota+","+dolgota);
            startActivity(intent);
        });

        view.activity = this;
        menu = new RestaurantInfoMenu(this);
        commentMenu = new RestaurantCommentMenu(this);
        view.commentMenu = commentMenu;
        view.onAttach(menu);
        SharedPreferences preferences = this.getSharedPreferences("authorization", MODE_PRIVATE);
        String userObject = preferences.getString("userdata", "");
        userAuth = new Gson().fromJson(userObject, User.class);
        view.setImageView(findViewById(R.id.restaurant_image));
        RestaurantPresenter pres = (RestaurantPresenter)presenter;
        pres.onViewAttached(view);
        pres.address = address;
        pres.kitchen = kitchen;
        pres.site = site;
        pres.number_phone = number_phone;
        pres.getMyRestaurantData(restaurant_id,userAuth.id);
        pres.getPhotoReference(shirota+", "+dolgota, 500,
                "restaurant","AIzaSyCl1_213Utokt_u18XpiyLEnQ9e1HwFGsU", title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.empty, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if(menuItem.getItemId() == android.R.id.home){
            this.finish();
        }
        Log.e("Check", String.valueOf(menuItem.getItemId()));
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected Presenter initPresenter() {
        return new RestaurantPresenter();
    }

    @Override
    protected RestaurantView initView() {
        return new RestaurantView();
    }
}
