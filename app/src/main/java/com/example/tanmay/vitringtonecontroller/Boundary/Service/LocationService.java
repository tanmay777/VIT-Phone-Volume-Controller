package com.example.tanmay.vitringtonecontroller.Boundary.Service;

import android.Manifest;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.example.tanmay.vitringtonecontroller.Boundary.Database.BuildingInformation;
import com.example.tanmay.vitringtonecontroller.Entity.TopLevelActivity;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Created by tanmay on 7/3/17.
 */

public class LocationService extends IntentService{

    Context context;
    BuildingInformation buildingInformation=new BuildingInformation();
    LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
    android.location.LocationListener locationListener;
    LatLngBounds SJTbounds=new LatLngBounds(new LatLng(12.970162, 79.163478),new LatLng(12.971610, 79.164400));
    LatLngBounds cdmmBlockBound= new LatLngBounds(new LatLng(12.969039, 79.154642),new LatLng(12.969272, 79.155242));
    LatLngBounds mainBuildingBound= new LatLngBounds(new LatLng(12.968908, 79.155376),new LatLng(12.969592, 79.156493));
    LatLngBounds gdnBlockBound=new LatLngBounds(new LatLng(12.969457, 79.154419),new LatLng(12.970424, 79.155291));
    LatLngBounds libraryBound=new LatLngBounds(new LatLng(12.969105, 79.156574),new LatLng(12.969654, 79.157156));
    LatLngBounds smvBound=new LatLngBounds(new LatLng(12.968780, 79.157248),new LatLng(12.969613, 79.158230));
    LatLngBounds ttBound=new LatLngBounds(new LatLng(12.970176, 79.158893),new LatLng(12.971074, 79.160116));

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 5, locationListener);

        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng locationLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                if (SJTbounds.contains(locationLatLng)&&buildingInformation.getSjtCheckBox()) {
                    //   Toast.makeText(TopLevelActivity.this, "In SJT", Toast.LENGTH_SHORT).show();
                    //serviceIntent.putExtra("Phone in academic building",1);
                    silentPhone();
                }
                else if (cdmmBlockBound.contains(locationLatLng)&&buildingInformation.getCdmmCheckBox()) {
                    //Toast.makeText(TopLevelActivity.this, "In CDMM block", Toast.LENGTH_SHORT).show();
                    //serviceIntent.putExtra("Phone in academic building",1);
                    silentPhone();
                }
                else if (mainBuildingBound.contains(locationLatLng)&&buildingInformation.getMainBuildingCheckbox()) {
                    //Toast.makeText(getApplicationContext(), "In MainBuilding", Toast.LENGTH_SHORT).show();
                    //serviceIntent.putExtra("Phone in academic building",1);
                    silentPhone();
                }
                else if (gdnBlockBound.contains(locationLatLng)&&buildingInformation.getGdnCheckBox()){
                    //Toast.makeText(getApplicationContext(), "In GDN building", Toast.LENGTH_SHORT).show();
                    //serviceIntent.putExtra("Phone in academic building",1);
                    silentPhone();
                }
                else if (libraryBound.contains(locationLatLng)&&buildingInformation.getLibraryCheckBox()) {
                    //Toast.makeText(getApplicationContext(), "In library", Toast.LENGTH_SHORT).show();
                    //serviceIntent.putExtra("Phone in academic building",1);
                    silentPhone();
                }
                else if (smvBound.contains(locationLatLng)&&buildingInformation.getSmvCheckBox()) {
                    //Toast.makeText(getApplicationContext(), "In SMV", Toast.LENGTH_SHORT).show();
                    //serviceIntent.putExtra("Phone in academic building",1);
                    silentPhone();
                }
                else if (ttBound.contains(locationLatLng)&&buildingInformation.getTtCheckBox()) {
                    //Toast.makeText(getApplicationContext(), "In TT", Toast.LENGTH_SHORT).show();
                    //serviceIntent.putExtra("Phone in academic building",1);
                    silentPhone();
                }
                else {
                    //Toast.makeText(getApplicationContext(), "Not in any academic building", Toast.LENGTH_SHORT).show();
                    //serviceIntent.putExtra("Phone in academic building",0);
                    loudPhone();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    protected void onHandleIntent(Intent intent) {

    }

    public LocationService(String name) {
        super(name);
    }

    public void silentPhone()
    {
//this is for to put the device in silent mode with vibrate
        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        if(!(audioManager.getRingerMode()==AudioManager.RINGER_MODE_VIBRATE))
        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }

    public void loudPhone() {
        //this is for to put into the ringing mode
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (!(audioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL)) {
               int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);

            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            audioManager.setStreamVolume(AudioManager.STREAM_RING, maxVolume, AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);
        }
    }
}
