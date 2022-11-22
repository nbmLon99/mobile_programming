package com.nbmlon.a2022mobileprogrammingteamproject.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;

public class PlaceInfoActivity extends AppCompatActivity {
    PlaceDTO mDstPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info);

        Intent intent = getIntent();
        mDstPlace = intent.getBundleExtra("placeInfo").getParcelable("placeDTO");



    }


}