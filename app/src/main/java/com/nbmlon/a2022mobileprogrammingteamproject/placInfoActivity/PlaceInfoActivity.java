package com.nbmlon.a2022mobileprogrammingteamproject.placInfoActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.dialog.LoadingDialog;
import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.placInfoActivity.dialog.SetTagDialog;
import com.nbmlon.a2022mobileprogrammingteamproject.viewmodel.TagViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlaceInfoActivity extends AppCompatActivity {
    private TagViewModel tagViewModel;
    private PlaceDTO mDstPlace;
    private PlaceInfoTagAdapter mAdapter;
    private RecyclerView tagRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info);
        tagViewModel = new ViewModelProvider(this).get(TagViewModel.class);

        //dstPlaceDTO 가져오기
        Intent intent = getIntent();
        mDstPlace = (PlaceDTO) intent.getExtras().getSerializable("placeDTO");

        tagRv = findViewById(R.id.detail_tagRV);
        tagViewModel.StartFindTagForPlace(mDstPlace);
        LoadingDialog loadingDialog = new LoadingDialog(PlaceInfoActivity.this,"로딩중");
        loadingDialog.show();
        tagViewModel.GetTagForPlace().observe(PlaceInfoActivity.this, new Observer<List<TagDTO>>() {
            @Override
            public void onChanged(List<TagDTO> tagDTOS) {
                mAdapter = new PlaceInfoTagAdapter(tagDTOS);
                tagRv.setAdapter(mAdapter);
                loadingDialog.dismiss();
            }
        });

        ((TextView)findViewById(R.id.detail_name)).setText(mDstPlace.name);
        ((TextView)findViewById(R.id.detail_address)).setText(mDstPlace.address);
        ((TextView)findViewById(R.id.detail_city)).setText(mDstPlace.city);
        ((TextView)findViewById(R.id.detail_phone)).setText(mDstPlace.phone);
        TextView tv_url =((TextView)findViewById(R.id.detail_url));
        if(mDstPlace.url!=null){
            SpannableString url = new SpannableString(mDstPlace.url);
            url.setSpan(new UnderlineSpan(), 0, url.length(), 0);
            tv_url.setText(url);
        }
        tv_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) PlaceInfoActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText(mDstPlace.name, mDstPlace.url);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(PlaceInfoActivity.this, "url이 복사되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });


        String status = "";
        if(mDstPlace.parking.equals("Y") ) {status += "주차가능  ";}
        if(mDstPlace.storage.equals("Y")) {status += "물품보관함존재  ";}
        if(mDstPlace.infantHolder.equals("Y")) {status += "유아거치대존재  ";}
        if(mDstPlace.pointRoad.equals("Y")) {status += "점자유도로존재  ";}
        if(mDstPlace.wheelChair.equals("Y")) {status += "휠체어이동가능  ";}


        ((TextView)findViewById(R.id.detail_condition)).setText((status.isEmpty())? "-" : status);


        findViewById(R.id.detail_btn_setTag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetTagDialog dialog = new SetTagDialog(PlaceInfoActivity.this, tagViewModel.getAllTags().getValue(), mDstPlace, new SetTagDialog.PlaceTaggedDoneCallback() {
                    @Override
                    public void TaggedDone(boolean updated, List<TagDTO> updatedTags, List<TagDTO> modifiedPlaceTag) {
                        //태그 수정된 게 존재
                        if(updated){
                            //태그 업데이트 후 디테일 인포 수정 후 어뎁터 연결 -> 화면 표시
                            tagViewModel.update(updatedTags);
                            tagViewModel.setModifiedTagList(modifiedPlaceTag);
                        }
                    }
                });
                dialog.show();
            }
        });
    }



}