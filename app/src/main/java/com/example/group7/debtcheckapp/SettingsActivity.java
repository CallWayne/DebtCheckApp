package com.example.group7.debtcheckapp;

/**
 * Activity für Setting's
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * @author Niklas Schlüter, André Käthner
 * @version 1.0s
 */
public class SettingsActivity extends AppCompatActivity {

    /**
     * Methode für das erstellen der Activity
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
