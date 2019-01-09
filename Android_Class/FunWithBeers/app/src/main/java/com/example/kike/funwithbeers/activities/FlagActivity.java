package com.example.kike.funwithbeers.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kike.funwithbeers.R;
import com.example.kike.funwithbeers.connectionSql.DataBaseAccess;
import com.example.kike.funwithbeers.connectionSql.DataBaseOpenHelper;
import com.example.kike.funwithbeers.models.Flag;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FlagActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private static final String CONT = "CONT";
    private DataBaseOpenHelper conn;
    private ImageButton mImgViewFlag;
    private ImageButton btnLeft, btnRight;
    private ArrayList<Flag> mFlags;
    private TextView mNameCountry, mNameCont;
    private int cont = 0;
    private String mName, mShortName, mRegion, mRegionShortName, mRegionContPress, url;
    private int mId;
    private SQLiteDatabase db;
    //private Cursor mCursor;
    private ArrayList<Flag> mUrlFlags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);
        getSupportActionBar().hide();

        mImgViewFlag = findViewById(R.id.imgFlag);
        btnLeft = findViewById(R.id.imgLeftButton);
        btnRight = findViewById(R.id.imgRightButton);
        mNameCountry = findViewById(R.id.nameCountry);
        mNameCont = findViewById(R.id.nameCont);

        // Load the database with the information
        getDataBaseInfo();
    }

    public void getDataBaseInfo() {
        DataBaseAccess sql = DataBaseAccess.getInstance(getApplicationContext());
        sql.openDataBaseConnection();
        mRegionContPress = getIntent().getStringExtra("CONT");
        String params = mRegionContPress;
        Log.i(CONT, "Continent Press: " + mRegionContPress);

        mUrlFlags = sql.getInfoFlag(params);

//        for (int i = 0; i < mUrlFlags.size(); i++) {
//            Log.i(CONT, "URL Flag: " + mUrlFlags.get(i));
//        }

        //try {
        //url = sql.getInfoFlag(params);
//            mCursor = db.query(Utilities.TABLE_FLAG, fields, Utilities.FIELD_REGION_SHORT_NAME + "=?", params, null, null, null);
//            mCursor.moveToFirst();
//
//            mId = mCursor.getInt(0);
//            mName = mCursor.getString(1);
//            mRegion = mCursor.getString(2);
//            mRegionShortName = mCursor.getString(3);
//            mShortName = mCursor.getString(4);
//            url = mCursor.getString(5);

        mNameCountry.setText(mUrlFlags.get(cont).getName());
        mNameCont.setText(mUrlFlags.get(cont).getRegion());
        //mCursor.close();

        // Add a new Flag
        //mFlags.add(new Flag(mId, mName, mShortName, mRegion, mRegionShortName));

        Picasso.get()
                .load(mUrlFlags.get(cont).getFlag())
                .fit()
                .centerCrop()
                .into(mImgViewFlag);
        mImgViewFlag.setClipToOutline(true);
    }


    public void returnMenu(View view) {
        Intent mContinentMenu = new Intent(getApplicationContext(), ContinentActivity.class);
        finish();
    }

    public void moveLeft(View view) {
        if (cont != 0) {
            cont--;
        }

//        while (mRegion != mRegionContPress) {
//            mShortName = mFlags.get(cont).getShortName();
//            url = mUrlFlags.get(cont);
//            cont++;
//            break;
//        }

//        if (mRegionCountry == mRegionContPress) {
//            mShortName = mFlags.get(cont).getRegion();
//            url = "http://www.geognos.com/api/en/countries/flag/" + mShortName + ".png";
//        } else {
//            cont++;
//            mShortName = mFlags.get(cont).getRegion();
//            url = "http://www.geognos.com/api/en/countries/flag/" + mShortName + ".png";
//        }

        url = mUrlFlags.get(cont).getFlag();
        mNameCountry.setText(mUrlFlags.get(cont).getName());
        mNameCont.setText(mUrlFlags.get(cont).getRegion());

        Picasso.get()
                .load(url)
                .fit()
                .centerCrop()
                .into(mImgViewFlag);
        mImgViewFlag.setClipToOutline(true);
    }


    public void moveRight(View view) {
        if (cont >= 0 && cont < mUrlFlags.size() - 1) {
            cont++;
        }

//        if (mRegion == mRegionContPress) {
//            mShortName = mFlags.get(cont).getShortName();
//            url = mUrlFlags.get(cont);
//        } else {
//            cont++;
//            mShortName = mFlags.get(cont).getShortName();
//            url = mUrlFlags.get(cont);
//        }

        url = mUrlFlags.get(cont).getFlag();
        mNameCountry.setText(mUrlFlags.get(cont).getName());
        mNameCont.setText(mUrlFlags.get(cont).getRegion());

        Picasso.get()
                .load(url)
                .fit()
                .centerCrop()
                .into(mImgViewFlag);
        mImgViewFlag.setClipToOutline(true);
    }
}
