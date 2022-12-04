package com.nbmlon.a2022mobileprogrammingteamproject.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.repository.PlaceRepository;
import com.nbmlon.a2022mobileprogrammingteamproject.searchActivityCondition.SearchActivity_Condition;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PlaceViewModel extends ViewModel {
    public PlaceRepository placeRepository = PlaceRepository.getINSTANCE();

    public PlaceViewModel() {
        // trigger user load.
    }

    public LiveData<List<PlaceDTO>> getResultLiveData(){
        return placeRepository.getRepositoryResult();
    }

    /** startSearch within Firebase - for SearchActivity_Condition **/
    public void searchForCondition(
            String area,

            String parking,
            String storage,
            String infant,
            String wheel,
            String pointRoad
    ) {
        new Runnable() {
            @Override
            public void run() {
                placeRepository.searchCondition("", parking, storage, infant, wheel, pointRoad,
                        new PlaceRepository.CompleteQueryCallback() {
                            @Override
                            public void QueryComplete(List<DocumentSnapshot> documentSnapshots) {
                                ArrayList<PlaceDTO> results = new ArrayList<>();
                                for (DocumentSnapshot dc : documentSnapshots){
                                    results.add( dc.toObject(PlaceDTO.class) );
                                }
                                placeRepository.setLiveDataValue(results);
                            }
                });
            }
        }.run();
    }


    /** startSearch within Room - for SearchActtivity_Tag **/
    public void searchForTags(Set<Integer> ids){

    }


    public void resetPlaceResults(){
        placeRepository.resetLiveData();
    }


}
