package com.example.android.smartreminder;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by samir692 on 3/11/18.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
