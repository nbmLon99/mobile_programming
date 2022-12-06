package com.nbmlon.a2022mobileprogrammingteamproject.mainActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.placInfoActivity.PlaceInfoActivity;
import com.nbmlon.a2022mobileprogrammingteamproject.repository.TagRepositoy;
import com.nbmlon.a2022mobileprogrammingteamproject.searchActivityCondition.SearchActivity_Condition;
import com.nbmlon.a2022mobileprogrammingteamproject.myMap.MyMapView;
import com.nbmlon.a2022mobileprogrammingteamproject.searchActivityTag.SearchActivity_Tag;
import com.nbmlon.a2022mobileprogrammingteamproject.settingTagActivity.SettingTagActivity;
import com.nbmlon.a2022mobileprogrammingteamproject.utils.JsonUploading;
import com.nbmlon.a2022mobileprogrammingteamproject.utils.Utils;
import com.nbmlon.a2022mobileprogrammingteamproject.viewmodel.PlaceViewModel;
import com.nbmlon.a2022mobileprogrammingteamproject.viewmodel.TagViewModel;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    //RequestCode
    private final static int CALL_SEARCH_CONDITION = 101;
    private final static int CALL_SEARCH_TAG = 102;
    public final static int RETURN_SEARCH_CONDITION = 103;
    public final static int RETURN_SEARCH_TAG = 104;


    private PlaceViewModel placeViewModel;
    private TagViewModel tagViewModel;

    private MyMapView mapView;
    private Boolean isFabOpen = false;

    private FloatingActionButton mainFab, conditionFab, tagFab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TagRepositoy.initialize(getApplication());

        setContentView(R.layout.activity_main);
        setFloatingButtonOnClick();
        setSideMenu();

        tagViewModel = new ViewModelProvider(this).get(TagViewModel.class);
        placeViewModel = new ViewModelProvider(this).get(PlaceViewModel.class);
        PlaceInfoTest();

    }

    private void mapViewInitialize() {
        mapView = new MyMapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.mapview);
        mapViewContainer.addView(mapView);
    }

    private void setSideMenu(){
        ((Button)findViewById(R.id.btn_side_menu)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DrawerLayout)findViewById(R.id.rootLayout)).openDrawer(findViewById(R.id.side_menu));
            }
        });
        NavigationView navigationView = findViewById(R.id.side_menu);
        navigationView.findViewById(R.id.side_menu_tag_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingTagActivity.class));
            }
        });


    }

    private void setFloatingButtonOnClick(){
        mainFab = findViewById(R.id.mainFab);
        conditionFab = findViewById(R.id.btn_open_search_condition);
        tagFab = findViewById(R.id.btn_open_search_tag);

        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFAB();
            }
        });

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
                Intent intent = new Intent(MainActivity.this, SearchActivity_Tag.class);
                startActivityForResult(intent, CALL_SEARCH_TAG );
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RETURN_SEARCH_CONDITION){
            List<PlaceDTO> results = placeViewModel.getResultLiveData().getValue();
            //뷰모델에서 데이터 가져와서 지도에 띄우기


            //Toast로 개수 띄우기
            String resultSize = Integer.toString(results.size());
            Toast.makeText(MainActivity.this, "검색결과 : " + resultSize + "개",Toast.LENGTH_SHORT).show();
            //repository 초기화
            placeViewModel.resetPlaceResults();
        }
        else if (resultCode == RETURN_SEARCH_TAG){
            List<PlaceDTO> results = tagViewModel.getSearchResult().getValue();
            //뷰모델에서 데이터 가져와서 지도에 띄우기





            //Toast로 개수 띄우기
            String resultSize = Integer.toString(results.size());
            Toast.makeText(MainActivity.this, "검색결과 : " + resultSize + "개",Toast.LENGTH_SHORT).show();
            //repository 초기화
            tagViewModel.resetTagResults();
        }
    }


    private void toggleFAB(){
        if(isFabOpen){
            ObjectAnimator.ofFloat(conditionFab,"translationY",0f).start();
            ObjectAnimator.ofFloat(tagFab,"translationY",0f).start();
            mainFab.setImageResource(R.drawable.ic_baseline_add_24);
        } else{
            ObjectAnimator.ofFloat(conditionFab,"translationY",-200f).start();
            ObjectAnimator.ofFloat(tagFab,"translationY",-400f).start();
            mainFab.setImageResource(R.drawable.ic_baseline_clear_24);
        }
        isFabOpen = !isFabOpen;
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


    private void PlaceInfoTest(){
        Intent intent = new Intent(MainActivity.this, PlaceInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("placeDTO",new PlaceDTO("01yfacWyqH8crtk4hBQ0","테스트이름","테스트 도시","테스트 주소","테스트 위도","경도","폰","url",true,true,true,true,true));
        intent.putExtras(bundle);
        startActivity(intent);
    }

}