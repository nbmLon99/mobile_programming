package com.nbmlon.a2022mobileprogrammingteamproject.settingTagActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;

import java.util.ArrayList;
import java.util.List;

public class SettingTagAdapter extends RecyclerView.Adapter<SettingTagAdapter.TagViewHolder> {
    private List<TagDTO> items;
    private TagRemovedCallback rc;

    public SettingTagAdapter(List<TagDTO> items, TagRemovedCallback tagRemovedCallback){
        this.items = items;
        this.rc = tagRemovedCallback;
    }


    class TagViewHolder extends RecyclerView.ViewHolder{
        TextView tv_tag;
        Button btn_delete_tag;

        public TagViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tag = itemView.findViewById(R.id.tv_tag);
            btn_delete_tag = itemView.findViewById(R.id.btn_delete_tag);
            btn_delete_tag.setVisibility(View.VISIBLE);
        }


        private void bind(int position){
            tv_tag.setText(items.get(position).name);
            btn_delete_tag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog dialog = new AlertDialog.Builder(itemView.getContext())
                            .setTitle("정말로 삭제하시겠습니까?")
                            .setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    TagDTO tagRemove = items.get(position);
                                    //items.remove(position);
                                    //SettingTagAdapter.this.notifyItemRemoved(position);

                                    //room에서 삭제 구현필요
                                    rc.TagRemoved(tagRemove);
                                }
                            })
                            .setPositiveButton("취소", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setCancelable(false)
                            .create();
                    dialog.show();
                }
            });
        }
    }

    @NonNull
    @Override
    public SettingTagAdapter.TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag_list, parent, false);
        return new SettingTagAdapter.TagViewHolder (itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingTagAdapter.TagViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void TagAdded(TagDTO tag){
        items.add(tag);
        notifyItemInserted(items.size()-1);
    }


    public interface TagRemovedCallback{
        void TagRemoved(TagDTO tag);
    }
}
