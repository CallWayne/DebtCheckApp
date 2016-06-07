package com.example.group7.debtcheckapp;

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

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;
    @InjectView(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login(v);
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login(View btnLogin ) {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        LoginTask loginTask = new LoginTask(btnLogin.getContext());
        loginTask.execute(email, password);
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    private void onLoginFailed() {
        CharSequence text = "Login fehlgeschlagen!";
        Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    /*
    Validierung der Eingabewerte im Login Bereich
     */
    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    private class LoginTask extends AsyncTask<String, Integer, Account> {

        private Context context;

        public LoginTask(Context context)
        {
            this.context = context;
        }

        @Override
        protected Account doInBackground(String... params) {
            if(params.length != 2) {
                return null;
            }

            String email = params[0];
            String password = params[1];
            DebtCheckAndroidApplication app = (DebtCheckAndroidApplication) getApplication();

            try {
                Account userAccount = app.getOnlineIntegrationServiceInterface().login(email, password);
                return userAccount;
            }
            catch (InvalidLoginException e) {
                e.printStackTrace();
            }
            return null;
        }

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
