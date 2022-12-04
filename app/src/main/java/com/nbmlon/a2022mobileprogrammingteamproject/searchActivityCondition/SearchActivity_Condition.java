package com.nbmlon.a2022mobileprogrammingteamproject.searchActivityCondition;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.dialog.LoadingDialog;
import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.viewmodel.PlaceViewModel;

import java.util.List;

public class SearchActivity_Condition extends AppCompatActivity implements LifecycleOwner {
    RadioGroup parkingGroup, storageGroup, infantGroup, wheelGroup, pointRoadGroup;
    PlaceViewModel placeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_condition);
        placeViewModel = new ViewModelProvider(this).get(PlaceViewModel.class);

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
                //로딩뷰 띄우기
                LoadingDialog loadingDialog = new LoadingDialog(SearchActivity_Condition.this);
                loadingDialog.show();
                placeViewModel.searchForCondition(
                        spinner.getSelectedItem().toString(),
                        getStatus(parkingGroup),
                        getStatus(storageGroup),
                        getStatus(infantGroup),
                        getStatus(wheelGroup),
                        getStatus(pointRoadGroup)
                );

                placeViewModel.searchResults().observe((LifecycleOwner) SearchActivity_Condition.this, new Observer<List<PlaceDTO>>() {
                    @Override
                    public void onChanged(List<PlaceDTO> placeDTOS) {
                        //observe 한다음 검색 다 되면 로딩뷰 지우고 finish
                        Log.d("result", placeViewModel.searchResults().toString());
                        loadingDialog.dismiss();
                        SearchActivity_Condition.this.finish();
                    }
                });
            }
        });


    }


    private String getStatus(RadioGroup rg){
        switch (rg.getCheckedRadioButtonId()){
            case R.id.parking_yes :
            case R.id.storage_yes :
            case R.id.infantHolder_yes:
            case R.id.wheelChair_yes:
            case R.id.pointRoad_yes:
                return "Y";

            case R.id.parking_no :
            case R.id.storage_no :
            case R.id.infantHolder_no :
            case R.id.wheelChair_no :
            case R.id.pointRoad_no :
                return "N";

            default:
                return null;
        }
    }
}