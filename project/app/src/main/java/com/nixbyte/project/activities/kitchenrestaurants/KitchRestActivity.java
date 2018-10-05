package com.nixbyte.project.activities.kitchenrestaurants;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.kitchenrestaurants.adapter.KitchRestAdapter;
import com.nixbyte.project.activities.kitchenrestaurants.model.KitchenRestMenu;
import com.nixbyte.project.activities.kitchenrestaurants.view.KitchRest;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Presenter;

public class KitchRestActivity extends AbstractActivity<KitchRestView>{


    KitchenRestMenu menu;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        menu = new KitchenRestMenu(this);
        view.onAttach(this);
        ((KitchRestPresenter)presenter).onViewAttached(view);
        Bundle bundle = getIntent().getExtras();
        String type = bundle.getString("restaurant_type");
        ((KitchRestPresenter)presenter).loadData(type);
    }

    @Override
    protected Presenter initPresenter() {
        return new KitchRestPresenter();
    }

    @Override
    protected KitchRestView initView() {
        return new KitchRestView(this,findViewById(android.R.id.content));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return view.actionBar.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return  view.actionBar.onOptionsItemSelected(menuItem);
    }
}
