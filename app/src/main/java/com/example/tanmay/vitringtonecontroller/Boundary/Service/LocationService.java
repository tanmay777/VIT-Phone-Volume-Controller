package com.example.tanmay.vitringtonecontroller.Boundary.Service;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.tanmay.vitringtonecontroller.Boundary.Database.BuildingInformation;
import com.example.tanmay.vitringtonecontroller.Entity.Home.TopLevelActivity;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Created by tanmay on 7/3/17.
 */

public class LocationService extends Service {

    BuildingInformation buildingInformation = new BuildingInformation();
    LocationManager locationManager;
    android.location.LocationListener locationListener;
    LatLngBounds SJTbounds = new LatLngBounds(new LatLng(12.970162, 79.163478), new LatLng(12.971610, 79.164400));
    LatLngBounds cdmmBlockBound = new LatLngBounds(new LatLng(12.969039, 79.154642), new LatLng(12.969272, 79.155242));
    LatLngBounds mainBuildingBound = new LatLngBounds(new LatLng(12.968908, 79.155376), new LatLng(12.969592, 79.156493));
    LatLngBounds gdnBlockBound = new LatLngBounds(new LatLng(12.969457, 79.154419), new LatLng(12.970424, 79.155291));
    LatLngBounds libraryBound = new LatLngBounds(new LatLng(12.969105, 79.156574), new LatLng(12.969654, 79.157156));
    LatLngBounds smvBound = new LatLngBounds(new LatLng(12.968780, 79.157248), new LatLng(12.969613, 79.158230));
    LatLngBounds ttBound = new LatLngBounds(new LatLng(12.970176, 79.158893), new LatLng(12.971074, 79.160116));

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Log.v("Location Service", "OnStartCommand");

        locationListener = new android.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                if (buildingInformation.getTurnon()) {
                    LatLng locationLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                    if (SJTbounds.contains(locationLatLng) && buildingInformation.getSjtCheckBox()) {
                        Toast.makeText(getApplicationContext(), "In SJT", Toast.LENGTH_SHORT).show();
                        //serviceIntent.putExtra("Phone in academic building",1);
                        silentPhone();
                    } else if (cdmmBlockBound.contains(locationLatLng) && buildingInformation.getCdmmCheckBox()) {
                        Toast.makeText(getApplicationContext(), "In CDMM block", Toast.LENGTH_SHORT).show();
                        //serviceIntent.putExtra("Phone in academic building",1);
                        silentPhone();
                    } else if (mainBuildingBound.contains(locationLatLng) && buildingInformation.getMainBuildingCheckbox()) {
                        Toast.makeText(getApplicationContext(), "In MainBuilding", Toast.LENGTH_SHORT).show();
                        //serviceIntent.putExtra("Phone in academic building",1);
                        silentPhone();
                    } else if (gdnBlockBound.contains(locationLatLng) && buildingInformation.getGdnCheckBox()) {
                        Toast.makeText(getApplicationContext(), "In GDN building", Toast.LENGTH_SHORT).show();
                        //serviceIntent.putExtra("Phone in academic building",1);
                        silentPhone();
                    } else if (libraryBound.contains(locationLatLng) && buildingInformation.getLibraryCheckBox()) {
                        Toast.makeText(getApplicationContext(), "In library", Toast.LENGTH_SHORT).show();
                        //serviceIntent.putExtra("Phone in academic building",1);
                        silentPhone();
                    } else if (smvBound.contains(locationLatLng) && buildingInformation.getSmvCheckBox()) {
                        Toast.makeText(getApplicationContext(), "In SMV", Toast.LENGTH_SHORT).show();
                        //serviceIntent.putExtra("Phone in academic building",1);
                        silentPhone();
                    } else if (ttBound.contains(locationLatLng) && buildingInformation.getTtCheckBox()) {
                        Toast.makeText(getApplicationContext(), "In TT", Toast.LENGTH_SHORT).show();
                        //serviceIntent.putExtra("Phone in academic building",1);
                        silentPhone();
                    } else {
                        Toast.makeText(getApplicationContext(), "Not in any academic building", Toast.LENGTH_SHORT).show();
                        Log.v("building information is",Boolean.toString(buildingInformation.getTurnon()));
                        Log.v(Double.toString(location.getLatitude()),Double.toString(location.getLongitude()));
                        //serviceIntent.putExtra("Phone in academic building",0);
                        loudPhone();
                    }
                } else{
                    Toast.makeText(getApplicationContext(), "Turn it on. To use the service", Toast.LENGTH_SHORT).show();
                    Log.v("building information is",Boolean.toString(buildingInformation.getTurnon()));
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

            }
        };

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 5, locationListener);

        return super.onStartCommand(intent, flags, startId);


    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void silentPhone() {
//this is for to put the device in silent mode with vibrate
        Toast.makeText(getApplicationContext(), "Phone is going to vibrate mode", Toast.LENGTH_SHORT).show();
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (!(audioManager.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE))
            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }

    public void loudPhone() {
        //this is for to put into the ringing mode
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (!(audioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL)) {
            Toast.makeText(getApplicationContext(), "Phone is going to normal mode", Toast.LENGTH_SHORT).show();
            int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);

            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            audioManager.setStreamVolume(AudioManager.STREAM_RING, maxVolume, AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);
        }
    }

}