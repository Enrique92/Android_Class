package com.example.kike.recipeslist;

public class Recipe {
    private String mNameRecipie;
    private String mDescriptionRecipe;
    private String mTextRecipe;
    private int mPhoto;

    public Recipe(String mNameRecipie, String mDescriptionRecipe, String mTextRecipe, int mPhoto) {
        this.mNameRecipie = mNameRecipie;
        this.mDescriptionRecipe = mDescriptionRecipe;
        this.mTextRecipe = mTextRecipe;
        this.mPhoto = mPhoto;
    }

    public String getNameRecipie() {
        return mNameRecipie;
    }

    public void setNameRecipie(String mNameRecipe) {
        this.mNameRecipie = mNameRecipe;
    }

    public String getTextRecipe() {
        return mTextRecipe;
    }

    public void setTextRecipe(String mTextRecipe) {
        this.mTextRecipe = mTextRecipe;
    }

    public String getDescriptionRecipe() {
        return mDescriptionRecipe;
    }

    public void setDescriptionRecipe(String mDescriptionRecipe) {
        this.mDescriptionRecipe = mDescriptionRecipe;
    }


    public int getPhoto() {
        return mPhoto;
    }

    public void setPhoto(int mPhoto) {
        this.mPhoto = mPhoto;
    }
}
