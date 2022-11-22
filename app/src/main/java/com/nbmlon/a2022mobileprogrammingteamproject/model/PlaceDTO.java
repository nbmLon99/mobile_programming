package com.nbmlon.a2022mobileprogrammingteamproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;

@Entity
public class PlaceDTO implements Parcelable {
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

    protected PlaceDTO(Parcel in) {
        id = in.readString();
        name = in.readString();
        city = in.readString();
        address = in.readString();
        longitude = in.readString();
        langitude = in.readString();
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(city);
        dest.writeString(address);
        dest.writeString(longitude);
        dest.writeString(langitude);
        dest.writeString(phone);
        dest.writeString(url);
        dest.writeByte((byte) (parking == null ? 0 : parking ? 1 : 2));
        dest.writeByte((byte) (storage == null ? 0 : storage ? 1 : 2));
        dest.writeByte((byte) (infantHolder == null ? 0 : infantHolder ? 1 : 2));
        dest.writeByte((byte) (wheelChair == null ? 0 : wheelChair ? 1 : 2));
        dest.writeByte((byte) (pointRoad == null ? 0 : pointRoad ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PlaceDTO> CREATOR = new Creator<PlaceDTO>() {
        @Override
        public PlaceDTO createFromParcel(Parcel in) {
            return new PlaceDTO(in);
        }

        @Override
        public PlaceDTO[] newArray(int size) {
            return new PlaceDTO[size];
        }
    };
}
