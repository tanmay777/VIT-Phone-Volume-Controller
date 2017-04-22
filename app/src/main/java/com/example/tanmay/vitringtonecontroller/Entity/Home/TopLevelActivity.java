package com.example.tanmay.vitringtonecontroller.Entity.Home;

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
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tanmay.vitringtonecontroller.Boundary.API.APIConstants;
import com.example.tanmay.vitringtonecontroller.Boundary.Database.BuildingInformation;
import com.example.tanmay.vitringtonecontroller.Boundary.Service.LocationService;
import com.example.tanmay.vitringtonecontroller.Entity.Actors.TimetableDetailsModel;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import com.example.tanmay.vitringtonecontroller.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopLevelActivity extends AppCompatActivity implements OnMapReadyCallback{
    CheckBox mainBuildingCheckbox,cdmmCheckBox,gdnCheckBox,libraryCheckBox,smvCheckBox,ttCheckBox,sjtCheckBox;
    private GoogleMap mMap;
    BuildingInformation buildingInformation=new BuildingInformation();
    //To access location services we need location manager.
    LocationManager locationManager;
    android.location.LocationListener locationListener;
    FloatingActionButton floatingActionButton;
    Button turnOn,turnOff;

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
        turnOn=(Button)findViewById(R.id.turn_on);
        turnOff=(Button)findViewById(R.id.turn_off);

        final Intent serviceIntent=new Intent(this, LocationService.class);

        turnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Service is turning on",Toast.LENGTH_SHORT).show();
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                            android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 10);
                    }
                }
                startService(serviceIntent);
            }
        });

        turnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(serviceIntent);
                Toast.makeText(getApplicationContext(),"Service is turning off",Toast.LENGTH_SHORT).show();
            }
        });
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
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

	String url= APIConstants.TIME_TABLE;
	final StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<TimetableDetailsModel> timetableDetailsModels = new ArrayList<TimetableDetailsModel>();
                        List<TimetableDetailsModel> list = new Gson().fromJson(response, timetableDetailsModels.getClass());

                        for (Object a : list)
                        {
                            System.out.println(a);
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }){
                    @Override
                    protected Map<String,String> getParams()
                    {
                        Map<String, String>params = new HashMap<>();
                        params.put("regNo",getIntent().getStringExtra("regNo"));
                        params.put("psswd",getIntent().getStringExtra("psswd"));
                        return params;
                    }
                };
                Volley.newRequestQueue(getApplicationContext()).add(request);

        locationListener = new android.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mMap.clear();
                //this will be called everytime the location changes
                mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("You"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 16));
                Toast.makeText(getApplicationContext(), "Latitude is" + location.getLatitude() + "Longitute is" + location.getLongitude(), Toast.LENGTH_SHORT).show();
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
                    buildingInformation.setMainBuildingCheckbox(true);
                }
                else {
                    buildingInformation.setMainBuildingCheckbox(false);

                }
            }
        });

        cdmmCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cdmmCheckBox.isChecked())
                {
                    buildingInformation.setCdmmCheckBox(true);
                }
                else {
                    buildingInformation.setCdmmCheckBox(false);
                }
            }
        });

        gdnCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(gdnCheckBox.isChecked())
                {
                    buildingInformation.setGdnCheckBox(true);
                }
                else {
                    buildingInformation.setGdnCheckBox(false);

                }
            }
        });

        libraryCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(libraryCheckBox.isChecked())
                {
                    buildingInformation.setLibraryCheckBox(true);
                }
                else {
                    buildingInformation.setLibraryCheckBox(false);
                }
            }
        });

        smvCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(smvCheckBox.isChecked())
                {
                    buildingInformation.setSmvCheckBox(true);
                }
                else {
                    buildingInformation.setSmvCheckBox(false);
                }
            }
        });

        ttCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ttCheckBox.isChecked())
                {
                    buildingInformation.setTtCheckBox(true);
                }
                else {
                    buildingInformation.setTtCheckBox(false);
                }
            }
        });

        sjtCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(sjtCheckBox.isChecked())
                {
                    buildingInformation.setSjtCheckBox(true);
                }
                else {
                    buildingInformation.setSjtCheckBox(false);
                }
            }
        });
    }

    private void findLocation() {
        Toast.makeText(getApplicationContext(),"Wait, getting your location",Toast.LENGTH_SHORT).show();
        buildingInformation.setTurnon(true);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 10);
            }
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 5, locationListener);

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


    //TODO: Would I need to login first here to get the time table?
    //TODO: Can't I change the name of java classes required by gson?
    //TODO: How to create json when timeformatisnot allowed is not available?

}
