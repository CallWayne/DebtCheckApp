package com.example.group7.debtcheckapp;


import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.*;

/**
 * @author Niklas Schlüter, André Käthner
 * @serial Account
 * @version 1.0
 */
@RunWith(AndroidJUnit4.class)
public class LoginTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    //@Rule
    //public ActivityTestRule<LoginActivity> rule = new ActivityTestRule<>(LoginActivity.class);

    public LoginTest() {
        super(LoginActivity.class);
    }

    private Activity activity;
    private Instrumentation instrumentation;

    protected void setUp() throws Exception {
        super.setUp();

        this.activity = super.getActivity();
        this.instrumentation = super.getInstrumentation();
    }

    @Test
    public void loginBtnClick() throws Exception {
        try {
        Log.d("Test","loginBtnClick");
        Espresso.onView(withId(R.id.input_email)).perform(click());
        Espresso.onView(withId(R.id.input_email)).perform(typeText("test@outlook.de"));
        Espresso.onView(withId(R.id.input_password)).perform(click());
        Espresso.onView(withId(R.id.input_password)).perform(typeText("123456"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btn_login)).perform(click());
    } catch (Exception e) {
        Log.d("androidTest", e.getMessage());
    }
    }
}
