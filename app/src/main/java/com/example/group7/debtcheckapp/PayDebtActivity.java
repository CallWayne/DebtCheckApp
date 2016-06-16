package com.example.group7.debtcheckapp;

/**
 * Activity für das zurückzahlen von Schulden
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group7.debtcheckapp.Exceptions.InvalidPayDebtException;
import com.example.group7.debtcheckapp.Mock.Debt;
import java.math.BigDecimal;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class PayDebtActivity extends AppCompatActivity {
    private static final String TAG = "PayDebtActivity";
    @InjectView(R.id.edit_payment) EditText _edit_paymentText;
    @InjectView(R.id.btn_payDebt) Button _btn_payDebtButton;
    @InjectView(R.id.textView_debt) TextView _text_debt;
    Debt debt;

    /**
     * Methode für das erstellen der Activity
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_debt);
        ButterKnife.inject(this);
        //erstellt einen neuen Intent und holt sich die übergebenen Debt von DebtListActivity
        Intent i = getIntent();
        debt = (Debt) i.getSerializableExtra("debtIntent");
        //initialisiert die Höhe der Schuld
        double debtAmount = debt.getAmount();
        //gibt die Höhe der Schuld am Bildschirm aus
        _text_debt.setText("Die Schuld beträgt zur Zeit: " + debtAmount);
        //initialisert einen Button
        _btn_payDebtButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Methode, wenn auf den Button geklickt wird
             * @param v View
             */
            @Override
            public void onClick(View v) {
                //ruft die Methode payDebt auf
                payDebt(v);
            }
        });
    }

    /**
     * Methode das zurückzahlen der Schuld
     * @param view View
     */
    private void payDebt(View view) {
        Log.d(TAG, "payDebt");

        double debtAmount = debt.getAmount();
        String amount = _edit_paymentText.getText().toString();
        double amountDbl = Double.parseDouble(amount);
        //prüft ob die Rückzahlung größer als die eigentliche Schuld ist
        if(debtAmount >= amountDbl) {
            //Initialisierung des AsyncTask und Ausführung
            PayDebtTask payDebtTask = new PayDebtTask(view.getContext());
            payDebtTask.execute(amount);
            //starten der nächsten Activity
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        }
        else {
            //zeigt Fehlermeldung an wenn die Rückzahlung größer als die eigentliche Schuld ist
            CharSequence text = "Du kannst nicht mehr Bezahlen als du jemanden schuldest!";
            Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * AsyncTask PayDebtTask
     */
    private class PayDebtTask extends AsyncTask<String, Integer, Void> {
        private Context context;

        /**
         * Konstruktor
         * @param context Context
         */
        public PayDebtTask(Context context)
        {
            this.context = context;
        }

        /**
         * Methode für das abholen der Daten vom Server
         */
        @Override
        protected Void doInBackground(String... params) {
            Log.d(TAG, "doInBackground");

            try {
                //Initialisierung der Parameter
                String amount = params[0];
                DebtCheckAndroidApplication app = (DebtCheckAndroidApplication) getApplication();
                BigDecimal amountBd = new BigDecimal(amount);
                //Methode payDebt auf dem WebService aufrufen
                try{
                    app.getOnlineIntegrationServiceInterface().payDebt(debt.getCreditor(), amountBd, debt.getId());
                }catch(InvalidPayDebtException e){
                    e.printStackTrace();
                }

                //Toast initialisieren und anzeigen
                CharSequence text = "Erfolgreich zurückgezahlt";
                Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
