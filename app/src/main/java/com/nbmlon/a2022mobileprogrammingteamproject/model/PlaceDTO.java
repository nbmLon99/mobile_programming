package com.nbmlon.a2022mobileprogrammingteamproject.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

@Entity
public class PlaceDTO {
    @Exclude
    @PrimaryKey
    private String id;

    @ColumnInfo(name = "name")
    @PropertyName("name")
    private String name;
    @ColumnInfo(name = "city")
    private String city;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "longitude")
    private String longitude;
    @ColumnInfo(name = "langitude")
    private String langitude;
    @ColumnInfo(name = "phone")
    private String phone;
    @ColumnInfo(name = "url")
    private String url;

    @ColumnInfo(name = "parking")
    private Boolean parking;
    @ColumnInfo(name = "storage")
    private Boolean storage;
    @ColumnInfo(name = "infantHolder")
    private Boolean infantHolder;
    @ColumnInfo(name = "wheelChair")
    private Boolean wheelChair;
    @ColumnInfo(name = "pointRoad")
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
