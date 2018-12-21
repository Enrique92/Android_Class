package com.derrick.park.assignment3_contacts.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;

import java.util.ArrayList;

public class AddContactActivity extends AppCompatActivity {

    private ArrayList<Contact> mContactList;
    private EditText edName;
    private EditText edPhoneNumber;
    private Button btnSubmit;
    private static final String TAG = "DEBUG";
    private static final String TAG_NAME = "name";
    private static final String TAG_SURNAME = "surname";
    private static final String TAG_PHONE = "phone";
    private boolean addContact;
    private String name = "", surname = "", nameAndSurname = "", phoneNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edName = findViewById(R.id.etName);
        edPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnSubmit = findViewById(R.id.btnSubmit);
    }


    public void checkFieldsNewContact() {
        int var = 0;
        addContact = true;
        nameAndSurname = edName.getText().toString().toLowerCase();
        phoneNumber = edPhoneNumber.getText().toString();

        if (nameAndSurname.equals("") || nameAndSurname.isEmpty()) {
            addContact = false;
            showToast("You have to introduce Name and Surname");
        } else {
            // Get the name and the surname from the EditText
            int space = nameAndSurname.indexOf(" ", 0);

            if (space == -1) {
                addContact = false;
                showToast("You have to introduce Name and Surname");
            } else {
                name = nameAndSurname.substring(0, space);
                surname = nameAndSurname.substring(space + 1, nameAndSurname.length());

                if (name.isEmpty() || surname.isEmpty()) {
                    addContact = false;
                    var = 1;
                    showToast("You have to introduce Name and Surname");
                }

                if (var == 1 || phoneNumber.length() < 10 || phoneNumber.isEmpty() || phoneNumber.equals("")) {
                    addContact = false;
                    showToast("You have to introduce 10 digits");
                }
            }
        }
    }

    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void addNewContact(View mView) {
        checkFieldsNewContact();

        // If any field is wrong we don't add the contact
        if (addContact == true) {
            showToast("User added!");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (addContact == true) {
                    Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
                    mIntent.putExtra(TAG_NAME, name);
                    mIntent.putExtra(TAG_SURNAME, surname);
                    mIntent.putExtra(TAG_PHONE, phoneNumber);
                    setResult(RESULT_OK, mIntent);
                    finish();
                }
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
