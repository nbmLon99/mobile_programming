package com.nbmlon.a2022mobileprogrammingteamproject.utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDAO;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//https://mynamewoon.tistory.com/15
//https://roomedia.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-RoomDatabase-in-Java
@Database(entities = {TagDTO.class}, version = 1)
public abstract class MyRoomDatabase extends RoomDatabase {


    public abstract TagDAO tagDao();
    public static final int NUMBER_OF_THREADS = 4;

    private static volatile MyRoomDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MyRoomDatabase.class, "myTag")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
