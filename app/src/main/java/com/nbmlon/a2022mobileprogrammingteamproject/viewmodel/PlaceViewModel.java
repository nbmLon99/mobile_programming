package com.nbmlon.a2022mobileprogrammingteamproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;
import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.repository.PlaceRepository;

import java.util.ArrayList;
import java.util.List;

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


    public void resetPlaceResults(){
        placeRepository.resetLiveData();
    }


}
