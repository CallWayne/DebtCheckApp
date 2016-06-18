package com.example.group7.debtcheckapp;

/**
 * MainActivity, Startbildschirm
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group7.debtcheckapp.Exceptions.InvalidGetAllClaimsException;
import com.example.group7.debtcheckapp.Exceptions.InvalidGetAllDebtsException;
import com.example.group7.debtcheckapp.Mock.Debt;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    @InjectView(R.id.text_allDebts) TextView _text_allDebts;
    @InjectView(R.id.text_allClaims) TextView _text_allClaims;

    ArrayList<Debt> debtList;
    ArrayList<Debt> claimList;

    /**
     * Methode für das erstellen der Activity
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.inject(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final Button button = (Button) findViewById(R.id.btn_newDebt);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DebtActivity.class);
                startActivity(intent);
            }
        });

        setText();
    }

    public void setText() {
        Log.d(TAG, "setText");

        DebtListTask debtListTask = new DebtListTask();
        try {
            debtList = debtListTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ClaimListTask claimListTask = new ClaimListTask();
        try {
            claimList = claimListTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        double allDebts=0;
        if(debtList!=null){
            for(int i = 0; i<debtList.size();i++){
                Debt x = debtList.get(i);
                allDebts = allDebts + x.getAmount();
            }
            _text_allDebts.setText(String.valueOf(allDebts));
        }
        else{
            _text_allDebts.setText("Keine Schuld vorhanden!");
        }


        double allClaims=0;
        if(claimList!=null){
            for(int i = 0; i<claimList.size();i++){
                Debt x = claimList.get(i);
                allClaims = allClaims + x.getAmount();
            }
            _text_allClaims.setText(String.valueOf(allClaims));
        }
        else{
            _text_allClaims.setText("Keine Forderung vorhanden!");
        }
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.d(TAG, "onNavigationItemSelected");

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            // Handle the Settings
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_debtList) {
            Intent intent = new Intent(this, DebtListActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_claimList) {
            Intent intent = new Intent(this, ClaimListActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_logout) {

                LogoutTask logoutTask = new LogoutTask();
                logoutTask.execute();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class LogoutTask extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... params) {
            Log.d(TAG, "doInBackground LogoutTask");

            try{
                DebtCheckAndroidApplication app = (DebtCheckAndroidApplication) getApplication();
                app.getOnlineIntegrationServiceInterface().logout();
                CharSequence text = "Logout erfolgreich!";
                Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
            }
            catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }

    private class DebtListTask extends AsyncTask<Void, Void, ArrayList<Debt>> {

        @Override
        protected ArrayList<Debt> doInBackground(Void... params) {
            Log.d(TAG, "doInBackground DebtListTask");

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

    private class ClaimListTask extends AsyncTask<Void, Void, ArrayList<Debt>> {

        @Override
        protected ArrayList<Debt> doInBackground(Void... params) {
            Log.d(TAG, "doInBackground ClaimListTask");

            DebtCheckAndroidApplication app = (DebtCheckAndroidApplication) getApplication();
            ArrayList<Debt> claimList = null;
            try {
                claimList = app.getOnlineIntegrationServiceInterface().getAllClaims();
            } catch (InvalidGetAllClaimsException e) {
                e.printStackTrace();
            }
            return claimList;
        }
    }
}
