package com.nbmlon.a2022mobileprogrammingteamproject.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.repository.TagRepositoy;

import java.util.List;

public class TagViewModel extends ViewModel {
    private TagRepositoy repository;
    LiveData<List<TagDTO>> contacts;

    public TagViewModel(final Application application) {
        super();
        repository = TagRepositoy.getInstance();
        contacts = repository.getAllContacts();
    }

    public LiveData<List<TagDTO>> getAllContacts() {
        return contacts;
    }

    public void insert(TagDTO contacts) {
        repository.insert(contacts);
    }
}
