package com.nbmlon.a2022mobileprogrammingteamproject.repository;


import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDAO;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.utils.MyRoomDatabase;

import java.util.List;

public class TagRepositoy {
    private static TagRepositoy INSTANCE;
    private static TagDAO tagDAO;
    private static MutableLiveData<List<TagDTO>> allContacts;

    private TagRepositoy(){}

    public static void initialize(Application application){
        INSTANCE = new TagRepositoy();
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        tagDAO = db.tagDao();
    }

    public static TagRepositoy getInstance(){
        return INSTANCE;
    }


    public LiveData<List<TagDTO>> getAllContacts() {
        allContacts.setValue(tagDAO.getAll());
        return allContacts;
    }


    public void insert(TagDTO tag) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            tagDAO.insert(tag);
        });
    }

}
