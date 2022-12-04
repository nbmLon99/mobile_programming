package com.nbmlon.a2022mobileprogrammingteamproject.repository;


import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDAO;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.utils.MyRoomDatabase;

import java.util.Collections;
import java.util.List;

public class TagRepositoy {
    private static TagRepositoy INSTANCE;
    private static TagDAO tagDAO;
    public static MutableLiveData<List<TagDTO>> allTags = new MutableLiveData<>(Collections.EMPTY_LIST);

    private TagRepositoy(){}

    public static void initialize(Application application){
        new Thread() {
            @Override
            public void run() {
                INSTANCE = new TagRepositoy();
                MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
                tagDAO = db.tagDao();
                allTags.postValue(tagDAO.getAll());
            }
        }.start();
    }

    public static TagRepositoy getInstance(){
        return INSTANCE;
    }

    public LiveData<List<TagDTO>> getAllTags() {
        return allTags;
    }


    public void insert(TagDTO tag) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            tagDAO.insert(tag);
            List<TagDTO> tmp =allTags.getValue();
            tmp.add(tag);
            allTags.postValue(tmp);
        });
    }

    public void delete(TagDTO tag) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            tagDAO.delete(tag);
            List<TagDTO> tmp =allTags.getValue();
            try {
                tmp.remove(tag);
                allTags.postValue(tmp);
            }
            catch (Exception e){
                getAllTags();
            }
        });
    }


    /** Tag placeID 업데이트 **/
    public void update(List<TagDTO> tags){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            allTags.postValue(tags);
            for (TagDTO tagDTO : tags){
                if(tagDTO.place_ids.size() > 0)
                    tagDAO.update(tagDTO);
                else
                    tagDAO.delete(tagDTO);
            }
        });
    }

}
