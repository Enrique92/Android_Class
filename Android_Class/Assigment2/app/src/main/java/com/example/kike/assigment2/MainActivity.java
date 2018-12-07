package com.example.kike.assigment2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Scanner mScanner = new Scanner(getResources().openRawResource(R.raw.catch_me_if_you_can));
        Spinner mSpinner = findViewById(R.id.movies_spinner);
        ImageView mImageView = findViewById(R.id.imageViewMovies);
        TextView mTextView = findViewById(R.id.textMovie);

        // Get the id of the Image
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.catch_me_if_you_can);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }});
        int id = getResources().getIdentifier("catch_me_if_you_can", "raw", getPackageName());
        String result = "";
        while (mScanner.hasNext()) {
            result += mScanner.nextLine();
        }
        mScanner.close();

        // Print the result on your TextView
    }
}
