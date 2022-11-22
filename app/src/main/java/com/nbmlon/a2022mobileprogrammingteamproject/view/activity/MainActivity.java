package com.nbmlon.a2022mobileprogrammingteamproject.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;
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

        dateGetTest();




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


    private void dataUploadingTest(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Location")
                .add(new PlaceDTO("","","","","","","","",true,true,true,true,true));

    }

    private void dateGetTest(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Location")
                .document("OnIJRzne7HsI7m4NGjPW")
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Log.d("PlacDTO",documentSnapshot.toObject(PlaceDTO.class).getCity());
                    }
                });



    }
}