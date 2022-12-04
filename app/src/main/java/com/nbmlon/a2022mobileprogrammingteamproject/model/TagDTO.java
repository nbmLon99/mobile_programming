package com.nbmlon.a2022mobileprogrammingteamproject.model;

import android.nfc.Tag;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.nbmlon.a2022mobileprogrammingteamproject.utils.myTypeConverter;

import java.util.Collections;
import java.util.List;

@Entity(tableName = "myTag")
public class TagDTO {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;


    //삭제를 위해선 필요하겠네
    @TypeConverters(myTypeConverter.class)
    @ColumnInfo(name = "placeIds")
    public List<String> place_ids = Collections.emptyList();


    /** Add New TagDTO **/
    public TagDTO(String name){
        this.name = name;
    }
}
