package com.nbmlon.a2022mobileprogrammingteamproject.placInfoActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nbmlon.a2022mobileprogrammingteamproject.R;
import com.nbmlon.a2022mobileprogrammingteamproject.model.TagDTO;

import java.util.List;

public class PlaceInfoTagAdapter extends RecyclerView.Adapter<PlaceInfoTagAdapter.TagViewHolder> {

    private List<TagDTO> items;

    public PlaceInfoTagAdapter(List<TagDTO> items){
        this.items = items;
    }

    class TagViewHolder extends RecyclerView.ViewHolder {
        public TagViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(int position){

        }
    }

    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag_list, parent, true);

        return null;
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
