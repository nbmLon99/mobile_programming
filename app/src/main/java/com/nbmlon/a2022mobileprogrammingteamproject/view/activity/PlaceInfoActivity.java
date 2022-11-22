package com.nbmlon.a2022mobileprogrammingteamproject.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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

        //mDstPlace에 맞게 text binding
        ((TextView)findViewById(R.id.detail_name)).setText(mDstPlace.getName());
        ((TextView)findViewById(R.id.detail_address)).setText(mDstPlace.getAddress());
        ((TextView)findViewById(R.id.detail_city)).setText(mDstPlace.getCity());
        ((TextView)findViewById(R.id.detail_phone)).setText(mDstPlace.getPhone());
        ((TextView)findViewById(R.id.detail_url)).setText(mDstPlace.getUrl());

        ((TextView)findViewById(R.id.detail_condition)).setText(" boolean에 맞게 표시할 영역입니다. ");

    }



}