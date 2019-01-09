package com.example.kike.funwithbeers.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.kike.funwithbeers.R;

public class AboutItActivity extends AppCompatActivity {

    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_it);
        getSupportActionBar().hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // When the activity start we start the song at the background
        player = MediaPlayer.create(this, R.raw.all_go_to_the_bar);
        player.setLooping(true);
        player.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
    }

    public void returnMenu(View view) {
        Intent mMainMenu = new Intent(getApplicationContext(), MainActivity.class);
        finish();
    }
}
