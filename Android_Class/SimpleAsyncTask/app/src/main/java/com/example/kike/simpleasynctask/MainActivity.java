package com.example.kike.simpleasynctask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView1);
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setMax(10);

    }

    public void startTask(View view) {
        // Put a message in the text view
        mTextView.setText(R.string.napping);
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setProgress(0);

        // Start the AsyncTask.
        new SimpleAsyncTask(mTextView, mProgressBar).execute(10);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
