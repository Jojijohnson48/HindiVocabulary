package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Activity context, ArrayList<Word> words){
        super(context,0,words);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        final Word currentWord=getItem(position);
        TextView hindiWord=listItemView.findViewById(R.id.hindi_text_view);
        hindiWord.setText(currentWord.gethindiTranslation());

        TextView defaultWord=listItemView.findViewById(R.id.default_text_view);
        defaultWord.setText(currentWord.getdefaultTranslation());

        ImageView img=listItemView.findViewById(R.id.img);
        if(currentWord.there_is_image()) {
            img.setImageResource(currentWord.getImage());
        }
        else{
            img.setVisibility(View.GONE);
        }


        return listItemView;
    }

}

