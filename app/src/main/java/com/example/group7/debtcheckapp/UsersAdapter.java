package com.example.group7.debtcheckapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.group7.debtcheckapp.Mock.Account;

import java.util.ArrayList;

/**
 * Created by niklasschluter on 17.05.16.
 */
public class UsersAdapter extends ArrayAdapter<Account> {
    public UsersAdapter(Context context, ArrayList<Account> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Account user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_account, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.AccName);
        // Populate the data into the template view using the data object
        tvName.setText(user.getUserName());
        // Return the completed view to render on screen
        return convertView;
    }
}

