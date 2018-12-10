package com.example.kike.implicit_intents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mwebsiteEditText, mlocationEditText, mshareEditText;
    private Button mopenWebsiteButton, mopenLocationButton, mshareTextButton, takePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mwebsiteEditText = findViewById(R.id.website_edittext);
        mlocationEditText = findViewById(R.id.location_edittext);
        mshareEditText = findViewById(R.id.share_edittext);
        mopenWebsiteButton = findViewById(R.id.open_website_button);
        mopenLocationButton = findViewById(R.id.open_location_button);
        mshareTextButton = findViewById(R.id.share_text_button);
        takePicture = findViewById(R.id.take_a_picture);

    }

    public void openWebsite(View view) {
        String url = mwebsiteEditText.getText().toString();
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void openLocation(View view) {
        String loc = mlocationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void shareText(View view) {
        String txt = mshareEditText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder.from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.share_edit_text)
                .setText(txt)
                .startChooser();
    }

    public void takePicture(View view) {
        Intent mIntent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        startActivity(mIntent);
    }
}
