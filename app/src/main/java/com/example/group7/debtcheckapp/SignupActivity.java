package com.example.group7.debtcheckapp;

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
import com.example.group7.debtcheckapp.Mock.AccountList;


public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @InjectView(R.id.input_name) EditText _nameText;
    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_signup) Button _signupButton;
    @InjectView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup(v);
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup(View btnSignup) {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String username = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        //Account x = new Account(name, email, password);
        //AccountList.setAccList(x);

        SignupTask signupTask = new SignupTask(btnSignup.getContext());
        signupTask.execute(username, email, password);



        /*new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);*/
    }


    /*public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }*/

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

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

    private class SignupTask extends AsyncTask<String, Integer, Account> {

        private Context context;

        public SignupTask(Context context)
        {
            this.context = context;
        }

        @Override
        protected Account doInBackground(String... params) {
            if(params.length != 3) {
                return null;
            }

            String username = params[0];
            String email = params[1];
            String password = params[2];
            DebtCheckAndroidApplication app = (DebtCheckAndroidApplication) getApplication();

            try {
                Account userAccount = app.getOnlineIntegrationServiceInterface().signup(username, email, password);
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
                //Toast anzeigen
                CharSequence text = "Registration fehlgeschlagen!";
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
