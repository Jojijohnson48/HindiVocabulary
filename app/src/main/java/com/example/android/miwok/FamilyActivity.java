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

public class FamilyActivity extends AppCompatActivity {
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_family);
        setTitle("Family");


        final ArrayList<Word> familyWords=new ArrayList<>();
        familyWords.add(new Word("Father","Pithaaji",R.drawable.family_father,R.raw.family_father));
        familyWords.add(new Word("Mother","Maathaji",R.drawable.family_mother,R.raw.family_mother));
        familyWords.add(new Word("Son","Beta",R.drawable.family_son,R.raw.family_son));
        familyWords.add(new Word("Daughter","Beti",R.drawable.family_daughter,R.raw.family_daughter));
        familyWords.add(new Word("Older Brother","Bhada Bhai",R.drawable.family_older_brother,R.raw.family_olderbrother));
        familyWords.add(new Word("Younger Brother","Chotta bhai",R.drawable.family_younger_brother,R.raw.family_youngerbrother));
        familyWords.add(new Word("Older Sister","Bhadi Behen",R.drawable.family_older_sister,R.raw.family_oldersister));
        familyWords.add(new Word("Younger Sister","Chotti Behehn",R.drawable.family_younger_sister,R.raw.family_youngersister));
        familyWords.add(new Word("Grand Mother","Daadiji",R.drawable.family_grandmother,R.raw.family_grandmother));
        familyWords.add(new Word("Grand Father","Daadaji",R.drawable.family_grandfather,R.raw.family_grandfather));

        WordAdapter itemsAdapter=new WordAdapter(this,familyWords);
        ListView listview=findViewById(R.id.familyList);
        listview.setAdapter(itemsAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=familyWords.get(position);
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
