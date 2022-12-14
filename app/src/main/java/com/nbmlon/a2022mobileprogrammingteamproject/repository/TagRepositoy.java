package com.nbmlon.a2022mobileprogrammingteamproject.repository;


import android.app.Application;
import android.nfc.Tag;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDAO;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.utils.MyRoomDatabase;

import java.util.Collections;
import java.util.List;

public class TagRepositoy {
    private static TagRepositoy INSTANCE;
    private static TagDAO tagDAO;
    public static MutableLiveData<List<TagDTO>> allTags = new MutableLiveData<>(Collections.EMPTY_LIST);

    /** Place별 태그 목록 검색결과 **/
    private static MutableLiveData<List<TagDTO>> PlaceTag = new MutableLiveData<>();
    public static LiveData<List<TagDTO>> GetPlaceTag() {return PlaceTag;}

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
                allTags.postValue(tagDAO.getAll());
            }
        });
    }


    /** Tag placeID 업데이트 **/
    public void update(List<TagDTO> tags){
        allTags.setValue(tags);
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            for (TagDTO tagDTO : tags){
                tagDAO.update(tagDTO);
            }
        });
    }

    public void FindTagForPlace(PlaceDTO placeDTO) {
        List<TagDTO> results = Collections.EMPTY_LIST;
        for(TagDTO tagDTO : allTags.getValue()){
            if(tagDTO.place_ids.contains(placeDTO.id))
                results.add(tagDTO);
        }
        PlaceTag.postValue(results);

    }
}
