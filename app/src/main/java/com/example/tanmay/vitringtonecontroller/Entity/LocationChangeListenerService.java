package com.example.tanmay.vitringtonecontroller.Entity;

import android.app.IntentService;
import android.content.Intent;

public class LocationChangeListenerService extends IntentService {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    public LocationChangeListenerService() {
        super("LocationChangeListenerService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
    }
}
