package com.example.kike.shoppinglistmaps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private Button p1, p2, p3, p4, p5, p6, p7, p8, p9, p10;
    private String product;
    private final static String TAG_NAME = "SHOP_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        p1 = findViewById(R.id.prod1);
        p2 = findViewById(R.id.prod2);
        p3 = findViewById(R.id.prod3);
        p4 = findViewById(R.id.prod4);
        p5 = findViewById(R.id.prod5);
        p6 = findViewById(R.id.prod6);
        p7 = findViewById(R.id.prod7);
        p8 = findViewById(R.id.prod8);
        p9 = findViewById(R.id.prod9);
        p10 = findViewById(R.id.prod10);
    }

    public void addProduct(View v) {
        if (v.getId() == R.id.prod1) {
            product = "Cheese";
        } else if (v.getId() == R.id.prod2) {
            product = "Bread";
        } else if (v.getId() == R.id.prod3) {
            product = "Rice";
        } else if (v.getId() == R.id.prod4) {
            product = "Apples";
        } else if (v.getId() == R.id.prod5) {
            product = "Yogurt";
        } else if (v.getId() == R.id.prod6) {
            product = "Water";
        } else if (v.getId() == R.id.prod7) {
            product = "Butter";
        } else if (v.getId() == R.id.prod8) {
            product = "Meat";
        } else if (v.getId() == R.id.prod9) {
            product = "Perfume";
        } else if (v.getId() == R.id.prod10) {
            product = "Onion";
        }
        sendItemSelected();
    }

    private void sendItemSelected() {
        Intent mIntent = new Intent();
        mIntent.putExtra(TAG_NAME, product);
        setResult(RESULT_OK, mIntent);
        finish();
    }
}
