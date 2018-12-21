package com.example.kike.batterydrawable;

import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private LevelListDrawable mLevelListDrawable;
    private ImageView mImageView;
    private ImageButton mIncrease, mDescrease;
    private int cont = 0;
    private static final String TAG = "DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.battery);
        mIncrease = findViewById(R.id.increase);
        mDescrease = findViewById(R.id.decrease);
    }

    public void decreaseBattery(View view) {
        mImageView.setBackgroundResource(R.drawable.battery_animation_decrease);
        if (cont == 1) {
            ((LevelListDrawable) mImageView.getBackground()).setLevel(1);
        } else if (cont == 2) {
            ((LevelListDrawable) mImageView.getBackground()).setLevel(1);
        } else if (cont == 3) {
            ((LevelListDrawable) mImageView.getBackground()).setLevel(1);
        } else if (cont == 4) {
            ((LevelListDrawable) mImageView.getBackground()).setLevel(1);
        } else if (cont == 5) {
            ((LevelListDrawable) mImageView.getBackground()).setLevel(1);
        } else if (cont == 6) {
            ((LevelListDrawable) mImageView.getBackground()).setLevel(1);
        }
        if (cont > 0) {
            cont--;
        }

        Log.i(TAG, "cont - " + cont);
    }

    public void increaseBattery(View view) {
        mImageView.setBackgroundResource(R.drawable.battery_animation_increase);

        if (cont == 1) {
            ((LevelListDrawable) mImageView.getBackground()).setLevel(1);
        } else if (cont == 2) {
            ((LevelListDrawable) mImageView.getBackground()).setLevel(2);
        } else if (cont == 3) {
            ((LevelListDrawable) mImageView.getBackground()).setLevel(3);
        } else if (cont == 4) {
            ((LevelListDrawable) mImageView.getBackground()).setLevel(4);
        } else if (cont == 5) {
            ((LevelListDrawable) mImageView.getBackground()).setLevel(5);
        } else if (cont == 6) {
            ((LevelListDrawable) mImageView.getBackground()).setLevel(6);
        }
        if (cont < 6) {
            cont++;
        }
        Log.i(TAG, "cont -> " + cont);
//        if (cont < 5 && cont >= 0) {
//            ((LevelListDrawable) mImageView.getBackground()).setLevel(cont);
//            cont++;
//        }
    }
}
