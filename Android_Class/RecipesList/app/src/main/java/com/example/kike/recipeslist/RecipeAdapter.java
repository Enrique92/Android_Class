package com.example.kike.recipeslist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private ArrayList<Recipe> mRecipes;
    static final String TAG_DESC = "DESC";
    static final String TAG_IMG = "IMG";

    public RecipeAdapter(ArrayList<Recipe> mRecipes) {
        this.mRecipes = mRecipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new RecipeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder mRecipeViewHolder, int position) {
        Recipe mRecipe = mRecipes.get(position);
        mRecipeViewHolder.tvTitle.setText(mRecipe.getNameRecipie());
        mRecipeViewHolder.tvText.setText(mRecipe.getDescriptionRecipe());
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        private TextView tvTitle;
        private TextView tvText;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTitle = itemView.findViewById(R.id.titleRecipe);
            tvText = itemView.findViewById(R.id.textRecipe);
        }

        @Override
        public void onClick(View v) {
            Context mContext = v.getContext();
            int position = getAdapterPosition();
            Recipe mRecipe = mRecipes.get(position);

            Intent mIntent = new Intent(v.getContext(), DetailsActivity.class);
            mIntent.putExtra(TAG_DESC, mRecipe.getTextRecipe());
            mIntent.putExtra(TAG_IMG, mRecipe.getPhoto());
            mContext.startActivity(mIntent);
        }
    }
}
