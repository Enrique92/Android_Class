package com.example.kike.cameraproyect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_TAKE_IMAGE = 1;
    private static String imagePath = "";
    private ImageView ivMyPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivMyPicture = (ImageView) findViewById(R.id.iv_my_picture);
        ivMyPicture.post(new Runnable() {
            @Override
            public void run() {
                loadImage();
            }
        });
    }

    private void loadImage() {
        if (imagePath.isEmpty()) return;

        Bitmap myPictureBitmap = BitmapFactory.decodeFile(imagePath);
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
            myPictureBitmap = Bitmap.createScaledBitmap(myPictureBitmap, ivMyPicture.getWidth(), ivMyPicture.getHeight(), true);
        }
        ivMyPicture.setImageBitmap(myPictureBitmap);
    }

    public void takePicture(View v) {
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = new File(storageDir.getAbsolutePath() + "/my_picture.jpg");
        imagePath = image.getAbsolutePath();
        Log.d("takePicture", "picture will be saved at: " + imagePath);

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(image));
        startActivityForResult(takePictureIntent, REQUEST_TAKE_IMAGE);
    }

    public void deletePicture(View v) {
        ivMyPicture.setImageBitmap(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_IMAGE && resultCode == Activity.RESULT_OK) {
            loadImage();
            //            Bundle extras = data.getExtras();
            //            Bitmap myPictureBitmap = (Bitmap) extras.get("data");
        }
    }
}
