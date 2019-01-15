package com.example.kike.funwithbeers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kike.funwithbeers.R;

public class BreweryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery);

        // Hide de action bar
        getSupportActionBar().hide();
    }
}
