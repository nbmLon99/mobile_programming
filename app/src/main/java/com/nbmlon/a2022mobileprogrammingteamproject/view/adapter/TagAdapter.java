package com.nbmlon.a2022mobileprogrammingteamproject.view.adapter;

import android.nfc.Tag;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nbmlon.a2022mobileprogrammingteamproject.R;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagViewHolder> {

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
            switch (parent.getId()){
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
    }

    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag_list, parent, true);
        return new TagViewHolder(itemView).setInitVisibility(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TagViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
