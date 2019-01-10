package com.example.kike.funwithbeers.models;

public class Beer {
    private int id;
    private String name;
    private String country;
    private String type;
    // % of Alcohol
    private String percofalc;
    private String description;
    private int score;
    private String linkimage;

    public Beer(int mId, String mName, String mCountry, String mType, String mPercofalc, String mDescription, int mScore, String mLinkimage) {
        id = mId;
        name = mName;
        country = mCountry;
        type = mType;
        percofalc = mPercofalc;
        description = mDescription;
        score = mScore;
        linkimage = mLinkimage;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String mCountry) {
        country = mCountry;
    }

    public String getType() {
        return type;
    }

    public void setType(String mType) {
        type = mType;
    }

    public String getPercofalc() {
        return percofalc;
    }

    public void setPercofalc(String mPercofalc) {
        percofalc = mPercofalc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String mDescription) {
        description = mDescription;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int mScore) {
        score = mScore;
    }

    public String getLinkimage() {
        return linkimage;
    }

    public void setLinkimage(String mLinkimage) {
        linkimage = mLinkimage;
    }
}
