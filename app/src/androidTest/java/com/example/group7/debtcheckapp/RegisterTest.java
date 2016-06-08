package com.example.group7.debtcheckapp;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by niklasschluter on 08.06.16.
 */
public class RegisterTest extends ActivityInstrumentationTestCase2<SignupActivity> {
    private EditText benutzername;
    private EditText email;
    private EditText password;
    private Button loginButton;
    private SignupActivity nSignupActivity;

    public RegisterTest() {
        super(SignupActivity.class);
    }

    //Initialiseriung der Variablen
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
        nSignupActivity = getActivity();
        benutzername = (EditText) nSignupActivity.findViewById(R.id.input_name);
        email = (EditText) nSignupActivity.findViewById(R.id.input_email);
        password = (EditText) nSignupActivity.findViewById(R.id.input_password);
        loginButton = (Button) nSignupActivity.findViewById(R.id.btn_signup);
    }
    //Testen ob Eingabe != null ist
    public void testPreconditions() {
        assertNotNull(email);
        assertNotNull(password);
    }
    //Testen ob der LoginButton clickable ist
    public void testClick() {
        nSignupActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                assertTrue(loginButton.performClick());

            }
        });
    }
}
