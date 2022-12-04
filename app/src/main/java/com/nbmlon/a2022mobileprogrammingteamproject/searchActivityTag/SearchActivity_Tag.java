package com.nbmlon.a2022mobileprogrammingteamproject.searchActivityTag;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.nbmlon.a2022mobileprogrammingteamproject.MyApplication;
import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.dialog.LoadingDialog;
import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.viewmodel.PlaceViewModel;
import com.nbmlon.a2022mobileprogrammingteamproject.viewmodel.TagViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SearchActivity_Tag extends AppCompatActivity {
    private TagViewModel tagViewModel;
    private SearchTagAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tag);
        tagViewModel =  new ViewModelProvider(this).get(TagViewModel.class);
        //tag list rv연결
        RecyclerView rv = findViewById(R.id.rv_tag_search);

        tagViewModel.tagRepository.allTags.observe(this, new Observer<List<TagDTO>>() {
            @Override
            public void onChanged(List<TagDTO> tagDTOS) {
                mAdapter = (new SearchTagAdapter(tagDTOS));
                rv.setAdapter(mAdapter);
            }
        });

        //검색시작
        findViewById(R.id.btn_start_search_tag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로딩 다이얼로그 띄우기
                LoadingDialog loadingDialog = new LoadingDialog(SearchActivity_Tag.this, "검색중");
                loadingDialog.show();

                Set<Integer> tagIDs = mAdapter.getCheckedTagIDs_for_search();
                tagViewModel.searchForTags(tagIDs);
                tagViewModel.searchResultMutableLiveData.observe(SearchActivity_Tag.this, new Observer<List<PlaceDTO>>() {
                    @Override
                    public void onChanged(List<PlaceDTO> placeDTOS) {
                        Log.d("검색완료", placeDTOS.toString());
                        loadingDialog.dismiss();
                        SearchActivity_Tag.this.finish();
                    }
                });
            }
        });
        //observe 후 검색완료시 finish
    }
}