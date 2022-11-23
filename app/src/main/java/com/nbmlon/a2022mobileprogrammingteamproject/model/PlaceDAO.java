package com.nbmlon.a2022mobileprogrammingteamproject.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

//https://developer.android.com/training/data-storage/room?hl=ko#java
@Dao
public interface PlaceDAO {
    @Query("SELECT * FROM placedto")
    List<PlaceDTO> getAll();

    @Query("SELECT * FROM placedto WHERE id IN (:ids)")
    List<PlaceDTO> loadAllByIds(int[] ids);

    @Insert
    void insertAll(PlaceDTO... users);

    @Delete
    void delete(PlaceDTO user);
}
