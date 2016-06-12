package com.example.group7.debtcheckapp;

/**
 * Activity für das anzeigen vom Profil
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class ProfileActivity extends AppCompatActivity {

    /**
     * Methode für das erstellen der Activity
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
}
