package com.nbmlon.a2022mobileprogrammingteamproject.myMap;

import android.content.Context;
import android.util.AttributeSet;

import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.myMap.maplistener.MyMapViewEventListener;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.List;

public class MyMapView extends MapView{
    public MyMapView(Context context, POIItemEventListener poiItemEventListener) {
        super(context);
        this.setMapViewEventListener(new MyMapViewEventListener().getListener());
        this.setPOIItemEventListener(poiItemEventListener);

        //부산시청 위치 center
        mapViewCenterPosition(35,129,7);
    }


    public void MarkingResults(List<PlaceDTO> placeDTOS){
        for (PlaceDTO placeDTO : placeDTOS){
            MapPOIItem marker = new MapPOIItem();
            marker.setItemName(placeDTO.name);
            marker.setUserObject(placeDTO);
            marker.setMapPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(placeDTO.latitude), Double.parseDouble(placeDTO.longitude)));
            this.addPOIItem(marker);
        }
    }

    private void mapViewCenterPosition(int latitude, int longitude, int zoomlevel){
        // 중심점 변경 + 줌 레벨 변경
        this.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(latitude, longitude), zoomlevel, true);
        // 줌 인
        this.zoomIn(true);
        // 줌 아웃
        this.zoomOut(true);
    }

    public void resetPOIItem(){
    }
}
