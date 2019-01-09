package com.example.kike.funwithbeers.connectionSql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.kike.funwithbeers.models.Flag;

import java.util.ArrayList;

public class DataBaseAccess {
    private SQLiteOpenHelper mOpenHelper;
    private SQLiteDatabase mDatabase;
    private static DataBaseAccess mInstance;
    private Cursor mCursor = null;
    private static final String CONT = "CONT";

    private DataBaseAccess(Context mContext) {
        this.mOpenHelper = new DataBaseOpenHelper(mContext);
    }

    // Get the instance of the DataBase
    public static DataBaseAccess getInstance(Context mContext) {
        if (mInstance == null) {
            mInstance = new DataBaseAccess(mContext);
        }

        return mInstance;
    }

    // Open the connection to the DB
    public void openDataBaseConnection() {
        this.mDatabase = mOpenHelper.getWritableDatabase();
    }

    // Close the connection
    public void closeDataBaseConnection() {
        if (mDatabase == null) {
            this.mDatabase.close();
        }
    }

    public ArrayList<Flag> getInfoFlag(String regionShortName) {
        String[] fields = {Utilities.FIELD_ID, Utilities.FIELD_NAME,
                Utilities.FIELD_REGION, Utilities.FIELD_REGION_SHORT_NAME,
                Utilities.FIELD_SHORTNAME, Utilities.FIELD_FLAG};
        ArrayList<String> dataUrl = new ArrayList<>();
        ArrayList<Flag> dataUrl2 = new ArrayList<>();

        //mCursor = mDatabase.query(Utilities.TABLE_FLAG, fields, Utilities.FIELD_REGION_SHORT_NAME + "=?", regionShortName, null, null, null);
        mCursor = mDatabase.rawQuery("select * from " + Utilities.TABLE_FLAG + " where "
                + Utilities.FIELD_REGION_SHORT_NAME + " = '" + regionShortName + "' order by " + Utilities.FIELD_NAME + " ASC", new String[]{});

        while (mCursor.moveToNext()) {
            int mId = mCursor.getInt(0);
            String mName = mCursor.getString(1);
            String mRegion = mCursor.getString(2);
            String mRegionShortName = mCursor.getString(3);
            String mShortName = mCursor.getString(4);
            String url = mCursor.getString(5);
//            Log.i(CONT, "URL Flag: " + url);
//            Log.i(CONT, "Number of Flags: " + mCursor.getCount());
//            for (int i = 0; i < mCursor.getCount(); i++) {
//                dataUrl.add(url);
//            }
//            Log.i(CONT, "Array URL Flag: " + dataUrl);
            dataUrl2.add(new Flag(mId, mName, mShortName, mRegion, mRegionShortName, url));
        }

        return dataUrl2;
        //return mBuffer.toString();
    }
}
