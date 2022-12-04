package com.nbmlon.a2022mobileprogrammingteamproject.placInfoActivity.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TagSetAdapter extends RecyclerView.Adapter<TagSetAdapter.TagViewHolder> {

    private Set<Integer> checkedTagIDs;
    private List<TagDTO> items;

    public TagSetAdapter(List<TagDTO> items){
        this.items = items;
    }


    class TagViewHolder extends RecyclerView.ViewHolder{
        TextView tv_tag;
        RadioButton rb_tag;

        public TagViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tag = itemView.findViewById(R.id.tv_tag);
            rb_tag = itemView.findViewById(R.id.rb_tag);
            rb_tag.setVisibility(View.VISIBLE);

        }


        private void bind(int position){
            rb_tag.setVisibility(View.VISIBLE);
            rb_tag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int dstID = items.get(position).id;
                    if (isChecked)
                        checkedTagIDs.add(dstID);
                    else
                        checkedTagIDs.remove(dstID);
                }
            });
        }

    }

    @NonNull
    @Override
    public TagSetAdapter.TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag_list, parent, true);
        return new TagSetAdapter.TagViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TagSetAdapter.TagViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    public Set<Integer> getCheckedTagIDs_for_search() {
        return checkedTagIDs;
    }
}
