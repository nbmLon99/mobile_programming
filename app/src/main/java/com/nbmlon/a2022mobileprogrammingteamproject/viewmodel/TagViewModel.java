package com.nbmlon.a2022mobileprogrammingteamproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.repository.PlaceRepository;
import com.nbmlon.a2022mobileprogrammingteamproject.repository.TagRepositoy;

import java.util.List;
import java.util.Set;

public class TagViewModel extends ViewModel {
    private PlaceRepository placeRepository = PlaceRepository.getINSTANCE();
    public TagRepositoy tagRepository = TagRepositoy.getInstance();

    public TagViewModel() {}

    /** LiveData For AllTag **/
    public LiveData<List<TagDTO>> getAllTags() {
        return tagRepository.getAllTags();
    }

    /** LiveData For TagSearching **/
    public LiveData<List<PlaceDTO>> getSearchResult(){
        return placeRepository.getRepositoryTagResult();
    }

    public void insert(TagDTO tag) {
        tagRepository.insert(tag);
    }
    public void delete(TagDTO tag){tagRepository.delete(tag);}
    public void update(List<TagDTO> tags){tagRepository.update(tags);}


    /** 태그로 검색 **/
    public void searchForTags(Set<Integer> tagIDs) {
        List<String> resultPlaceIDs = null;
        if( tagRepository.getAllTags().getValue() != null ){
            for (TagDTO tag : tagRepository.getAllTags().getValue() ){
                if (tagIDs.contains(tag.id)){
                    if( resultPlaceIDs != null)
                        resultPlaceIDs.retainAll(tag.place_ids);
                    else
                        resultPlaceIDs = tag.place_ids;
                }
            }
        }

        placeRepository.searchPlaceFromID(resultPlaceIDs);
    }

    /** Place별 Tag 목록 얻어오기 **/
    public LiveData<List<TagDTO>> TagForPlace(){
        return tagRepository.GetPlaceTag();
    }
    /** 검색 시작 **/
    public void StartFindTagForPlace(PlaceDTO placeDTO){
        new Thread(){
            @Override
            public void run() {
                super.run();
                tagRepository.FindTagForPlace(placeDTO);
            }
        }.start();
    }

    public void resetTagResults() {
        placeRepository.resetTagLiveData();
    }
}
