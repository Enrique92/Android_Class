package com.example.kike.funwithbeers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kike.funwithbeers.R;
import com.example.kike.funwithbeers.models.Continent;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ContinentActivity extends AppCompatActivity {

    private static final int REQ = 1;
    private static final String CONT = "CONT";
    private ImageButton mImgViewContinents;
    private ImageButton btnLeft, btnRight, btnReturn;
    private ArrayList<Continent> mContinentsList;
    private TextView nameContinent, numberCountries;
    private int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continent);
        getSupportActionBar().hide();

        mImgViewContinents = findViewById(R.id.imgContinent);
        btnLeft = findViewById(R.id.imgLeftButtonCont);
        btnRight = findViewById(R.id.imgRightButtonCont);
        nameContinent = findViewById(R.id.nameContinent);
        numberCountries = findViewById(R.id.numberCountries);

        // Insert the values in the ArrayList of continents
        loadContinents();

        // Set the text of the TextView to show the name of the
        // continent and the number of countries that it have.
        nameContinent.setText(mContinentsList.get(cont).getName());

        Picasso.get()
                .load(mContinentsList.get(cont).getImage())
                .fit()
                .centerCrop()
                .into(mImgViewContinents);
        mImgViewContinents.setClipToOutline(true);
    }

    private void loadContinents() {
        mContinentsList = new ArrayList<>();
        mContinentsList.add(new Continent(0, "North America", "NA", R.drawable.noth_america));
        mContinentsList.add(new Continent(1, "Center America", "CA", R.drawable.center_america));
        mContinentsList.add(new Continent(2, "South America", "SA", R.drawable.south_america));
        mContinentsList.add(new Continent(3, "Europe", "EU", R.drawable.europe));
        mContinentsList.add(new Continent(4, "Africa", "AF", R.drawable.africa));
        mContinentsList.add(new Continent(5, "Asia", "AS", R.drawable.asia));
        mContinentsList.add(new Continent(6, "Oceania", "OC", R.drawable.oceania));
    }

    public void returnMenu(View view) {
        Intent mMainMenu = new Intent(getApplicationContext(), MainActivity.class);
        finish();
    }

    public void moveLeftCont(View view) {
        if (cont > 0) {
            cont--;
        }
        nameContinent.setText(mContinentsList.get(cont).getName());

        Picasso.get()
                .load(mContinentsList.get(cont).getImage())
                .fit()
                .centerCrop()
                .into(mImgViewContinents);
        mImgViewContinents.setClipToOutline(true);
    }

    public void moveRightCont(View view) {
        if (cont < 6) {
            cont++;
        } else {
            cont = 6;
        }
        nameContinent.setText(mContinentsList.get(cont).getName());

        Picasso.get()
                .load(mContinentsList.get(cont).getImage())
                .fit()
                .centerCrop()
                .into(mImgViewContinents);
        mImgViewContinents.setClipToOutline(true);
    }

    public void viewCountriesContinent(View view) {
        Intent mCountries = new Intent(getApplicationContext(), FlagActivity.class);
        mCountries.putExtra(CONT, mContinentsList.get(cont).getShortName());
        startActivityForResult(mCountries, REQ);
    }
}
