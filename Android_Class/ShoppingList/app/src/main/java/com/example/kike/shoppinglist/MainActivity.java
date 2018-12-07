package com.example.kike.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView sel1, sel2, sel3, sel4, sel5, sel6, sel7, sel8, sel9, sel10;
    private Button goOtherScreen;
    private final static String TAG_NAME = "SHOP_LIST";
    ArrayList<String> productsSelected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle stateInstanceState) {
        super.onCreate(stateInstanceState);
        setContentView(R.layout.activity_main);
        sel1 = findViewById(R.id.sel1);
        sel2 = findViewById(R.id.sel2);
        sel3 = findViewById(R.id.sel3);
        sel4 = findViewById(R.id.sel4);
        sel5 = findViewById(R.id.sel5);
        sel6 = findViewById(R.id.sel6);
        sel7 = findViewById(R.id.sel7);
        sel8 = findViewById(R.id.sel8);
        sel9 = findViewById(R.id.sel9);
        sel10 = findViewById(R.id.sel10);
        goOtherScreen = findViewById(R.id.viewProducts);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String info = data.getStringExtra(TAG_NAME);
                productsSelected.add(info);

                for (int i = 0; i < productsSelected.size(); i++) {
                    // Add the products to the TextView
                    if (i == 0) {
                        sel1.setText(productsSelected.get(i));
                    } else if (i == 1) {
                        sel2.setText(productsSelected.get(i));
                    } else if (i == 2) {
                        sel3.setText(productsSelected.get(i));
                    } else if (i == 3) {
                        sel4.setText(productsSelected.get(i));
                    } else if (i == 4) {
                        sel5.setText(productsSelected.get(i));
                    } else if (i == 5) {
                        sel6.setText(productsSelected.get(i));
                    } else if (i == 6) {
                        sel7.setText(productsSelected.get(i));
                    } else if (i == 7) {
                        sel8.setText(productsSelected.get(i));
                    } else if (i == 8) {
                        sel9.setText(productsSelected.get(i));
                    } else if (i == 9) {
                        sel10.setText(productsSelected.get(i));
                    }
                }
            }
        }
    }

    public void moveScreen(View v) {
        Intent mIntent = new Intent(this, SecondActivity.class);
        startActivityForResult(mIntent, 1);
    }
}
