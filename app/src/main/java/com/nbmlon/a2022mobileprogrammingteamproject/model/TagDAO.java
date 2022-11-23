package com.nbmlon.a2022mobileprogrammingteamproject.model;

import android.nfc.Tag;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TagDAO {
    @Query("SELECT * FROM tagdto")
    List<TagDTO> getAll();

    @Query("SELECT * FROM tagdto WHERE id IN (:ids)")
    List<TagDTO> loadAllByIds(int[] ids);


    @Insert
    void insertAll(TagDTO... tags);

    @Delete
    void delete(TagDTO tag);
}
