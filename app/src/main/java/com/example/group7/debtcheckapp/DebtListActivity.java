package com.example.group7.debtcheckapp;

/**
 * Activity für das darstellen der Schulden
 */

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

import com.example.group7.debtcheckapp.Exceptions.InvalidGetAllDebtsException;
import com.example.group7.debtcheckapp.Mock.Debt;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class DebtListActivity extends AppCompatActivity {
    private static final String TAG = "DebtListActivity";

    ArrayList<Debt> debtList = new ArrayList<>();
    ListView listView ;
    Debt debt;

    /**
     * Methode für das erstellen der Activity
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debts_list);
        DebtListTask debtListTask = new DebtListTask();
        try {
            //starten des AsyncTask debtListTask
            debtList = debtListTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //erstellen und füllen der ListView
        listView = (ListView) findViewById(R.id.listview_DebtList);
        listView.setAdapter(new DebtAdapter(this, debtList));
        //wenn man einen LognClick macht
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            /**
             * Methode für die LongClickAktion
             * @param parent AdapterView
             * @param view View
             * @param position int
             * @param id long
             * @return boolean
             */
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                debt = (Debt) listView.getItemAtPosition(position);
                registerForContextMenu(listView);
                //öffnet Menü
                openContextMenu(listView);
                return true;
            }
        });
    }

    /**
     * Methode zum erstellen des ContextMenu
     * @param menu ContextMenu
     * @param v View
     * @param info ContextMenu.ContextMenuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo info){
        Log.d(TAG, "onCreateContextMenu");

        super.onCreateContextMenu(menu, v, info);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_debtlist, menu);
    }

    /**
     * Methode für das Auswählen eines Objektes im ContextMenu
     * @param item MenuItem
     * @return boolean
     */
    @Override
    public boolean onContextItemSelected(MenuItem item){
        Log.d(TAG, "onContextItemSelected");

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()){
            case R.id.btn_payDebt:
                //intent erstellen
                Intent intent = new Intent(getBaseContext(), PayDebtActivity.class);
                //dem inten eine Schuld übergeben, damit sie in der nächsten Activity verüfbar ist
                intent.putExtra("debtIntent", debt);
                //starten der nächsten Activity
                startActivity(intent);
                return true;
        }
        return true;
    }

    /**
     * AsyncTask, für das abrufen der Schulden
     */
    private class DebtListTask extends AsyncTask<Void, Void, ArrayList<Debt>> {
        @Override
        protected ArrayList<Debt> doInBackground(Void... params) {
            Log.d(TAG, "doInBackground");
            DebtCheckAndroidApplication app = (DebtCheckAndroidApplication) getApplication();
            ArrayList<Debt> debtList = null;
            try {
                debtList = app.getOnlineIntegrationServiceInterface().getAllDebts();
            } catch (InvalidGetAllDebtsException e) {
                e.printStackTrace();
            }
            return debtList;
         }
    }
}
