package com.nbmlon.a2022mobileprogrammingteamproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.repository.PlaceRepository;
import com.nbmlon.a2022mobileprogrammingteamproject.searchActivityCondition.SearchActivity_Condition;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PlaceViewModel extends ViewModel {
    PlaceRepository placeRepository = PlaceRepository.getINSTANCE();

    private MutableLiveData<List<PlaceDTO>> searchResultMutableLiveData = new MutableLiveData<List<PlaceDTO>>();
    public LiveData<List<PlaceDTO>> searchResults() {return  searchResultMutableLiveData;}
    
    private PlaceViewModel() {
        // trigger user load.
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
                placeRepository.searchCondition(area, parking, storage, infant, wheel, pointRoad,
                        new PlaceRepository.CompleteQueryCallback() {
                            @Override
                            public void QueryComplete(List<DocumentSnapshot> documentSnapshots) {
                                List<PlaceDTO> results = Collections.emptyList();
                                for (DocumentSnapshot dc : documentSnapshots){
                                    results.add( dc.toObject(PlaceDTO.class) );
                                }
                                searchResultMutableLiveData.setValue(results);
                            }
                });
            }
        }.run();
    }


    /** startSearch within Room - for SearchActtivity_Tag **/
    public void searchForTags(Set<String> ids){

    }


}
