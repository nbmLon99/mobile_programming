package com.nbmlon.a2022mobileprogrammingteamproject.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;

public class PlaceViewModel extends ViewModel {

    public final MutableLiveData<PlaceDTO> placeMutableLiveData = new MutableLiveData<>();

    public PlaceViewModel() {
        // trigger user load.
    }


    public void searchForCondition(
            String area,

            int parking,
            int storage,
            int infant,
            int wheel,
            int pointRoad
    ) {

    }

}
