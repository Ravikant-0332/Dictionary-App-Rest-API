package com.example.dictionaryapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.R;
import com.example.dictionaryapp.models.Definition;

import java.util.List;

public class DefinitionsAdapter extends RecyclerView.Adapter<DefinitionsAdapter.viewHolder> {
    Context context;
    List<Definition> definitions;

    public DefinitionsAdapter(Context context, List<Definition> definitions) {
        this.context = context;
        this.definitions = definitions;
    }

    @NonNull
    @Override
    public DefinitionsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.definition_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionsAdapter.viewHolder holder, int position) {
        if(definitions.get(position).getDefinition().length()!=0)
            holder.tv_definition.setText(String.format("Definition: %s", definitions.get(position).getDefinition()));
        if(definitions.get(position).getExample().length()!=0)
            holder.tv_example.setText(String.format("Example: %s", definitions.get(position).getExample()));
    }

    @Override
    public int getItemCount() {
        return this.definitions.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tv_definition;
        TextView tv_example;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tv_definition = itemView.findViewById(R.id.definition_layout_text_definition);
            tv_example = itemView.findViewById(R.id.definition_layout_text_example);
        }
    }
}
