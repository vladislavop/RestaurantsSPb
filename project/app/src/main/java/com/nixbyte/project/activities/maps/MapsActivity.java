package com.nixbyte.project.activities.maps;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.appolica.interactiveinfowindow.InfoWindow;
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
import com.nixbyte.project.model.restaurant.ObjectRestaurant;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Presenter;
import com.nixbyte.project.modules.slidingmenu.model.MainMenu;

import java.util.List;


public class MapsActivity extends AbstractActivity<MapsView> implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private final String TAG = this.getClass().getSimpleName();

    private GoogleMap mMap;

    public static String kitchen_type = "All";

    List<ObjectRestaurant> restaurants;

    InfoWindowManager infoWindowManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        MapInfoWindowFragment mapFragment = (MapInfoWindowFragment) getSupportFragmentManager()
                .findFragmentById(R.id.infoWindowMap);
        infoWindowManager = mapFragment.infoWindowManager();
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        InfoWindowManager infoWindowManager;
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(59.945909, 30.359975), 12.0f));
        view.onAttach(mMap);
        ((MapsPresenter) presenter).onViewAttached(view);
        mMap.setOnMarkerClickListener(this);
        ((MapsPresenter)presenter).getMarkersData(1000, kitchen_type);
    }


    @Override
    protected Presenter initPresenter() {
        return new MapsPresenter();
    }

    @Override
    protected MapsView initView() {
        return new MapsView(this, findViewById(android.R.id.content));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom( marker.getPosition(), 12.0f));
        ((MapsPresenter)presenter).setInfoWindow(marker, infoWindowManager);
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return view.actionBar.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return  view.actionBar.onOptionsItemSelected(menuItem);
    }
}
