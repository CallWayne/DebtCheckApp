package com.example.group7.debtcheckapp;

/**
 * Activity für das darstellen der Forderungen
 */

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.group7.debtcheckapp.Mock.Debt;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class ClaimListActivity extends AppCompatActivity {

    ArrayList<Debt> claimList = new ArrayList<>();
    ListView listView ;

    /**
     * Methode für das erstellen der Activity
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_list);
        ClaimListTask claimListTask = new ClaimListTask();
        try {
            //ausführen des AsyncTask
            claimList = claimListTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //erstellen und füllen der ListView
        listView = (ListView) findViewById(R.id.listview_ClaimList);
        listView.setAdapter(new ClaimAdapter(this, claimList));
    }

    /**
     * AsyncTask für das abrufen der Forderungen
     */
    private class ClaimListTask extends AsyncTask<Void, Void, ArrayList<Debt>> {
        @Override
        protected ArrayList<Debt> doInBackground(Void... params) {
            DebtCheckAndroidApplication app = (DebtCheckAndroidApplication) getApplication();
            ArrayList<Debt> claimList = app.getOnlineIntegrationServiceInterface().getAllClaims();
            return claimList;
        }
    }
}

