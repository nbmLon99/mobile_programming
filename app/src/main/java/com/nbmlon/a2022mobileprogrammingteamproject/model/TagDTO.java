package com.nbmlon.a2022mobileprogrammingteamproject.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TagDTO {
    @PrimaryKey
    public String id;

    @ColumnInfo(name = "name")
    public String name;

}
