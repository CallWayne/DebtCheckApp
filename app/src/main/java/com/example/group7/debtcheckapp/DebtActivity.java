package com.example.group7.debtcheckapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import com.example.group7.debtcheckapp.Mock.AccountList;

public class DebtActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_friends);
        // Create an ArrayAdapter using the string array and a default spinner layout
        UsersAdapter adapter = new UsersAdapter(this, AccountList.getAccList());
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}
