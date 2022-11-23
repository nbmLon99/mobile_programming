package com.nbmlon.a2022mobileprogrammingteamproject.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.viewmodel.PlaceViewModel;

public class SearchActivity_Condition extends AppCompatActivity {
    static final int CHECKED_TRUE = 200;
    static final int CHECKED_FALSE = 201;
    static final int CHECKED_NONE = 202;

    RadioGroup parkingGroup, storageGroup, infantGroup, wheelGroup, pointRoadGroup;
    PlaceViewModel placeViewModel = new ViewModelProvider(this).get(PlaceViewModel.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_condition);

        //스피너 설정
        Spinner spinner =  findViewById(R.id.area_spinner);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_item, R.layout.item_spinner_area);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(0);

        parkingGroup = findViewById(R.id.parking_group);
        storageGroup = findViewById(R.id.storage_group);
        infantGroup = findViewById(R.id.infantHolder_group);
        wheelGroup = findViewById(R.id.wheelChair_group);
        pointRoadGroup = findViewById(R.id.pointRoad_group);

        //검색 시작
        findViewById(R.id.btn_start_search_condition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeViewModel.searchForCondition(
                        spinner.getSelectedItem().toString(),
                        getStatus(parkingGroup),
                        getStatus(storageGroup),
                        getStatus(infantGroup),
                        getStatus(wheelGroup),
                        getStatus(pointRoadGroup)
                );
                //로딩뷰 띄우기
            }
        });


        //observe 한다음 검색 다 되면 로딩뷰 지우고 finish
    }


    private int getStatus(RadioGroup rg){
        switch (rg.getCheckedRadioButtonId()){
            case R.id.parking_yes :
            case R.id.storage_yes :
            case R.id.infantHolder_yes:
            case R.id.wheelChair_yes:
            case R.id.pointRoad_yes:
                return CHECKED_TRUE;

            case R.id.parking_no :
            case R.id.storage_no :
            case R.id.infantHolder_no :
            case R.id.wheelChair_no :
            case R.id.pointRoad_no :
                return CHECKED_FALSE;

            default:
                return CHECKED_NONE;
        }
    }
}