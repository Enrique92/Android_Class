package com.example.kike.funwithbeers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FlagList {
    @SerializedName("results")
    @Expose
    private ArrayList<Flag> flagList;


    public ArrayList<Flag> getFlagList() {
        return flagList;
    }

    public void setFlagList(ArrayList<Flag> mFlagList) {
        flagList = mFlagList;
    }
}
