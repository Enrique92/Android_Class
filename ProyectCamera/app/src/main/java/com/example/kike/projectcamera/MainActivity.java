package com.example.kike.projectcamera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private final static int REQUEST_PICTURE = 1;
    private final static String TAG = "LOG";
    private ImageView mImageView;

    /**
     * The main method that initialize the view.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.idCamera);
        mImageView = findViewById(R.id.idImage);
    }

    /**
     * Method to ask for the grants and take a photo with the camera.
     *
     * @param v
     */
    public void takePhoto(View v) {
        // The is in execution
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Explain why we need the permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                // Call the method to take a picture
            } else {
                // Ask for the permissions
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_PICTURE);
            }
        }

        // Open the camera and take the picture
        Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, REQUEST_PICTURE);
    }

    /**
     * Apply the Bitmap to the ImageView.
     *
     * @param bitmap
     */
    private void createImage(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
    }

    /**
     * If the result of the photo was correct we apply the changes.
     *
     * @param requestCode
     * @param resultCode
     * @param mIntent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent mIntent) {
        super.onActivityResult(requestCode, resultCode, mIntent);
        if (resultCode == RESULT_OK && requestCode == REQUEST_PICTURE) {
            Bundle mBundle = mIntent.getExtras();
            Bitmap mBitmap = (Bitmap) mBundle.get("data");
            if (mBitmap != null) {
                // Apply the Bitmap to the ImageView
                createImage(mBitmap);
                getPixelColourImage(mIntent);
            }
            Log.i(TAG, "LOG: Photo!!!");
        }
    }

    private void getPixelColourImage(Intent intent) {
        // Adjust this to your needs
        int redThreshold = 200;
        // redPixels.get(int)[0] = x, redPixels.get(int)[1] = y
        List<int[]> redPixels = new ArrayList<>();
        List<int[]> greenPixels = new ArrayList<>();
        List<int[]> bluePixels = new ArrayList<>();
        Bitmap thumbnail = (Bitmap) intent.getExtras().get("data");
        // The x is row
        for (int x = 0; x < thumbnail.getWidth(); x++) {
            // The y is col
            for (int y = 0; y < thumbnail.getHeight(); y++) {
                if (Color.red(thumbnail.getPixel(x, y)) > redThreshold) {
                    redPixels.add(new int[]{x, y});
                    Log.i(TAG, "LOG: It is red");
                } else if (Color.blue(thumbnail.getPixel(x, y)) > redThreshold) {
                    bluePixels.add(new int[]{x, y});
                    Log.i(TAG, "LOG: It is blue");
                } else if (Color.green(thumbnail.getPixel(x, y)) > redThreshold) {
                    greenPixels.add(new int[]{x, y});
                    Log.i(TAG, "LOG: It is green");
                }
            }
        }
    }
}
