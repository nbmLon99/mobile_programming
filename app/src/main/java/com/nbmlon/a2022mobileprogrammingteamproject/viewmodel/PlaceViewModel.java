package com.nbmlon.a2022mobileprogrammingteamproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;

import java.util.List;

public class PlaceViewModel extends ViewModel {

    private MutableLiveData<List<PlaceDTO>> placeMutableLiveData = new MutableLiveData<List<PlaceDTO>>();
    public LiveData<List<PlaceDTO>> getPlaces() {return  placeMutableLiveData;}



    public PlaceViewModel() {
        // trigger user load.
    }


    /** startSearch within Firebase - for SearchActivity_Condition **/
    public void searchForCondition(
            String area,

            int parking,
            int storage,
            int infant,
            int wheel,
            int pointRoad
    ) {

    }

    /** startSearch within Room - for SearchActtivity_Tag **/
    public void searchForTags(int[] ids){

    }


}
