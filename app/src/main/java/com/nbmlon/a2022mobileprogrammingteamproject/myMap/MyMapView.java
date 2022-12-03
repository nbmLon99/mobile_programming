package com.nbmlon.a2022mobileprogrammingteamproject.myMap;

import android.content.Context;
import android.util.AttributeSet;

import com.nbmlon.a2022mobileprogrammingteamproject.myMap.maplistener.MyMapViewEventListener;
import com.nbmlon.a2022mobileprogrammingteamproject.myMap.maplistener.MyPOIItemEventListener;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class MyMapView extends MapView {
    public MyMapView(Context context) {
        super(context);
    }

    public MyMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setMapViewEventListener(new MyMapViewEventListener().getListener());
        this.setPOIItemEventListener(new MyPOIItemEventListener().getListener());
    }

    public MyMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void mapViewCenterPosition(int latitude, int longitude, int zoomlevel){
        // 중심점 변경 + 줌 레벨 변경
        this.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(latitude, longitude), zoomlevel, true);
        // 줌 인
        this.zoomIn(true);
        // 줌 아웃
        this.zoomOut(true);
    }
}
