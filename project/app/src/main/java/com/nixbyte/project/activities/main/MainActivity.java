package com.nixbyte.project.activities.main;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.cardmenu.CardActivity;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.slidingmenu.model.MainMenu;
import com.nixbyte.project.modules.activity.Presenter;
import com.nixbyte.project.modules.slidingmenu.model.itemtypes.DefaultItem;

public class MainActivity extends AbstractActivity<MainView> {

    public static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        MainMenu.createMenu(this);

        MainMenu.UnlockDrawer();

        super.onCreate(savedInstanceState);

        onNext(null, CardActivity.class, false);

    }


    @Override
    protected Presenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected MainView initView() {
        return new MainView(this,findViewById(android.R.id.content));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return view.actionBar.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return  view.actionBar.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

