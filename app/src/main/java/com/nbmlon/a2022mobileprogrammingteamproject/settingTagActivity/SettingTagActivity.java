package com.nbmlon.a2022mobileprogrammingteamproject.settingTagActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.dialog.LoadingDialog;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;
import com.nbmlon.a2022mobileprogrammingteamproject.viewmodel.TagViewModel;

import java.util.ArrayList;
import java.util.List;

/** Tag 추가/삭제 Activity **/
public class SettingTagActivity extends AppCompatActivity implements SettingTagAdapter.TagRemovedCallback {
    private TagViewModel tagViewModel;
    private SettingTagAdapter mAdapter;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_setting);
        tagViewModel = new ViewModelProvider(this).get(TagViewModel.class);
        RecyclerView rv = findViewById(R.id.rv_tag_setting);
        tagViewModel.getAllTags().observe(this, new Observer<List<TagDTO>>() {
            @Override
            public void onChanged(List<TagDTO> tagDTOS) {
                if(loadingDialog!=null){
                    loadingDialog.dismiss();
                    loadingDialog = null;
                }
                mAdapter = new SettingTagAdapter(tagDTOS, SettingTagActivity.this);
                rv.setAdapter(mAdapter);
            }
        });

        findViewById(R.id.btn_add_tag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View dialogView = LayoutInflater.from(SettingTagActivity.this).inflate(R.layout.dialog_tag_add,null, false);

                AlertDialog dialog = new AlertDialog.Builder(SettingTagActivity.this)
                        .setView(dialogView)
                        .setTitle("태그 추가")
                        .setPositiveButton("추가", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(((EditText)dialogView.findViewById(R.id.add_tag_et)).getText().toString().isEmpty())
                                    Toast.makeText(SettingTagActivity.this, "추가할 태그 이름을 설정해주세요",Toast.LENGTH_SHORT).show();
                                else{
                                    String input = ((EditText)dialogView.findViewById(R.id.add_tag_et)).getText().toString();
                                    TagDTO newTagDTO = new TagDTO(input);
                                    tagViewModel.insert(newTagDTO);
                                    dialog.dismiss();
                                }
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setCancelable(true)
                        .create();

                dialog.show();
            }
        });
    }

    @Override
    public void TagRemoved(TagDTO tag) {
        loadingDialog = new LoadingDialog(this, "삭제중");
        loadingDialog.show();
        tagViewModel.delete(tag);

    }


}