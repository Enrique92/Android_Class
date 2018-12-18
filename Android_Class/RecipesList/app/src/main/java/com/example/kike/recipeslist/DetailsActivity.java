package com.example.kike.recipeslist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.kike.recipeslist.RecipeAdapter.TAG_DESC;
import static com.example.kike.recipeslist.RecipeAdapter.TAG_IMG;

public class DetailsActivity extends AppCompatActivity {

    private final static String TAG = "TAG";
    private String description;
    private int image;
    private TextView tvDescription;
    private ImageView ivRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create the TextView and ImageView
        tvDescription = findViewById(R.id.descRecipe);
        ivRecipe = findViewById(R.id.imageRecipe);

        // Get the values from the Array of the MainActivity
        description = getIntent().getStringExtra(TAG_DESC);
        image = getIntent().getIntExtra(TAG_IMG, 0);
        Log.i(TAG, "IMAGE: " + image);

        // Show in the DetailsActivity
        tvDescription.setText(description);
        Log.i(TAG, "IMAGE RESOURCE: " + image);
        ivRecipe.setImageResource(image);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.i("ActionBar", "Back!");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
