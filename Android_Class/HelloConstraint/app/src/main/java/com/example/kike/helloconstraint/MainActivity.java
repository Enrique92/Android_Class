package com.example.kike.helloconstraint;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    private TextView mShowCount;
    private Button buttonCount;
    private Button buttonZero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
        buttonCount = (Button) findViewById(R.id.button_count);
        buttonZero = (Button) findViewById(R.id.button_zero);
    }

    public void showToast(View view) {
        Toast.makeText(this, "This is a Toast", Toast.LENGTH_LONG).show();
    }

    public void countUp(View view) {
        count++;
        if (mShowCount != null) {
            mShowCount.setText(Integer.toString(count));
            if (count != 0) {
                view.setBackgroundColor(Color.parseColor("#2DCF04"));
                view.setBackgroundColor(Color.parseColor("#D81B60"));
            }
        }
    }

    public void zeroButton(View view) {
        // Set the counter to 0.
        count = 0;
        mShowCount.setText(Integer.toString(count));
        view.setBackgroundColor(Color.parseColor("#A2A2A2"));
        view.setBackgroundColor(Color.parseColor("#0422fc"));
    }
}
