package com.nbmlon.a2022mobileprogrammingteamproject.model;

import android.os.Parcel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.PropertyName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Entity
@IgnoreExtraProperties
public class PlaceDTO implements Serializable {
    @Exclude
    @PrimaryKey
    public String id;

    @ColumnInfo(name = "name")
    @SerializedName("업체명")  //json deserialize
    @PropertyName("업체명")   //firebase serialize
    public String name;

    @ColumnInfo(name = "city")
    @SerializedName("시군구명")
    @PropertyName("시군구명")
    public String city;

    @ColumnInfo(name = "address")
    @PropertyName("주소")
    public String address;

    @Ignore
    @Exclude
    @SerializedName("도로명")
    public String address_new1;

    @Ignore
    @Exclude
    @SerializedName("도로명상세")
    public String address_new2;

    @Ignore
    @Exclude
    @SerializedName("읍면동명")
    public String address_old1;

    @Ignore
    @Exclude
    @SerializedName("번지")
    public String address_old2;

    @ColumnInfo(name = "longitude")
    @SerializedName("경도")
    @PropertyName("경도")
    public String longitude;

    @ColumnInfo(name = "latitude")
    @SerializedName("위도")
    @PropertyName("위도")
    public String latitude;

    @ColumnInfo(name = "phone")
    @SerializedName("전화번호")
    @PropertyName("전화번호")
    public String phone;

    @ColumnInfo(name = "url")
    @SerializedName("홈페이지주소")
    @PropertyName("홈페이지주소")
    public String url;



    @ColumnInfo(name = "parking")
    @SerializedName("주차가능여부")
    @PropertyName("주차가능여부")
    public String parking;
    @ColumnInfo(name = "storage")
    @SerializedName("물품보관함유무")
    @PropertyName("물품보관함유무")
    public String storage;
    @ColumnInfo(name = "infantHolder")
    @SerializedName("유아거치대유무")
    @PropertyName("유아거치대유무")
    public String infantHolder;
    @ColumnInfo(name = "wheelChair")
    @SerializedName("휠체어이동가능여부")
    @PropertyName("휠체어이동가능여부")
    public String wheelChair;
    @ColumnInfo(name = "pointRoad")
    @SerializedName("점자유도로유무")
    @PropertyName("점자유도로유무")
    public String pointRoad;

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
        this.parking = (parking != null)?  ((parking)? "Y" : "N") : null;
        this.storage = (storage != null)?  ((storage)? "Y" : "N") : null;
        this.infantHolder = (infantHolder != null)?  ((infantHolder)? "Y" : "N") : null;
        this.wheelChair = (wheelChair != null)?  ((wheelChair)? "Y" : "N") : null;
        this.pointRoad = (pointRoad != null)?  ((pointRoad)? "Y" : "N") : null;
    }


    /** Get Full Address From PlaceDTO **/
    public String getAddress(){
        String result= "부산광역시 " + this.city + " ";
        if(this.address_new1 != null)
            result += this.address_new1 + " " + this.address_new2;
        else
            result += this.address_old1 + " " + this.address_old2 ;

        return result;
    }
}
