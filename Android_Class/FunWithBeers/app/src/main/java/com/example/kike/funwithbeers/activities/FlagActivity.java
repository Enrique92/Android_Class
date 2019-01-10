package com.example.kike.funwithbeers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kike.funwithbeers.R;
import com.example.kike.funwithbeers.connectionSql.DataBaseAccess;
import com.example.kike.funwithbeers.models.Flag;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FlagActivity extends AppCompatActivity {
    private ImageButton mImgViewFlag;
    private ImageButton btnLeft, btnRight;
    private TextView mNameCountry, mNameCont;
    private int cont = 0;
    private String mRegionContPress, url;
    private ArrayList<Flag> mUrlFlags;
    private static final int REQ = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);

        // Hide de action bar
        getSupportActionBar().hide();

        // Initialized the params of the view
        mImgViewFlag = findViewById(R.id.imgFlag);
        btnLeft = findViewById(R.id.imgLeftButton);
        btnRight = findViewById(R.id.imgRightButton);
        mNameCountry = findViewById(R.id.nameCountry);
        mNameCont = findViewById(R.id.nameCont);

        // Load the database with the information
        getDataBaseInfo();
    }

    /**
     * This method get the data from the DB and then print in the screen
     */
    public void getDataBaseInfo() {
        // Start the connection
        DataBaseAccess sql = DataBaseAccess.getInstance(getApplicationContext());
        // Open the connection
        sql.openDataBaseConnection();
        // Get the name of the continent and after that we get the countries of that continent
        mRegionContPress = getIntent().getStringExtra("CONT");
        String params = mRegionContPress;

        // Get the name, continent and flags of the continent from the select
        mUrlFlags = sql.getInfoFlag(params);
        mNameCountry.setText(mUrlFlags.get(cont).getName());
        mNameCont.setText(mUrlFlags.get(cont).getRegion());

        // Print the flag of each country
        Picasso.get()
                .load(mUrlFlags.get(cont).getFlag())
                .fit()
                .centerCrop()
                .into(mImgViewFlag);
        mImgViewFlag.setClipToOutline(true);
    }

    /**
     * This method return to the Continent Activity
     *
     * @param view
     */
    public void returnMenu(View view) {
        Intent mContinentMenu = new Intent(getApplicationContext(), ContinentActivity.class);
        finish();
    }

    /**
     * This method move the cursor to the next flag of the left
     *
     * @param view
     */
    public void moveLeft(View view) {
        // Move between the different flags
        if (cont != 0) {
            cont--;
        }

        // Get the name, continent and flags of the continent from the select
        url = mUrlFlags.get(cont).getFlag();
        mNameCountry.setText(mUrlFlags.get(cont).getName());
        mNameCont.setText(mUrlFlags.get(cont).getRegion());

        // Print the flag of each country
        Picasso.get()
                .load(url)
                .fit()
                .centerCrop()
                .into(mImgViewFlag);
        mImgViewFlag.setClipToOutline(true);
    }

    /**
     * This method move the cursor to the next flag of the right
     *
     * @param view
     */
    public void moveRight(View view) {
        // Move between the different flags
        if (cont >= 0 && cont < mUrlFlags.size() - 1) {
            cont++;
        }

        // Get the name, continent and flags of the continent from the select
        url = mUrlFlags.get(cont).getFlag();
        mNameCountry.setText(mUrlFlags.get(cont).getName());
        mNameCont.setText(mUrlFlags.get(cont).getRegion());

        // Print the flag of each country
        Picasso.get()
                .load(url)
                .fit()
                .centerCrop()
                .into(mImgViewFlag);
        mImgViewFlag.setClipToOutline(true);
    }

    public void showBeers(View view) {
        Intent mFlags = new Intent(getApplicationContext(), BeerActivity.class);
        startActivityForResult(mFlags, REQ);
    }
}
