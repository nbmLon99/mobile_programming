package com.nbmlon.a2022mobileprogrammingteamproject.placInfoActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;

import java.util.List;


//구현 미완
public class PlaceInfoTagAdapter extends RecyclerView.Adapter<PlaceInfoTagAdapter.TagViewHolder> {

    private List<TagDTO> items;

    public PlaceInfoTagAdapter(List<TagDTO> items){
        this.items = items;
    }

    class TagViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTag;
        public TagViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTag = itemView.findViewById(R.id.tv_tag_forPlace);
        }

        public void bind(int position){
            tvTag.setText(items.get(position).name);
        }
    }

    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag_placeinfo, parent, false);
        return new TagViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TagViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
