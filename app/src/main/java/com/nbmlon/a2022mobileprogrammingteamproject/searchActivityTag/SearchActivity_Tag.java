package com.nbmlon.a2022mobileprogrammingteamproject.searchActivityTag;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.view.adapter.TagAdapter;
import com.nbmlon.a2022mobileprogrammingteamproject.viewmodel.PlaceViewModel;

import java.util.ArrayList;
import java.util.Set;

public class SearchActivity_Tag extends AppCompatActivity {
    PlaceViewModel placeViewModel = new ViewModelProvider(this).get(PlaceViewModel.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tag);

        //tag list rv연결
        RecyclerView rv = findViewById(R.id.rv_tag_search);
        rv.setAdapter(new TagAdapter(new ArrayList<>()));


        //검색시작
        findViewById(R.id.btn_start_search_tag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<String> tagIDs = ((TagAdapter) rv.getAdapter()).getCheckedTagIDs_for_search();
                placeViewModel.searchForTags(tagIDs);
                //로딩 다이얼로그 띄우기
            }
        });
        //observe 후 검색완료시 finish
    }
}