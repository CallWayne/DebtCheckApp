package com.example.group7.debtcheckapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.group7.debtcheckapp.Mock.Debt;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ClaimListActivity extends AppCompatActivity {

    ArrayList<Debt> claimList = new ArrayList<>();
    ListView listView ;
    Debt debt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_list);

        ClaimListTask claimListTask = new ClaimListTask();
        try {
            claimList = claimListTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        listView = (ListView) findViewById(R.id.listview_ClaimList);
        listView.setAdapter(new ClaimAdapter(this, claimList));
    }


    private class ClaimListTask extends AsyncTask<Void, Void, ArrayList<Debt>> {

        @Override
        protected ArrayList<Debt> doInBackground(Void... params) {
            DebtCheckAndroidApplication app = (DebtCheckAndroidApplication) getApplication();
            ArrayList<Debt> claimList = app.getOnlineIntegrationServiceInterface().getAllClaims();
            return claimList;
        }
    }
}

