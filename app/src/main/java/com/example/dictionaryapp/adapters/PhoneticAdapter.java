package com.example.dictionaryapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.R;
import com.example.dictionaryapp.models.Phonetic;

import java.util.List;

public class PhoneticAdapter extends RecyclerView.Adapter<PhoneticAdapter.viewHolder> {
    Context context;
    List<Phonetic> phonetics;

    public PhoneticAdapter(Context context, List<Phonetic> phonetics) {
        this.context = context;
        this.phonetics = phonetics;
    }

    @NonNull
    @Override
    public PhoneticAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.phonetic_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneticAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(phonetics.get(position).getText().length()!=0) {
            holder.tv_text.setText(phonetics.get(position).getText());
            if(phonetics.get(position).getAudio().length()==0){
                holder.tv_img.setVisibility(View.GONE);
            }else {
                holder.tv_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MediaPlayer player = new MediaPlayer();
                        try {
                            player.setAudioAttributes(
                                    new AudioAttributes.Builder()
                                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                            .setUsage(AudioAttributes.USAGE_MEDIA)
                                            .build());
                            player.setDataSource(phonetics.get(position).getAudio());
                            player.prepare();
                            player.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Unable to play audio", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }else{
            holder.phonetic_card.setVisibility(View.GONE);
            holder.tv_text.setVisibility(View.GONE);
            holder.tv_img.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return this.phonetics.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tv_text;
        ImageView tv_img;
        CardView phonetic_card;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tv_text = itemView.findViewById(R.id.phonetic_layout_text);
            tv_img = itemView.findViewById(R.id.phonetic_layout_img);
            phonetic_card = itemView.findViewById(R.id.phonetic_card);
        }
    }
}
