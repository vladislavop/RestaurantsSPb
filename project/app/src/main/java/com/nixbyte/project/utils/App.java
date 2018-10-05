package com.nixbyte.project.utils;

import android.app.Application;
import android.content.Context;
import android.support.compat.BuildConfig;
import android.support.multidex.MultiDex;


import com.nixbyte.project.R;
import com.vk.sdk.VKSdk;

/**
 * Created by nixbyte on 24.01.17.
 */

public class App extends Application {
    private static Context context;

    private static final String DEV_MODE = "dev_mode";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        VKSdk.initialize(this);
    }
    public static Context getContext() {
        return context;
    }
}