package com.example.group7.debtcheckapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.group7.debtcheckapp.Exceptions.InvalidAddNewDebtException;
import com.example.group7.debtcheckapp.Mock.AccountList;
import com.example.group7.debtcheckapp.Mock.Debt;

import java.math.BigDecimal;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DebtActivity extends AppCompatActivity {
    private static final String TAG = "DebtActivity";
    private OnlineIntegrationServiceImplements oisi;

    @InjectView(R.id.edit_debt) EditText _editDebtText;
    @InjectView(R.id.edit_debtor) EditText _editDebtorText; //war vorher ein Spinner
    @InjectView(R.id.edit_reason) EditText _editReasonText;
    @InjectView(R.id.edit_date) EditText _editDateText;
    @InjectView(R.id.button_createNewDebt) Button _createdNewDebtButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt);
        ButterKnife.inject(this);

        /*Spinner spinner = (Spinner) findViewById(R.id.edit_debtor);
        // Create an ArrayAdapter using the string array and a default spinner layout
        UsersAdapter adapter = new UsersAdapter(this, AccountList.getAccList());
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);*/

        _createdNewDebtButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addNewDebt(v);
            }
        });
        /*final Button button = (Button) findViewById(R.id.button_createNewDebt);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });*/
    }

    public void addNewDebt(View btnAddNewDebt) {
        Log.d(TAG, "Add a new Debt");

        _createdNewDebtButton.setEnabled(false);

        final ProgressDialog  progressDialog = new ProgressDialog(DebtActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Erstellt eine neue Schuld...");
        progressDialog.show();

        String debtor = _editDebtorText.getTransitionName().toString();
        String amount = _editDebtText.getText().toString();
        String reason = _editReasonText.getText().toString();
        String endDate = _editDateText.getText().toString();

        AddNewDebtTask addNewDebtTask = new AddNewDebtTask(btnAddNewDebt.getContext());
        addNewDebtTask.execute(debtor, amount, reason);
    }

    private class AddNewDebtTask extends AsyncTask<String, Integer, Debt> {

        private Context context;

        public AddNewDebtTask(Context context)
        {
            this.context = context;
        }

        @Override
        protected Debt doInBackground(String... params) {
            if(params.length != 3) {
                return null;
            }

            String debtor = params[0];
            String amount = params[1];
            String reason = params[2];
            DebtCheckAndroidApplication app = (DebtCheckAndroidApplication) getApplication();

            BigDecimal amountBd = new BigDecimal(amount);

            try {
                Debt userDebt = app.getOnlineIntegrationServiceInterface().addNewDebt(debtor, amountBd, reason);
                return userDebt;
            }
            catch (InvalidAddNewDebtException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Debt result)
        {
            if(result != null)
            {
                //erfolgreich eingeloggt
                DebtCheckAndroidApplication app = (DebtCheckAndroidApplication) getApplication();
                app.setDebt(result);

                //Toast anzeigen
                CharSequence text = "Erstellen einer neuen Schuld erfolgreich! Die Höhe der Schuld beträgt " + result.getAmount();
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

                //Nächste Activity anzeigen
                _createdNewDebtButton.setEnabled(true);
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
            else
            {
                //Toast anzeigen
                CharSequence text = "Erstellen einer neuen Schuld fehlgeschlagen!";
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
