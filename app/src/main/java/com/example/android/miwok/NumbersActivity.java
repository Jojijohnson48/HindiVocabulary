package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_numbers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Numbers");

        final ArrayList<Word> words=new ArrayList<>();
        //words.add("one");
        words.add(new Word("one","Ek",R.drawable.number_one,R.raw.ek_voice_androidapp));
        words.add(new Word("two","Dho",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three","Theen",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four","Chaar",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five","Paanch",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six","Chae",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven","Saath",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight","Aath",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine","No",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten","Das",R.drawable.number_ten,R.raw.number_ten));
        WordAdapter itemsAdapter=new WordAdapter(this,words);
        ListView listview=findViewById(R.id.list);
        listview.setAdapter(itemsAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=words.get(position);
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
