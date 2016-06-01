package com.example.group7.debtcheckapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.group7.debtcheckapp.Mock.Account;
import com.example.group7.debtcheckapp.Mock.AccountList;

import java.util.ArrayList;

public class FriendsListActivity extends AppCompatActivity {

    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

        UsersAdapter adapter = new UsersAdapter(this, AccountList.getAccList());

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.listview_AccList);

        // Assign adapter to ListView
        listView.setAdapter(adapter);
    }
}
