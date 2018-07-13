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

public class PhrasesActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_phrases);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Phrases");
        final ArrayList<Word> phrases=new ArrayList<>();
        phrases.add(new Word("Where are you going?","Tum kahan jaa rahe ho?",R.raw.phrase1));
        phrases.add(new Word("What is your name?","Tumhara naam kya hai?",R.raw.phrase2));
        phrases.add(new Word("My name is...","Mera naam hai...",R.raw.phrase3));
        phrases.add(new Word("How are you feeling?","Tum kaisa mehsoos kar rahe ho?",R.raw.phrase4));
        phrases.add(new Word("How are you?","Tum kaise ho?",R.raw.phrase5));
        phrases.add(new Word("I'm good","Mein theek hoon",R.raw.phrase6));
        phrases.add(new Word("Are you coming?","Kya tum aa rahe ho?",R.raw.phrase7));
        phrases.add(new Word("Yes,I'm coming","Haan,mein aa raha hoon",R.raw.phrase8));
        phrases.add(new Word("Let's go","Chalo chalte hain",R.raw.phrase9));
        phrases.add(new Word("Come here","Yahan aao",R.raw.phrase10));

        WordAdapter itemsAdapter=new WordAdapter(this,phrases);
        ListView listItem=findViewById(R.id.phrasesList);
        listItem.setAdapter(itemsAdapter);

        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=phrases.get(position);
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
