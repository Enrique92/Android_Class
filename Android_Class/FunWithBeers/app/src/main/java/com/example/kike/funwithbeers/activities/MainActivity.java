package com.example.kike.funwithbeers.activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.kike.funwithbeers.R;

public class MainActivity extends AppCompatActivity {
    private static final int REQ = 1;
    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Hide de action bar
        getSupportActionBar().hide();

        // Initialized the params of the view
        constraintLayout = findViewById(R.id.mainMenuLayout);
        constraintLayout.setBackgroundResource(R.drawable.animation_list_background);
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
    }

    /**
     * This method is load when the cursor return to this Activity
     */
    @Override
    protected void onResume() {
        super.onResume();
        // Add an animation and sound to the GIF that we add
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            player = MediaPlayer.create(this, R.raw.beer_sound);
            player.setLooping(true);
            player.start();
            animationDrawable.start();
        }
    }

    /**
     * This method is call when the activity was in Pause
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            player.stop();
            animationDrawable.stop();
        }
    }

    /**
     * Method that change to the other Activity and select the country
     *
     * @param view
     */
    public void showFlags(View view) {
        Intent mContinents = new Intent(getApplicationContext(), ContinentActivity.class);
        startActivityForResult(mContinents, REQ);
    }

    /**
     * Method that exit from the app
     *
     * @param view
     */
    public void exitApp(View view) {
        // Exit from the App
        finishAffinity();
    }

    /**
     * Method that show more about the app
     *
     * @param view
     */
    public void aboutIt(View view) {
        Intent mAboutIt = new Intent(getApplicationContext(), AboutItActivity.class);
        startActivityForResult(mAboutIt, REQ);
    }

    /**
     * Method that show the Breweries of the world
     * @param view
     */
    public void showBreweries(View view) {
        Intent mBreweries = new Intent(getApplicationContext(), BreweryActivity.class);
        startActivityForResult(mBreweries, REQ);
    }
}
