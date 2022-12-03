package com.nbmlon.a2022mobileprogrammingteamproject.myMap.maplistener;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

/** For Marker Listener **/
public class MyPOIItemEventListener implements MapView.POIItemEventListener {
    public MapView.POIItemEventListener getListener() { return this; }
    
    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
        
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }
}
