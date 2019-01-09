package com.example.kike.funwithbeers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flag {
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("subregion")
    @Expose
    private String subRegion;
    private String shortName;
    @SerializedName("flag")
    @Expose
    private String flag;

    public Flag(int mId, String mName, String mShortName, String mRegion, String mSubRegion, String mFlag) {
        id = mId;
        name = mName;
        shortName = mShortName;
        region = mRegion;
        subRegion = mSubRegion;
        flag = mFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int mId) {
        id = mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String mName) {
        name = mName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String mRegion) {
        region = mRegion;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        flag = flag;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String mShortName) {
        shortName = mShortName;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String mSubRegion) {
        subRegion = mSubRegion;
    }
}
