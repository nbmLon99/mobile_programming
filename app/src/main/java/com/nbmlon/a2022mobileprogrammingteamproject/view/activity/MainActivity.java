package com.nbmlon.a2022mobileprogrammingteamproject.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.view.MyMapView;

import net.daum.mf.map.api.MapView;


public class MainActivity extends AppCompatActivity {
    final static int CALL_SEARCH_CONDITION = 101;
    final static int CALL_SEARCH_TAG = 102;

    MyMapView mapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void mapViewInitialize() {
        mapView = new MyMapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.mapview);
        mapViewContainer.addView(mapView);
    }

    private void setFloatingButtonOnClick(){

        // 조건검색 액티비티 호출
        findViewById(R.id.btn_open_search_condition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity_Condition.class);
                startActivityForResult(intent, CALL_SEARCH_CONDITION );
            }
        });

        // 태그검색 액티비티 호출
        findViewById(R.id.btn_open_search_tag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity_Condition.class);
                startActivityForResult(intent, CALL_SEARCH_TAG );
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == CALL_SEARCH_CONDITION){

        }
        else if (resultCode == RESULT_OK && requestCode == CALL_SEARCH_TAG){

        }
    }
}