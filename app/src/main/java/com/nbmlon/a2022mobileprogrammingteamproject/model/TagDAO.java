package com.nbmlon.a2022mobileprogrammingteamproject.model;

import android.nfc.Tag;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TagDAO {
    @Query("SELECT * FROM myTag")
    List<TagDTO> getAll();

    @Query("SELECT * FROM myTag WHERE id IN (:ids)")
    List<TagDTO> loadAllByIds(int[] ids);

    @Update
    void update(TagDTO tag);

    @Insert
    void insert(TagDTO tag);

    @Delete
    void delete(TagDTO tag);
}
