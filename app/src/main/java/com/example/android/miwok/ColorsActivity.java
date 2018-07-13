package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
private MediaPlayer maudioResID;
private MediaPlayer.OnCompletionListener mCompletionListener=new MediaPlayer.OnCompletionListener() {
    @Override
    public void onCompletion(MediaPlayer mp) {
        releaseMediaplayer();
    }
};
    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                maudioResID.pause();
                maudioResID.seekTo(0);
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_GAIN){
                maudioResID.start();
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaplayer();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Colors");

        final ArrayList<Word> colorList=new ArrayList<>();
        colorList.add(new Word("Red","Laal",R.drawable.color_red,R.raw.color_red));
        colorList.add(new Word("Green","Haraa",R.drawable.color_green,R.raw.color_green));
        colorList.add(new Word("Brown","Bhoora",R.drawable.color_brown,R.raw.color_brown));
        colorList.add(new Word("Grey","Dhoosar",R.drawable.color_gray,R.raw.color_grey));
        colorList.add(new Word("Black","Kaala",R.drawable.color_black,R.raw.color_black));
        colorList.add(new Word("White","Safed",R.drawable.color_white,R.raw.color_white));
        colorList.add(new Word("Light Yellow","Halka Peela",R.drawable.color_dusty_yellow,R.raw.color_lightyellow));
        colorList.add(new Word("Dark Yellow","Gehraa peela",R.drawable.color_mustard_yellow,R.raw.color_darkyellow));
        WordAdapter itemsAdapter = new WordAdapter(this,colorList);
        ListView listItem=findViewById(R.id.colorsList);
        listItem.setAdapter(itemsAdapter);

        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=colorList.get(position);
                 maudioResID=MediaPlayer.create(getApplicationContext(),word.getAudio());
                maudioResID.start();
                maudioResID.setOnCompletionListener(mCompletionListener);
            }

        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaplayer();
    }

    private void releaseMediaplayer(){
        if(maudioResID!=null){
            maudioResID.release();
            maudioResID=null;
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
