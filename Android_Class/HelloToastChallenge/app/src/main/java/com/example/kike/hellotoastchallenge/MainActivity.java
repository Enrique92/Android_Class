package com.example.kike.hellotoastchallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
    }

    public void showToast(View view) {
        Toast.makeText(this, "This is a Toast", Toast.LENGTH_LONG).show();
    }

    public void countUp(View view) {
        count++;
        if (mShowCount != null) {
            mShowCount.setText(Integer.toString(count));
        }
    }
}

