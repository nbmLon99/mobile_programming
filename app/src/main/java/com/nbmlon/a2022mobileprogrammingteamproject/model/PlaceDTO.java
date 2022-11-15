package com.nbmlon.a2022mobileprogrammingteamproject.model;

import com.google.firebase.firestore.PropertyName;

public class PlaceDTO {
    @PropertyName("name")
    private String name;
    @PropertyName("city")
    private String city;
    @PropertyName("address")
    private String address;

    @PropertyName("longitude")
    private String longitude;
    @PropertyName("langitude")
    private String langitude;

    @PropertyName("phone")
    private String phone;
    @PropertyName("url")
    private String url;

    @PropertyName("parking")
    private Boolean parking;
    @PropertyName("storage")
    private Boolean storage;
    @PropertyName("infantHolder")
    private Boolean infantHolder;
    @PropertyName("wheelChair")
    private Boolean wheelChair;
    @PropertyName("pointRoad")
    private Boolean pointRoad;

    public PlaceDTO(String name,String city, String address, String longitude, String  langitude, String  url, Boolean parking, Boolean storage, Boolean infantHolder , Boolean wheelChair, Boolean pointRoad){
        this.name = name;
        this.city = city;
        this.address = address;
        this.longitude = longitude;
        this.langitude = langitude;
        this.url = url;
        this.parking = parking;
        this.storage = storage;
        this.infantHolder = infantHolder;
        this.wheelChair = wheelChair;
        this.pointRoad = pointRoad;
    }

}
