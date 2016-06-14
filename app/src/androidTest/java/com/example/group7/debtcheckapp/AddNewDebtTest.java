package com.example.group7.debtcheckapp;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author Niklas Schlüter, André Käthner
 * @serial Account
 * @version 1.0
 */
public class AddNewDebtTest extends ActivityInstrumentationTestCase2<DebtActivity> {

    private EditText debt;
    private EditText debtor;
    private EditText reason;
    private Button createNewDebtButton;
    private DebtActivity nDebtActivity;

    public AddNewDebtTest() {
        super(DebtActivity.class);
    }

    //Initialiseriung der Variablen
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
        nDebtActivity = getActivity();
        debt = (EditText) nDebtActivity.findViewById(R.id.edit_debt);
        debtor = (EditText) nDebtActivity.findViewById(R.id.edit_debtor);
        reason = (EditText) nDebtActivity.findViewById(R.id.edit_reason);
        createNewDebtButton = (Button) nDebtActivity.findViewById(R.id.btn_createNewDebt);
    }

    //Testen ob Eingabe != null ist
    public void testPreconditions() {
        assertNotNull(debt);
        assertNotNull(debtor);
        assertNotNull(reason);
    }

    //Testen ob der LoginButton clickable ist
    public void testClick() {
        nDebtActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                assertTrue(createNewDebtButton.performClick());
            }
        });
    }
}
