package com.nbmlon.a2022mobileprogrammingteamproject.placInfoActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.placInfoActivity.dialog.SetTagDialog;

public class PlaceInfoActivity extends AppCompatActivity {
    PlaceDTO mDstPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info);

        Intent intent = getIntent();
        mDstPlace = intent.getBundleExtra("placeInfo").getParcelable("placeDTO");

        ((TextView)findViewById(R.id.detail_name)).setText(mDstPlace.name);
        ((TextView)findViewById(R.id.detail_address)).setText(mDstPlace.address);
        ((TextView)findViewById(R.id.detail_city)).setText(mDstPlace.city);
        ((TextView)findViewById(R.id.detail_phone)).setText(mDstPlace.phone);
        ((TextView)findViewById(R.id.detail_url)).setText(mDstPlace.url);

        ((TextView)findViewById(R.id.detail_condition)).setText(" boolean에 맞게 표시할 영역입니다. ");


        findViewById(R.id.detail_btn_setTag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetTagDialog dialog = new SetTagDialog(getBaseContext());
                dialog.show();
            }
        });
    }



}