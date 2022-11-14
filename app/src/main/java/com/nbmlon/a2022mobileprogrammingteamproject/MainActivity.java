package com.nbmlon.a2022mobileprogrammingteamproject;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.nbmlon.a2022mobileprogrammingteamproject.maplistener.MyMapViewEventListener;
import com.nbmlon.a2022mobileprogrammingteamproject.maplistener.MyPOIItemEventListener;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class MainActivity extends AppCompatActivity {
    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void mapViewInitialize() {
        mapView = new MapView(this);
        mapView.setMapViewEventListener(new MyMapViewEventListener().getListener());
        mapView.setPOIItemEventListener(new MyPOIItemEventListener().getListener());
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.mapview);
        mapViewContainer.addView(mapView);
    }

    private void mapViewCenterPosition(int latitude, int longitude, int zoomlevel){
        // 중심점 변경 + 줌 레벨 변경
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(latitude, longitude), zoomlevel, true);
        // 줌 인
        mapView.zoomIn(true);
        // 줌 아웃
        mapView.zoomOut(true);
    }
}