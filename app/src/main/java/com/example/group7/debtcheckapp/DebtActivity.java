package com.example.group7.debtcheckapp;

/**
 * Activity für das Anlegen neuer Schulden
 */

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
import android.widget.Toast;
import com.example.group7.debtcheckapp.Exceptions.InvalidAddNewDebtException;
import com.example.group7.debtcheckapp.Mock.Debt;
import java.math.BigDecimal;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class DebtActivity extends AppCompatActivity {

    private static final String TAG = "DebtActivity";

    @InjectView(R.id.edit_debt) EditText _editDebtText;
    @InjectView(R.id.edit_debtor) EditText _editDebtorText;
    @InjectView(R.id.edit_reason) EditText _editReasonText;
    @InjectView(R.id.btn_createNewDebt) Button _createdNewDebtButton;

    /**
     * Methode für das erstellen der Activity
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt);
        ButterKnife.inject(this);
        //Initialisierung des Button
        _createdNewDebtButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Methode für das Klicken des Button
             * @param v View
             */
            @Override
            public void onClick(View v) {
                addNewDebt(v);
            }
        });
    }

    /**
     * Methode für das erstellen einer Schuld
     * @param btnAddNewDebt View
     */
    public void addNewDebt(View btnAddNewDebt) {
        Log.d(TAG, "addNewDebt");

        _createdNewDebtButton.setEnabled(false);
        final ProgressDialog  progressDialog = new ProgressDialog(DebtActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Erstellt eine neue Schuld...");
        progressDialog.show();

        String debtor = _editDebtorText.getText().toString();
        String amount = _editDebtText.getText().toString();
        String reason = _editReasonText.getText().toString();

        //Initialsierung und Start des AsyncTask
        AddNewDebtTask addNewDebtTask = new AddNewDebtTask(btnAddNewDebt.getContext());
        addNewDebtTask.execute(debtor, amount, reason);
    }

    /**
     * AsyncTask für das erstellen einer Schuld
     */
    private class AddNewDebtTask extends AsyncTask<String, Integer, Debt> {

        private Context context;

        /**
         * Konstruktor
         * @param context Context
         */
        public AddNewDebtTask(Context context)
        {
            this.context = context;
        }

        /**
         * Methode das abholen der Daten vom Server
         * @return Debt
         */
        @Override
        protected Debt doInBackground(String... params) {
            Log.d(TAG, "doInBackground");

            if(params.length != 3) {
                return null;
            }
            //Übergebene Parameter holen
            String debtor = params[0];
            String amount = params[1];
            String reason = params[2];
            DebtCheckAndroidApplication app = (DebtCheckAndroidApplication) getApplication();
            //String in BigDecimal ändern
            BigDecimal amountBd = new BigDecimal(amount);

            try {
                //Methode im WebService aufrufen
                Debt userDebt = app.getOnlineIntegrationServiceInterface().addNewDebt(debtor, amountBd, reason);
                return userDebt;
            }
            catch (InvalidAddNewDebtException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * Methode für das setzen der Schuld
         * @param result Debt
         */
        protected void onPostExecute(Debt result) {
            Log.d(TAG, "onPostExecute");

            if(result != null)
            {
                //setDebt Methode ausführen
                DebtCheckAndroidApplication app = (DebtCheckAndroidApplication) getApplication();
                app.setDebt(result);
                //Toast erstellen und anzeigen
                CharSequence text = "Erstellen einer neuen Schuld erfolgreich!";
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                _createdNewDebtButton.setEnabled(true);
                //nächste Activity anzeigen
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
            else
            {
                //Toast erstellen und anzeigen
                CharSequence text = "Erstellen einer neuen Schuld fehlgeschlagen!";
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
