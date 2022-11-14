package com.nbmlon.a2022mobileprogrammingteamproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import net.daum.mf.map.api.MapView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MapView mapView = new MapView(this);
    }
}