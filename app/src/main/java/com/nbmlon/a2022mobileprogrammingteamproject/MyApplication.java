package com.nbmlon.a2022mobileprogrammingteamproject;

import android.app.Application;

import com.nbmlon.a2022mobileprogrammingteamproject.repository.TagRepositoy;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TagRepositoy.initialize(this);
    }
}
