package com.nbmlon.a2022mobileprogrammingteamproject.mainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.searchActivityCondition.SearchActivity_Condition;
import com.nbmlon.a2022mobileprogrammingteamproject.myMap.MyMapView;
import com.nbmlon.a2022mobileprogrammingteamproject.utils.JsonUploading;
import com.nbmlon.a2022mobileprogrammingteamproject.utils.Utils;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    final static int CALL_SEARCH_CONDITION = 101;
    final static int CALL_SEARCH_TAG = 102;

    MyMapView mapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ((Button)findViewById(R.id.btn_side_menu)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DrawerLayout)findViewById(R.id.rootLayout)).openDrawer(findViewById(R.id.side_menu));
            }
        });

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






    //

    /** Used For uploading JSONFILE TO FIRESTORE **/
    private void UploadJsonTO_Firestore(){
        List<PlaceDTO> places =  JsonUploading.getJSONitems(get_JSON_fileString_FromFile("output.json"));
        JsonUploading.upload(places);

    }
    /** Load JSONSTRING From File - Used For Uploading **/
    private String get_JSON_fileString_FromFile(String assetFilename){
        return Utils.getJsonFromAssets(getApplicationContext(), assetFilename);
    }

}