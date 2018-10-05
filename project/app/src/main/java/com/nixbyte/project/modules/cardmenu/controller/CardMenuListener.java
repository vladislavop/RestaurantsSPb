package com.nixbyte.project.modules.cardmenu.controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.nixbyte.project.activities.main.MainActivity;

public class CardMenuListener implements View.OnClickListener {

    Activity launc_activity;
    Class cl;
    String type;

    public CardMenuListener(Activity activity, Class cl, String type){
        launc_activity = activity;
        this.type = type;
        this.cl = cl;

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(launc_activity, cl);
        intent.putExtra("restaurant_type", type);
        launc_activity.startActivity(intent);
    }
}
