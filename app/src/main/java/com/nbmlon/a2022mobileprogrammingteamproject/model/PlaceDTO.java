package com.nbmlon.a2022mobileprogrammingteamproject.model;

import android.os.Parcel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.io.Serializable;


@Entity
@IgnoreExtraProperties
public class PlaceDTO implements Serializable {
    @Exclude
    @PrimaryKey
    private String id;

    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "city")
    private String city;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "longitude")
    private String longitude;
    @ColumnInfo(name = "latitude")
    private String latitude;
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

    protected PlaceDTO(Parcel in) {
        id = in.readString();
        name = in.readString();
        city = in.readString();
        address = in.readString();
        longitude = in.readString();
        latitude = in.readString();
        phone = in.readString();
        url = in.readString();
        byte tmpParking = in.readByte();
        parking = tmpParking == 0 ? null : tmpParking == 1;
        byte tmpStorage = in.readByte();
        storage = tmpStorage == 0 ? null : tmpStorage == 1;
        byte tmpInfantHolder = in.readByte();
        infantHolder = tmpInfantHolder == 0 ? null : tmpInfantHolder == 1;
        byte tmpWheelChair = in.readByte();
        wheelChair = tmpWheelChair == 0 ? null : tmpWheelChair == 1;
        byte tmpPointRoad = in.readByte();
        pointRoad = tmpPointRoad == 0 ? null : tmpPointRoad == 1;
    }

    public PlaceDTO(){}

    public PlaceDTO(String id, String name, String city, String address, String longitude, String latitude, String phone, String url, Boolean parking, Boolean storage, Boolean infantHolder, Boolean wheelChair, Boolean pointRoad) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.phone = phone;
        this.url = url;
        this.parking = parking;
        this.storage = storage;
        this.infantHolder = infantHolder;
        this.wheelChair = wheelChair;
        this.pointRoad = pointRoad;
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public String getCity() {return city;}
    public String getAddress() {return address;}
    public String getLongitude() {return longitude;}
    public String getLatitude() {return latitude;}
    public String getPhone() {return phone;}
    public String getUrl() {return url;}
    public Boolean getParking() {return parking;}
    public Boolean getStorage() {return storage;}
    public Boolean getInfantHolder() {return infantHolder;}
    public Boolean getWheelChair() {return wheelChair;}
    public Boolean getPointRoad() {return pointRoad;}
}
