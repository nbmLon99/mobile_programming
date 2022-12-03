package com.nbmlon.a2022mobileprogrammingteamproject.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.repository.TagRepositoy;

import java.util.List;

public class TagViewModel extends ViewModel {
    private TagRepositoy repository;

    private TagViewModel() {
        super();
        repository = TagRepositoy.getInstance();
    }

    public LiveData<List<TagDTO>> getAllTags() {
        return repository.getAllTags();
    }

    public void insert(TagDTO tag) {
        repository.insert(tag);
    }


    /** Tag 업데이트 **/
    public void update(List<TagDTO> tags){
        repository.update(tags);
    }
}
