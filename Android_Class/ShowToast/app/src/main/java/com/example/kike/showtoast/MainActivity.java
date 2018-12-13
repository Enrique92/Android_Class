package com.example.kike.showtoast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    CheckBox ch1, ch2, ch3, ch4, ch5;
    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.showToast);
        ch1 = findViewById(R.id.chBox1);
        ch2 = findViewById(R.id.chBox2);
        ch3 = findViewById(R.id.chBox3);
        ch4 = findViewById(R.id.chBox4);
        ch5 = findViewById(R.id.chBox5);
    }

    public void showToast(View view) {
        if (ch1.isChecked()) {
            message = message + " Chocolate Syrup ";
        } else if (ch2.isChecked()) {
            message = message + " Sprinkles ";
        } else if (ch3.isChecked()) {
            message = message + " Crushed Nuts ";
        } else if (ch4.isChecked()) {
            message = message + " Cherries ";
        } else if (ch5.isChecked()) {
            message = message + " Oreo Cookies Crumbles ";
        }

        // Show the toast in the screen
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
