package com.example.group7.debtcheckapp;

/**
 * Activity für den SplashScreen
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * Methode für das erstellen der Activity
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialsieren eines Intent
        Intent intent = new Intent(this, LoginActivity.class);
        //nächste Activity anzeigen
        startActivity(intent);
        finish();
    }
}