package com.example.group7.debtcheckapp;


import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author Niklas Schlüter, André Käthner
 * @serial Account
 * @version 1.0
 */
public class LoginTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    private EditText email;
    private EditText password;
    private Button loginButton;
    private LoginActivity nLoginActivity;

    public LoginTest() {
        super(LoginActivity.class);
    }

    //Initialiseriung der Variablen
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
        nLoginActivity = getActivity();
        email = (EditText) nLoginActivity.findViewById(R.id.input_email);
        password = (EditText) nLoginActivity.findViewById(R.id.input_password);
        loginButton = (Button) nLoginActivity.findViewById(R.id.btn_login);
    }
    //Testen ob Eingabe != null ist
    public void testPreconditions() {
        assertNotNull(email);
        assertNotNull(password);
    }
    //Testen ob der LoginButton clickable ist
    public void testClick() {
        nLoginActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                assertTrue(loginButton.performClick());

            }
        });
    }
}
