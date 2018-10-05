package com.nixbyte.project.activities.kitchenrestaurants.view;

import android.view.View;
import android.view.ViewGroup;

public class KitchRest {

    public String title;
    public String address;
    public String phone;
    public String site;
    public View.OnClickListener transListener;


    public KitchRest(String title, String address, String phone, String site, View.OnClickListener transListenter){
        this.title = title;
        this.address = address;
        this.phone = phone;
        this.site = site;
        this.transListener = transListenter;
    }

    public ViewGroup.LayoutParams getLayoutParams(){
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
