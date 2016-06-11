package com.example.group7.debtcheckapp;

/**
 * Activity für das Login von Usern
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.AsyncTask;
import com.example.group7.debtcheckapp.Exceptions.InvalidLoginException;
import com.example.group7.debtcheckapp.Mock.Account;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;
    @InjectView(R.id.link_signup) TextView _signupLink;

    /**
     * Methode für das erstellen der Activity
     * @param savedInstanceState Bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        //Initialisierung von _loginButton
        _loginButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Methode wenn der Button geklickt wird
             * @param v View
             */
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
        //Initialisierung von _signupLink
        _signupLink.setOnClickListener(new View.OnClickListener() {

            /**
             * Methode wenn der Button geklickt wird
             * @param v View
             */
            @Override
            public void onClick(View v) {
                //Initialisierung vom inten
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                //starten der nächsten Activity
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    /**
     * Methode für das Login
     * @param btnLogin View
     */
    public void login(View btnLogin ) {
        Log.d(TAG, "Login");
        //Eingabewerte werden mit validate() geprüft
        if (!validate()) {
            //wenn Werte falsch dann wird die Methode onLoginFailed() aufgerufen
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);
        //ProgressDialog wird erstellt und gestartet
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        //Werte werden gelesen email und password
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        //Initialisierung und Aufruf des AsyncTask loginTask
        LoginTask loginTask = new LoginTask(btnLogin.getContext());
        loginTask.execute(email, password);
    }

    /**
     * Methode für das fehlschlagen der Loginfunktion
     */
    private void onLoginFailed() {
        //Toast initialsieren und anzeigen
        CharSequence text = "Login fehlgeschlagen!";
        Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    /**
     * Methode für die Validierung der Eingabewerte
     * @return boolean
     */
    public boolean validate() {
        boolean valid = true;
        //Werte einlesen email und password
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        //Emailprüfung
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }
        //Passwordprüfung
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }

    /**
     * AsyncTask für das Login
     */
    private class LoginTask extends AsyncTask<String, Integer, Account> {

        private Context context;

        /**
         * Konstruktor
         * @param context Context
         */
        public LoginTask(Context context)
        {
            this.context = context;
        }

        /**
         * Methode das abholen der Daten vom Server
         * @®return Account
         */
        @Override
        protected Account doInBackground(String... params) {
            if(params.length != 2) {
                return null;
            }

            //Initialsierung der Parameter
            String email = params[0];
            String password = params[1];
            DebtCheckAndroidApplication app = (DebtCheckAndroidApplication) getApplication();

            try {
                //Methode im WebService aufrufen
                Account userAccount = app.getOnlineIntegrationServiceInterface().login(email, password);
                return userAccount;
            }
            catch (InvalidLoginException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * Methode für das Login
         * @param result Account
         */
        protected void onPostExecute(Account result)
        {
            if(result != null)
            {
                //erfolgreich eingeloggt
                DebtCheckAndroidApplication app = (DebtCheckAndroidApplication) getApplication();
                app.setAccount(result);
                //Toast anzeigen
                CharSequence text = "Login erfolgreich! Angemeldet als " + result.getUserName();
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                //Nächste Activity anzeigen
                _loginButton.setEnabled(true);
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
            else
            {
                //Bei Fehler die Methode onLoginFailed() aufrufen
                onLoginFailed();
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
        }
    }
}
