package com.nixbyte.project.activities.maps.route;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.nixbyte.project.R;
import com.nixbyte.project.modules.actionbar.ActionBarConstructor;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.FinishActivity;
import com.nixbyte.project.modules.activity.Viewable;

import java.util.ArrayList;
import java.util.List;

public class MapsRouteView implements Viewable<GoogleMap> {

    public MapsRouteActivity activity;
    GoogleMap map;

    public ActionBarConstructor actionBar;
    private ActionBarConstructor.Builder actionBarBulder;

    public MapsRouteView(MapsRouteActivity activity, View rootView) {
        this.activity = activity;
        this.actionBarBulder = new ActionBarConstructor.Builder(activity)
                .homeBehavior(new FinishActivity(activity))
                .icon(R.drawable.back_arrow)
                .menu(R.menu.main);
    }

    @Override
    public void onAttach(GoogleMap data) {
        map = data;
        this.actionBar = actionBarBulder.build();
        this.actionBar.setActionBar();
    }

    public void drawRoute(List<LatLng> coord_list){
        Polyline line = map.addPolyline(new PolylineOptions()
                .addAll(coord_list)
                .width(12)
                .color(Color.parseColor("#fc9657"))
                .geodesic(true));
    }

    public List<LatLng> decodeOverviewPolyLinePonts(String encoded) {
        List<LatLng> poly = new ArrayList<LatLng>();
        if (encoded != null && !encoded.isEmpty() && encoded.trim().length() > 0) {
            int index = 0, len = encoded.length();
            int lat = 0, lng = 0;

            while (index < len) {
                int b, shift = 0, result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lat += dlat;

                shift = 0;
                result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lng += dlng;

                LatLng p = new LatLng((((double) lat / 1E5)),
                        (((double) lng / 1E5)));
                poly.add(p);
            }
        }
        return poly;
    }

}
