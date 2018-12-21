package com.derrick.park.assignment3_contacts.activities;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;

import java.util.ArrayList;

import static com.derrick.park.assignment3_contacts.activities.MainActivity.TAG;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.CustomViewHolder> {

    private ArrayList<Contact> dataList;
    private final static String LOG = "TAG";

    public ContactsAdapter(ArrayList<Contact> dataList) {
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        // Get a reference to the Views in our layout
        public final View myView;
        private TextView textUser;
        private TextView phoneUser;
        private TextView letterAlphabet;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
            textUser = myView.findViewById(R.id.user);
            phoneUser = myView.findViewById(R.id.phone);
            letterAlphabet = myView.findViewById(R.id.letterAlphabet);
        }

        public void bind(Contact contact) {
            textUser.setText(contact.getName().toString());
            phoneUser.setText(contact.getCell());
            letterAlphabet.setText(contact.getName().toString().toUpperCase().substring(0, 1));
            if (contact.first) {
                letterAlphabet.setVisibility(View.VISIBLE);
            } else {
                letterAlphabet.setVisibility(View.GONE);
            }
        }
    }

    // Construct a RecyclerView.ViewHolder
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout, parent, false);
        return new CustomViewHolder(view);
    }

    // Set the data
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: position - " + position);
        holder.bind(dataList.get(position));
    }

    // Calculate the item count for the RecyclerView
    @Override
    public int getItemCount() {
        return dataList.size();
    }
}