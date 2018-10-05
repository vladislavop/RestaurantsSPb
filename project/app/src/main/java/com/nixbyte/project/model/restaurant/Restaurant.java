package com.nixbyte.project.model.restaurant;

import com.google.gson.annotations.SerializedName;

public class Restaurant {

    public  String coord_dolgota;

    public String coord_shirota;

    @SerializedName("name_en")
    public String name;

    public String www;

    public String addressline;

    public String email;

    public String kitchen_en;

    public String phone;

    @Override
    public String toString(){
        return new String(www + "\n" + addressline + "\n" + email + "\n" + kitchen_en + "\n" + phone);
    }

}
