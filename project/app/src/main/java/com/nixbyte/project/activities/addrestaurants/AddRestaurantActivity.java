package com.nixbyte.project.activities.addrestaurants;

import android.os.Bundle;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.kitchenrestaurants.KitchRestPresenter;
import com.nixbyte.project.activities.kitchenrestaurants.model.KitchenRestMenu;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Presenter;

public class AddRestaurantActivity extends AbstractActivity<AddRestaurantView> {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        view.onAttach(this);
    }


    @Override
    protected Presenter initPresenter() {
        return new AddRestaurantPresenter();
    }

    @Override
    protected AddRestaurantView initView() {
        return new AddRestaurantView(this, findViewById(android.R.id.content));
    }
}
