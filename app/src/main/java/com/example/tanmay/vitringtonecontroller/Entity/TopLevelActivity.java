package com.example.tanmay.vitringtonecontroller.Entity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import com.example.tanmay.vitringtonecontroller.R;

public class TopLevelActivity extends AppCompatActivity implements OnMapReadyCallback, android.location.LocationListener {
    CheckBox mainBuildingCheckbox,cdmmCheckBox,gdnCheckBox,libraryCheckBox,smvCheckBox,ttCheckBox,sjtCheckBox;
    private GoogleMap mMap;
    LocationManager locationManager;
    android.location.LocationListener locationListener;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.position);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findLocation();
            }
        });
        mainBuildingCheckbox=(CheckBox)findViewById(R.id.main_building_checkBox);
        cdmmCheckBox=(CheckBox)findViewById(R.id.cdmm_checkBox);
        gdnCheckBox=(CheckBox)findViewById(R.id.gdn_checkBox);
        libraryCheckBox=(CheckBox)findViewById(R.id.library_checkBox);
        smvCheckBox=(CheckBox)findViewById(R.id.smv_checkBox);
        ttCheckBox=(CheckBox)findViewById(R.id.tt_checkBox);
        sjtCheckBox=(CheckBox)findViewById(R.id.sjt_checkBox);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getBaseContext(), "Gps is turned on!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        Toast.makeText(getBaseContext(), "Gps is turned off!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        mMap.clear();
        //this will be called everytime the location changes
        mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(),location.getLongitude())).title("You"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),location.getLongitude()),16));
        Toast.makeText(getApplicationContext(),"Latitude is"+location.getLatitude()+"Longitute is"+location.getLongitude(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        // Add a marker in Sydney and move the camera
        LatLng delhi = new LatLng(28.7041, 77.1025);
        mMap.addMarker(new MarkerOptions().position(delhi).title("Delhi").snippet("This is my home <3"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(delhi,16));
        map.getUiSettings().setZoomGesturesEnabled(true);
    }

    //TODO: Add a sharepreference to saved if the check box are ticked or not.

    @Override
    public void onStart()
    {
        super.onStart();

        locationListener = new android.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mMap.clear();
                //this will be called everytime the location changes
                mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(),location.getLongitude())).title("You"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),location.getLongitude()),16));
                Toast.makeText(getApplicationContext(),"Latitude is"+location.getLatitude()+"Longitute is"+location.getLongitude(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };

        mainBuildingCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mainBuildingCheckbox.isChecked())
                {

                }
                else {

                }
            }
        });

        cdmmCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cdmmCheckBox.isChecked())
                {

                }
                else {

                }
            }
        });

        gdnCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(gdnCheckBox.isChecked())
                {

                }
                else {

                }
            }
        });

        libraryCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(libraryCheckBox.isChecked())
                {

                }
                else {

                }
            }
        });

        smvCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(smvCheckBox.isChecked())
                {

                }
                else {

                }
            }
        });

        ttCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ttCheckBox.isChecked())
                {

                }
                else {

                }
            }
        });

        sjtCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(sjtCheckBox.isChecked())
                {

                }
                else {

                }
            }
        });
    }

    private void findLocation() {
        Toast.makeText(getApplicationContext(),"Wait, getting your location",Toast.LENGTH_SHORT).show();

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 10);
            }
        }
        Log.v("Maps Activity","Check1");
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, locationListener);
        // 0 for min distance doesn't means the location manger will
        // request for location all the time. Instead it means the
        // that it will not only consider secs parameter. Note seconds are in miniseconds
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                if(grantResults.length>0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    findLocation();
                }
        }
    }
}
