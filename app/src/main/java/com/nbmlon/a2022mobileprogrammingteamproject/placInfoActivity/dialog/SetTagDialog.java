package com.nbmlon.a2022mobileprogrammingteamproject.placInfoActivity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SetTagDialog extends Dialog {
    PlaceTaggedDoneCallback placeTaggedDoneCallback;
    RecyclerView rv;
    TagSetAdapter mAdapter;
    PlaceDTO mDstPlace;
    List<TagDTO> mAllTags;

    public SetTagDialog(@NonNull Context context, List<TagDTO> tags, PlaceDTO dstPlace, PlaceTaggedDoneCallback placeTaggedDoneCallback) {
        super(context);
        setContentView(R.layout.dialog_tag_set);
        setTitle(dstPlace.name);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ViewGroup.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);


        this.placeTaggedDoneCallback = placeTaggedDoneCallback;
        mAllTags = tags;
        mDstPlace = dstPlace;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rv = ((RecyclerView)findViewById(R.id.rv_tag_dialog));
        //태그목록 넣어야해
        mAdapter = new TagSetAdapter(mAllTags);
        rv.setAdapter(mAdapter);


        // Room에 올리고(해당 태그에 장소 id 저장하는 방식) Dialog Dismiss
        findViewById(R.id.btn_set_tag_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<Integer> checkedTagIDs = mAdapter.getCheckedTagIDs_for_search();
                //tag별로 장소 업데이트
                boolean updated = false;
                ArrayList<TagDTO> modifiedPlaceTag = new ArrayList<>();
                for(TagDTO tag : mAllTags) {
                    if(checkedTagIDs.contains(tag.id))
                        modifiedPlaceTag.add(tag);

                    if (checkedTagIDs.contains(tag.id) && !tag.place_ids.contains(mDstPlace.id)) {

                        ArrayList<String> tmp =  new ArrayList<>(tag.place_ids);
                        tmp.add(mDstPlace.id);
                        tag.place_ids = tmp;
                        updated =true;

                    }
                    else if (!checkedTagIDs.contains(tag.id) && tag.place_ids.contains(mDstPlace.id)) {
                        ArrayList<String> tmp =  new ArrayList<>(tag.place_ids);
                        tmp.remove(mDstPlace.id);
                        tag.place_ids = tmp;
                        updated =true;
                    }

                }
                placeTaggedDoneCallback.TaggedDone(updated, mAllTags, modifiedPlaceTag);

                SetTagDialog.this.dismiss();
            }
        });

    }

    public interface PlaceTaggedDoneCallback{
        public void TaggedDone(boolean updated, List<TagDTO> AllTags, List<TagDTO> modifiedDstPlaceTag);
    }
}
