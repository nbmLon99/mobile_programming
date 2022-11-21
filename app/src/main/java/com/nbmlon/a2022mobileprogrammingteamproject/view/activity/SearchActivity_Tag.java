package com.nbmlon.a2022mobileprogrammingteamproject.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.view.adapter.TagAdapter;
import com.nbmlon.a2022mobileprogrammingteamproject.viewmodel.PlaceViewModel;

public class SearchActivity_Tag extends AppCompatActivity {
    PlaceViewModel placeViewModel = new PlaceViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tag);

        //tag list rv연결
        RecyclerView rv = findViewById(R.id.rv_tag_search);
        rv.setAdapter(new TagAdapter());


        //검색시작
        findViewById(R.id.btn_start_search_tag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeViewModel.searchForTags(new int[]{1,2,3});
                //로딩 다이얼로그 띄우기
            }
        });


        //observe 후 검색완료시 finish
    }
}