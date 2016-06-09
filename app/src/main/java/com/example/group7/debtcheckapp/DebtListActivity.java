package com.example.group7.debtcheckapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.group7.Wsdl2Code.OnlineIntegrationService.debt;
import com.example.group7.Wsdl2Code.OnlineIntegrationService.payDebtResponsee;
import com.example.group7.debtcheckapp.Exceptions.InvalidLoginException;
import com.example.group7.debtcheckapp.Mock.Account;
import com.example.group7.debtcheckapp.Mock.AccountList;
import com.example.group7.debtcheckapp.Mock.Debt;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DebtListActivity extends AppCompatActivity {

    ArrayList<Debt> debtList = new ArrayList<>();
    ListView listView ;
    Debt debt;

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
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                debt = (Debt) listView.getItemAtPosition(position);
                registerForContextMenu(listView);
                openContextMenu(listView);
                return true;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo info){
        super.onCreateContextMenu(menu, v, info);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_debtlist, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()){
            case R.id.btn_payDebt:
                Intent intent = new Intent(getBaseContext(), PayDebtActivity.class);
                intent.putExtra("debtIntent", debt);
                startActivity(intent);
                return true;
        }
        return true;
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
