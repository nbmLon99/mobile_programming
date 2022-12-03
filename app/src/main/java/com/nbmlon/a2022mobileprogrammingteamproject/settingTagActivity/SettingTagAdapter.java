package com.nbmlon.a2022mobileprogrammingteamproject.settingTagActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;

import java.util.ArrayList;

public class SettingTagAdapter extends RecyclerView.Adapter<SettingTagAdapter.TagViewHolder> {
    private ArrayList<TagDTO> items;
    private TagRemovedCallback rc;

    public SettingTagAdapter(ArrayList<TagDTO> items, TagRemovedCallback rc){
        this.items = items;
        this.rc = rc;
    }


    class TagViewHolder extends RecyclerView.ViewHolder{
        TextView tv_tag;
        RadioButton rb_tag;
        Button btn_delete_tag;

        public TagViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tag = itemView.findViewById(R.id.tv_tag);
            rb_tag = itemView.findViewById(R.id.rb_tag);
            btn_delete_tag = itemView.findViewById(R.id.btn_delete_tag);
            btn_delete_tag.setVisibility(View.VISIBLE);
        }


        private void bind(int position){
            btn_delete_tag.setVisibility(View.VISIBLE);
            btn_delete_tag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog dialog = new AlertDialog.Builder(itemView.getContext())
                            .setTitle("정말로 삭제하시겠습니까?")
                            .setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    TagDTO tagRemove = items.get(position);
                                    items.remove(position);
                                    SettingTagAdapter.this.notifyItemRemoved(position);

                                    //room에서 삭제
                                    rc.TagRemoved();
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
                }
            });
        }
    }

    @NonNull
    @Override
    public SettingTagAdapter.TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag_list, parent, true);
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
        void TagRemoved();
    }
}
