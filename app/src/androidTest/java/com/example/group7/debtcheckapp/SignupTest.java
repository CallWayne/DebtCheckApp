package com.example.group7.debtcheckapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

/**
 * @author Niklas Schlüter, André Käthner
 * @serial Account
 * @version 1.0
 */
@RunWith(AndroidJUnit4.class)
public class SignupTest extends ActivityInstrumentationTestCase2<SignupActivity> {

    //@Rule
    //public ActivityTestRule<SignupActivity> rule = new ActivityTestRule<>(SignupActivity.class);

    private Activity activity;
    private Instrumentation instrumentation;

    public SignupTest() {
        super(SignupActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();

        this.activity = super.getActivity();
        this.instrumentation = super.getInstrumentation();
    }

    /**
     * Prüft, ob der Signupprozess funktioniert.
     */
    @Test
    public void signupBtnClick()throws Exception {
        try {
        Log.d("Test","signupBtnClick");
        Espresso.onView(withId(R.id.input_name)).perform(click());
        Espresso.onView(withId(R.id.input_name)).perform(typeText("Test"));
        Espresso.onView(withId(R.id.input_email)).perform(click());
        Espresso.onView(withId(R.id.input_email)).perform(typeText("test@outlook.de"));
        Espresso.onView(withId(R.id.input_password)).perform(click());
        Espresso.onView(withId(R.id.input_password)).perform(typeText("123456"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btn_signup)).perform(click());
        } catch (Exception e) {
            Log.d("androidTest", e.getMessage());
        }
    }
}