package com.derrick.park.assignment3_contacts.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;
import com.derrick.park.assignment3_contacts.models.ContactList;
import com.derrick.park.assignment3_contacts.network.ContactClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Contact> mContactList;
    public static final String TAG = MainActivity.class.getSimpleName();
    private ContactsAdapter myAdapter;
    private RecyclerView myRecyclerView;
    private static final String DEBUG = "DEBUG";
    private static final String TAG_NAME = "name";
    private static final String TAG_SURNAME = "surname";
    private static final String TAG_PHONE = "phone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a handler for the RetrofitInstance interface
        Call<ContactList> call = ContactClient.getContacts(50);

        // Execute the request asynchronously
        call.enqueue(new Callback<ContactList>() {
            // Handle a successful response
            @Override
            public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                if (response.isSuccessful()) {
                    mContactList = response.body().getContactList();
                    for (Contact contact : mContactList) {
                        Log.d(TAG, "onResponse: " + mContactList.size());
                        Log.d(TAG, "onResponse: " + contact);
                    }
                }
                // Add the contacts to the view sorted
                loadDataList(mContactList);
            }

            @Override
            public void onFailure(Call<ContactList> call, Throwable t) {
                // If the request fails, then display the following toast
                Toast.makeText(MainActivity.this, "Unable to load users", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Display the retrieved data as a list
    private void loadDataList(ArrayList<Contact> usersList) {
        // Get a reference to the RecyclerView
        myRecyclerView = findViewById(R.id.myRecyclerView);
        // Sort the list
        Collections.sort(usersList, new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getName().getFirst().compareTo(o2.getName().getFirst());
            }
        });

        for (int i = 0; i < usersList.size(); i++) {
            if (i == 0 || !usersList.get(i - 1).getName().toString().substring(0, 1).equals(usersList.get(i).getName().toString().substring(0, 1))) {
                usersList.get(i).first = true;
            }
        }
        myAdapter = new ContactsAdapter(usersList);

        // Use a LinearLayoutManager with default vertical orientation
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        myRecyclerView.setLayoutManager(layoutManager);

        // Set the Adapter to the RecyclerView
        myRecyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addContact:
                addContacts();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addContacts() {
        Intent mIntent = new Intent(this, AddContactActivity.class);
        startActivityForResult(mIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // Get the values from AddContactActivity
                String name = data.getStringExtra(TAG_NAME);
                String surname = data.getStringExtra(TAG_SURNAME);
                String phone = data.getStringExtra(TAG_PHONE);

                // New contact to the List
                Contact cont = new Contact(new Contact.Name(name, surname), phone);

                // Add the new contact to the List
                mContactList.add(cont);

                // Add the contacts to the view sorted
                loadDataList(mContactList);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
