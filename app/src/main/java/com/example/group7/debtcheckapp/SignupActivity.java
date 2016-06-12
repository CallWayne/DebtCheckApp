package com.example.group7.debtcheckapp;

/**
 * Activity für das Registrieren
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.group7.debtcheckapp.Exceptions.InvalidLoginException;
import com.example.group7.debtcheckapp.Mock.Account;

/**
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    @InjectView(R.id.input_name) EditText _nameText;
    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_signup) Button _signupButton;
    @InjectView(R.id.link_login) TextView _loginLink;

    /**
     * Methode für das erstellen der Activity
     * @param savedInstanceState Bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);
        //Intialisieren des SignUp Buttons
        _signupButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Methode wenn der Button Signup geklickt wurde
             * @param v View
             */
            @Override
            public void onClick(View v) {
                //Methode signup() wird aufgerufen
                signup(v);
            }
        });

        //Intialisieren des LoginLinks
        _loginLink.setOnClickListener(new View.OnClickListener() {
            /**
             * Methode wenn der Link Login geklickt wurde
             * @param v View
             */
            @Override
            public void onClick(View v) {
                //schließt den RegistrationsScreen und öffnet den LoginScreen
                finish();
            }
        });
    }

    /**
     * Methode für das Registrieren
     * @param btnSignup View
     */
    public void signup(View btnSignup) {
        Log.d(TAG, "Signup");
        //prüfen ob die Eingabe Werte richtig sind
        if (!validate()) {
            //wenn falsch, dann Methode onSignupFailed() aufrufen
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);
        //Dialog initialisieren und aufrufen
        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();
        //EingabeWerte aus den jeweiligen Feldern holen
        String username = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        //AsyncTask intialisieren und ausführen
        SignupTask signupTask = new SignupTask(btnSignup.getContext());
        signupTask.execute(username, email, password);
    }

    /**
     * Methode wenn das Registrieren fehlschlägt
     */
    public void onSignupFailed() {
        //Toast initialisieren und anzeigen
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    /**
     * Methode für das prüfen der Eingabewerte
     * @return boolean
     */
    public boolean validate() {
        boolean valid = true;
        //Eingabwerte einlesen
        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        //prüfen ob der Benutzername valide ist
        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }
        //prüfen ob die Email valide ist
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }
        //prüfen ob das Password valide ist
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }

    /**
     * AsyncTask SignupTask
     */
    private class SignupTask extends AsyncTask<String, Integer, Account> {

        private Context context;

        /**
         * Konstruktor
         * @param context Context
         */
        public SignupTask(Context context)
        {
            this.context = context;
        }

        /**
         * Methode für das Abholen der Daten vom Server
         */
        @Override
        protected Account doInBackground(String... params) {
            if(params.length != 3) {
                return null;
            }
            //Parameter intialsieren
            String username = params[0];
            String email = params[1];
            String password = params[2];
            DebtCheckAndroidApplication app = (DebtCheckAndroidApplication) getApplication();
            try {
                //Methode aus dem Interface aufrufen
                Account userAccount = app.getOnlineIntegrationServiceInterface().signup(username, email, password);
                return userAccount;
            }
            catch (InvalidLoginException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * Methode für das Registrieren
         * @param result Account
         */
        protected void onPostExecute(Account result)
        {
            if(result != null)
            {
                //erfolgreich registriert
                DebtCheckAndroidApplication app = (DebtCheckAndroidApplication) getApplication();
                app.setAccount(result);
                //Toast anzeigen
                CharSequence text = "Registration erfolgreich! Angemeldet als " + result.getUserName();
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                //Nächste Activity anzeigen
                _signupButton.setEnabled(true);
                setResult(RESULT_OK, null);
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
            else
            {
                //Bei Fehler einen Toast anzeigen
                CharSequence text = "Registration fehlgeschlagen!";
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
