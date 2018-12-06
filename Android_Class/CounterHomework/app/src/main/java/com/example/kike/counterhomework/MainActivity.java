package com.example.kike.counterhomework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private int mCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView);

        if (savedInstanceState != null) {
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            if (isVisible) {
                mTextView.setVisibility(View.VISIBLE);
                mTextView.setText(savedInstanceState.getString("reply_text"));
            }
        }
    }

    public void addCounter(View view) {
        mCounter++;
        mTextView.setText(Integer.toString(mCounter));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mTextView.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
        }
        outState.putString("reply_text", mTextView.getText().toString());
    }
}
