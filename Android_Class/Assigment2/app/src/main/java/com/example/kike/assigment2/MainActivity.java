package com.example.kike.assigment2;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Scanner;
public class MainActivity extends AppCompatActivity {
    ImageView mImageView;
    TextView mTextView;
    Scanner mScanner;
    String result = "";
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner mSpinner = findViewById(R.id.movies_spinner);
        mScanner = new Scanner(getResources().openRawResource(R.raw.the_hangover));
        mImageView = findViewById(R.id.imageViewMovies);
        mTextView = findViewById(R.id.textMovie);
        ArrayAdapter<CharSequence> mArrayAdapter = ArrayAdapter.createFromResource(this, R.array.movies, android.R.layout.simple_spinner_item);
        mSpinner.setAdapter(mArrayAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                result = "";
                String pos = parent.getItemAtPosition(position).toString();
                int p = parent.getSelectedItemPosition();
                pos = pos.replace(" ", "_");
                pos = pos.toLowerCase();
                mImageView.setImageResource(parent.getResources().getIdentifier(pos,"drawable", getPackageName()));
                mScanner = new Scanner(getResources().openRawResource(parent.getResources().getIdentifier(pos, "raw", getPackageName())));
                //mScanner = new Scanner(getResources().openRawResource(R.raw.catch_me_if_you_can));
                Log.i("LOG-scanner", String.valueOf(mScanner));
                while (mScanner.hasNext()) {
                    result += mScanner.nextLine();
                }
                i++;
                Log.i("LOG-desc", result);
                // Print the result on your TextView
                mTextView.setText(result);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        if (i == 0) {
            while (mScanner.hasNext()) {
                result += mScanner.nextLine();
            }
        }
        // Print the result on your TextView
        mTextView.setText(result);
    }
}
