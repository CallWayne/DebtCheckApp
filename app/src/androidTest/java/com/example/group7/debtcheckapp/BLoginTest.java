package com.example.group7.debtcheckapp;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * @author Niklas Schlüter, André Käthner
 * @serial Account
 * @version 1.0
 */
@RunWith(AndroidJUnit4.class)
public class BLoginTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void loginBtnClick() throws Exception {

            //Klick auf das Edit Textfeld input_email
            Espresso.onView(withId(R.id.input_email)).perform(click());
            //Email wird ins Textfeld geschrieben
            Espresso.onView(withId(R.id.input_email)).perform(typeText("test1@outlook.de"));
            //Klick auf das Edit Textfeld input_password
            Espresso.onView(withId(R.id.input_password)).perform(click());
            //Passwort wird ins Textfeld geschrieben
            Espresso.onView(withId(R.id.input_password)).perform(typeText("123456"), closeSoftKeyboard());
            //Klick auf den btn_login Button
            Espresso.onView(withId(R.id.btn_login)).perform(click());
            //Prüft ob der btn_login Button noch existiert
            //Button sollte nicht mehr gefunden werden
            Espresso.onView(withId(R.id.btn_login)).check(doesNotExist());
    }
}
