package com.nbmlon.a2022mobileprogrammingteamproject.view.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;

import java.util.ArrayList;
import java.util.Set;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagViewHolder> {
    private int recyclerID;
    private Set<String> checkedTagIDs;
    private ArrayList<TagDTO> items;

    public TagAdapter(ArrayList<TagDTO> items){
        this.items = items;
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
        }

        private TagViewHolder setInitVisibility(ViewGroup parent){
            switch (recyclerID){
                case R.id.rv_tag_search:
                case R.id.rv_tag_dialog:
                    rb_tag.setVisibility(View.VISIBLE);
                    return this;

                case R.id.rv_tag_setting:
                    btn_delete_tag.setVisibility(View.VISIBLE);
                    return this;

                default:
                    return this;

            }
        }

        private void bind(int position){
            switch (recyclerID){
                case R.id.rv_tag_search:
                case R.id.rv_tag_dialog:
                    rb_tag.setVisibility(View.VISIBLE);
                    rb_tag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            String dstID = items.get(position).id;
                            if(isChecked){
                                checkedTagIDs.add(dstID);}
                            else{
                                checkedTagIDs.remove(dstID);}
                        }
                    });
                    return;

                case R.id.rv_tag_setting:
                    btn_delete_tag.setVisibility(View.VISIBLE);
                    btn_delete_tag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog dialog = new AlertDialog.Builder(itemView.getContext())
                                    .setTitle("정말로 삭제하시겠습니까?")
                                    .setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            items.remove(position);
                                            TagAdapter.this.notifyItemRemoved(position);
                                            //room에서 삭제
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
                    return;

                default:
                    return;

            }
        }

    }

    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag_list, parent, true);
        recyclerID = parent.getId();
        return new TagViewHolder(itemView).setInitVisibility(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TagViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    public Set<String> getCheckedTagIDs_for_search() {
        if(recyclerID == R.id.rv_tag_search)
            return checkedTagIDs;
        else
            return null;
    }
}
