package com.example.dictionaryapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.R;
import com.example.dictionaryapp.models.Meaning;

import java.util.List;

public class MeaningsAdapter extends RecyclerView.Adapter<MeaningsAdapter.viewHolder> {
    Context context;
    List<Meaning> meanings;

    public MeaningsAdapter(Context context, List<Meaning> meanings) {
        this.context = context;
        this.meanings = meanings;
    }

    @NonNull
    @Override
    public MeaningsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.meaning_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MeaningsAdapter.viewHolder holder, int position) {
        holder.tv_pos.setText(String.format("Part of Speech : %s", meanings.get(position).getPartOfSpeech()));
        DefinitionsAdapter adapter = new DefinitionsAdapter(context, meanings.get(position).getDefinitions());
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public int getItemCount() {
        return this.meanings.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tv_pos;
        RecyclerView recyclerView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tv_pos = itemView.findViewById(R.id.meaning_layout_text_pos);
            recyclerView = itemView.findViewById(R.id.meaning_layout_rv);
        }
    }
}
