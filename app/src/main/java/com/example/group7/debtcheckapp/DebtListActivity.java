package com.example.group7.debtcheckapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.group7.debtcheckapp.Exceptions.InvalidLoginException;
import com.example.group7.debtcheckapp.Mock.Account;
import com.example.group7.debtcheckapp.Mock.AccountList;
import com.example.group7.debtcheckapp.Mock.Debt;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DebtListActivity extends AppCompatActivity {

    ArrayList<Debt> debtList = new ArrayList<>();
    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debts_list);

        DebtListTask debtListTask = new DebtListTask();
        try {
            debtList = debtListTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        listView = (ListView) findViewById(R.id.listview_DebtList);
        listView.setAdapter(new DebtAdapter(this, debtList));



    }





    private class DebtListTask extends AsyncTask<Void, Void, ArrayList<Debt>> {

        @Override
        protected ArrayList<Debt> doInBackground(Void... params) {
            DebtCheckAndroidApplication app = (DebtCheckAndroidApplication) getApplication();
            ArrayList<Debt> debtList = app.getOnlineIntegrationServiceInterface().getAllDebts();
            return debtList;
         }
    }
}
