package com.nbmlon.a2022mobileprogrammingteamproject.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.view.adapter.TagAdapter;

import java.util.ArrayList;
import java.util.Set;

public class SetTagDialog extends Dialog {
    RecyclerView rv;

    public SetTagDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.layout_dialog_tag);
        setCancelable(false);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rv = ((RecyclerView)findViewById(R.id.rv_tag_dialog));
        rv.setAdapter(new TagAdapter(new ArrayList<>()));


        // Room에 올리고(해당 태그에 장소 id 저장하는 방식) Dialog Dismiss
        findViewById(R.id.btn_set_tag_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<String> tagIDs = ((TagAdapter) rv.getAdapter()).getCheckedTagIDs_for_search();
                SetTagDialog.this.dismiss();
            }
        });

    }
}
