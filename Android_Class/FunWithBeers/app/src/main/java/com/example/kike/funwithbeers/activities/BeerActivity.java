package com.example.kike.funwithbeers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.kike.funwithbeers.R;
import com.squareup.picasso.Picasso;

public class BeerActivity extends AppCompatActivity {
    private ImageButton mImageBeer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);

        // Hide de action bar
        getSupportActionBar().hide();

        mImageBeer = findViewById(R.id.imageBeer);

//        String url = "https://funwithbeers.000webhostapp.com/ImagesBeers/barba_roja_lager.png";
//
//        // Print the flag of each country
//        Picasso.get()
//                .load(url)
//                .fit()
//                .into(mImageBeer);
//        mImageBeer.setClipToOutline(true);
    }
}
