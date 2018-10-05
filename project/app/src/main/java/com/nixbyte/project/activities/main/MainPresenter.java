package com.nixbyte.project.activities.main;

import android.content.Intent;

import com.nixbyte.project.modules.activity.Presenter;
import com.nixbyte.project.utils.ApiFactory;

/**
 * Created by nixbyte on 26.01.17.
 */

public class MainPresenter implements Presenter<MainView> {

    private MainView view;

    @Override
    public void onViewAttached(MainView view) {
        this.view = view;
        this.view.onAttach(null);// Added authorization token object
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void onDestroyed() { }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
