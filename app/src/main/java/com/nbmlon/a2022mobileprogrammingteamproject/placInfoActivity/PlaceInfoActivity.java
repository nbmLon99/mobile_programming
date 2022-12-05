package com.nbmlon.a2022mobileprogrammingteamproject.placInfoActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.placInfoActivity.dialog.SetTagDialog;
import com.nbmlon.a2022mobileprogrammingteamproject.viewmodel.TagViewModel;

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
        mDstPlace = intent.getBundleExtra("placeInfo").getParcelable("placeDTO");


        tagRv = findViewById(R.id.detail_tagRV);
        //TagAdapter 설정
        tagViewModel.StartFindTagForPlace(mDstPlace);
        tagViewModel.GetTagForPlace().observe(this, new Observer<List<TagDTO>>() {
            @Override
            public void onChanged(List<TagDTO> tagDTOS) {
                mAdapter = new PlaceInfoTagAdapter(tagDTOS);
                tagRv.setAdapter(mAdapter);
            }
        });

        ((TextView)findViewById(R.id.detail_name)).setText(mDstPlace.name);
        ((TextView)findViewById(R.id.detail_address)).setText(mDstPlace.address);
        ((TextView)findViewById(R.id.detail_city)).setText(mDstPlace.city);
        ((TextView)findViewById(R.id.detail_phone)).setText(mDstPlace.phone);
        ((TextView)findViewById(R.id.detail_url)).setText(mDstPlace.url);

        ((TextView)findViewById(R.id.detail_condition)).setText(" boolean에 맞게 표시할 영역입니다. ");


        findViewById(R.id.detail_btn_setTag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetTagDialog dialog = new SetTagDialog(getBaseContext(), Collections.EMPTY_LIST, mDstPlace, new SetTagDialog.PlaceTaggedDoneCallback() {
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