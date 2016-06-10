package com.example.group7.debtcheckapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group7.Wsdl2Code.OnlineIntegrationService.payDebtResponsee;
import com.example.group7.debtcheckapp.Exceptions.InvalidLoginException;
import com.example.group7.debtcheckapp.Mock.Account;
import com.example.group7.debtcheckapp.Mock.Debt;

import java.math.BigDecimal;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PayDebtActivity extends AppCompatActivity {

    @InjectView(R.id.edit_payment) EditText _edit_paymentText;
    @InjectView(R.id.btn_payDebt) Button _btn_payDebtButton;
    @InjectView(R.id.textView_debt) TextView _text_debt;

    Debt debt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_debt);
        ButterKnife.inject(this);

        Intent i = getIntent();
        debt = (Debt) i.getSerializableExtra("debtIntent");

        double debtAmount = debt.getAmount();
        _text_debt.setText("Die Schuld beträgt zur Zeit: " + debtAmount);


        _btn_payDebtButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                payDebt(v);
            }
        });
    }

    private void payDebt(View view) {

        double debtAmount = debt.getAmount();
        String amount = _edit_paymentText.getText().toString();
        double amountDbl = Double.parseDouble(amount);

        if(debtAmount >= amountDbl) {
            PayDebtTask payDebtTask = new PayDebtTask(view.getContext());
            payDebtTask.execute(amount);
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        }
        else {
            CharSequence text = "Du kannst nicht mehr Bezahlen als du jemanden schuldest!";
            Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    private class PayDebtTask extends AsyncTask<String, Integer, Void> {

        private Context context;

        public PayDebtTask(Context context)
        {
            this.context = context;
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                String amount = params[0];
                DebtCheckAndroidApplication app = (DebtCheckAndroidApplication) getApplication();

                BigDecimal amountBd = new BigDecimal(amount);

                app.getOnlineIntegrationServiceInterface().payDebt(debt.getCreditor(), amountBd, debt.getId());

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
