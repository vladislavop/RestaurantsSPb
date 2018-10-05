package com.nixbyte.project.activities.maps.route;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.appolica.interactiveinfowindow.InfoWindowManager;
import com.appolica.interactiveinfowindow.fragment.MapInfoWindowFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nixbyte.project.R;
import com.nixbyte.project.activities.maps.MapsPresenter;
import com.nixbyte.project.activities.maps.MapsView;
import com.nixbyte.project.model.restaurant.ObjectRestaurant;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Presenter;

import java.security.Permission;
import java.util.List;

public class MapsRouteActivity extends AbstractActivity<MapsRouteView>implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private final String TAG = this.getClass().getSimpleName();

    private GoogleMap mMap;

    public static String kitchen_type = "All";

    InfoWindowManager infoWindowManager;

    MapsRoutePresenter routePresenter;
    LocationManager lm;
    Location location;
    double user_latitude;
    double user_longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        MapInfoWindowFragment mapFragment = (MapInfoWindowFragment) getSupportFragmentManager()
                .findFragmentById(R.id.infoWindowMap);
        infoWindowManager = mapFragment.infoWindowManager();
        mapFragment.getMapAsync(this);

        lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        view.onAttach(mMap);
        routePresenter = (MapsRoutePresenter)presenter;
        routePresenter.onViewAttached(view);
        InfoWindowManager infoWindowManager;
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(59.945909, 30.359975), 12.0f));
        Intent intent = getIntent();
        String restaurant_coord = intent.getStringExtra("restaurant_coord");
        String shirota = intent.getStringExtra("shirota");
        String dolgota = intent.getStringExtra("dolgota");
        LatLng position = new LatLng(Double.valueOf(shirota), Double.valueOf(dolgota));
        mMap.addMarker(new MarkerOptions().position(position).icon(BitmapDescriptorFactory.defaultMarker(this.getResources().getInteger(R.integer.marker_color_number))));

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.ACCESS_FINE_LOCATION},12);
        }
        else {
            location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            user_longitude = location.getLongitude();
            user_latitude = location.getLatitude();
            LatLng position2 = new LatLng(user_latitude, user_longitude);
            mMap.addMarker(new MarkerOptions().position(position2).icon(BitmapDescriptorFactory.defaultMarker(this.getResources().getInteger(R.integer.marker_color_number))));

        }

        routePresenter.CreateRoute(user_latitude+","+user_longitude, restaurant_coord);

    }




    @Override
    protected Presenter initPresenter() {
        return new MapsRoutePresenter();
    }

    @Override
    protected MapsRouteView initView() {
        return new MapsRouteView (this, findViewById(android.R.id.content));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom( marker.getPosition(), 12.0f));
        ((MapsPresenter)presenter).setInfoWindow(marker, infoWindowManager);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return view.actionBar.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return  view.actionBar.onOptionsItemSelected(menuItem);
    }
}
