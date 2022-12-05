package com.nbmlon.a2022mobileprogrammingteamproject.placInfoActivity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class SetTagDialog extends Dialog {
    PlaceTaggedDoneCallback placeTaggedDoneCallback;
    RecyclerView rv;
    TagSetAdapter mAdapter;
    PlaceDTO mDstPlace;
    List<TagDTO> mTags;

    public SetTagDialog(@NonNull Context context, List<TagDTO> tags, PlaceDTO dstPlace, PlaceTaggedDoneCallback placeTaggedDoneCallback) {
        super(context);
        setContentView(R.layout.dialog_tag_set);
        setCancelable(false);
        setTitle(dstPlace.name);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        this.placeTaggedDoneCallback = placeTaggedDoneCallback;
        mTags = tags;
        mDstPlace = dstPlace;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rv = ((RecyclerView)findViewById(R.id.rv_tag_dialog));
        //태그목록 넣어야해
        mAdapter = new TagSetAdapter(mTags);
        rv.setAdapter(mAdapter);


        // Room에 올리고(해당 태그에 장소 id 저장하는 방식) Dialog Dismiss
        findViewById(R.id.btn_set_tag_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<Integer> checkedTagIDs = mAdapter.getCheckedTagIDs_for_search();
                //tag별로 장소 업데이트
                boolean updated = false;
                List<TagDTO> modifiedPlaceTag = Collections.EMPTY_LIST;
                for(TagDTO tag : mTags) {
                    if(checkedTagIDs.contains(tag.id))
                        modifiedPlaceTag.add(tag);

                    if (checkedTagIDs.contains(tag.id) && !tag.place_ids.contains(mDstPlace.id)) {
                        tag.place_ids.add(mDstPlace.id);
                        updated =true;
                    }
                    else if (!checkedTagIDs.contains(tag.id) && tag.place_ids.contains(mDstPlace.id)) {
                        tag.place_ids.remove(mDstPlace.id);
                        updated =true;
                    }
                }
                placeTaggedDoneCallback.TaggedDone(updated, mTags, modifiedPlaceTag);

                SetTagDialog.this.dismiss();
            }
        });

    }

    public interface PlaceTaggedDoneCallback{
        public void TaggedDone(boolean updated, List<TagDTO> AllTags, List<TagDTO> modifiedDstPlaceTag);
    }
}
