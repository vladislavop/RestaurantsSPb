package com.nixbyte.project.activities.maps;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.appolica.interactiveinfowindow.InfoWindow;
import com.appolica.interactiveinfowindow.InfoWindowManager;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nixbyte.project.R;
import com.nixbyte.project.activities.maps.marker.MarkerFragment;
import com.nixbyte.project.model.restaurant.ObjectRestaurant;
import com.nixbyte.project.modules.actionbar.ActionBarConstructor;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.FinishActivity;
import com.nixbyte.project.modules.activity.Viewable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsView implements Viewable<GoogleMap> {

    private Context context;

    private ActionBarConstructor.Builder actionBarBulder;

    private ProgressDialog dialog;

    GoogleMap map;

    private InfoWindow markerWindow;

    private AppCompatActivity activity;

    public ActionBarConstructor actionBar;

    public MapsView(AppCompatActivity activity, View rootView) {
        this.activity = activity;
        this.context = rootView.getContext();
        this.actionBarBulder = new ActionBarConstructor.Builder(activity)
                .homeBehavior(new FinishActivity(activity))
                .icon(R.drawable.back_arrow)
                .menu(R.menu.main);
    }

    @Override
    public void onAttach(GoogleMap map) {
        this.map = map;
        this.actionBar = actionBarBulder.build();
        this.actionBar.setActionBar();
    }

    public void createMarker(double latitude, double longitude, Map<String, String> extra_data){
        LatLng location;
        location = new LatLng(latitude, longitude);
        map.addMarker(new MarkerOptions().position(location)
                .icon(BitmapDescriptorFactory.defaultMarker(context.getResources().getInteger(R.integer.marker_color_number)))
                .title(extra_data.get("type_data")).snippet(extra_data.get("id")));
    }

    public void setMarkers(List<ObjectRestaurant> restaurants){
        double longitude, latitude;
        Map<String, String> extra_data = new HashMap<>();
        for (ObjectRestaurant i:restaurants){
            if(i.getRow().coord_dolgota != "" && i.getRow().coord_shirota != "") {
                latitude = Double.parseDouble(i.getRow().coord_shirota);
                longitude = Double.parseDouble(i.getRow().coord_dolgota);
                extra_data.put("type_data", "spb");
                extra_data.put("id",i.getNum_id()+"");
                createMarker(latitude, longitude, extra_data);
            }
        }
    }

    public void setInfoWindow(Marker marker, InfoWindowManager infoWindowManager, MarkerFragment markerFragment){
        markerWindow = new InfoWindow(marker, new InfoWindow.MarkerSpecification(5, 39), markerFragment);
        infoWindowManager.toggle(markerWindow, true);
    }

    public void showProgressDialog(){
        dialog = new ProgressDialog(activity);
        dialog.setMessage("Loading...");
        dialog.show();
    }

    public void hideProgressDialog(){
        dialog.dismiss();
    }

}
