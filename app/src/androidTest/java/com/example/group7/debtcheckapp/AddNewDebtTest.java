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
public class AddNewDebtTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void addNewDebtBtnClick() throws Exception {

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



            //Klick auf den btn_newDebt Button
            Espresso.onView(withId(R.id.btn_newDebt)).perform(click());
            //Prüft ob der btn_newDebt Button noch existiert
            //Button sollte nicht mehr gefunden werden
            Espresso.onView(withId(R.id.btn_newDebt)).check(doesNotExist());



            //Klick auf das Edit Textfeld edit_debt
            Espresso.onView(withId(R.id.edit_debt)).perform(click());
            //Höhe der Schuld wird ins Textfeld geschrieben
            Espresso.onView(withId(R.id.edit_debt)).perform(typeText("80"));
            //Klick auf das Edit Textfeld edit_debtor
            Espresso.onView(withId(R.id.edit_debtor)).perform(click());
            //Schuldner wird ins Textfeld geschrieben
            //Name des Schuldners muss bekannt sein
            Espresso.onView(withId(R.id.edit_debtor)).perform(typeText("Test2"));
            //Klick auf das Edit Textfeld edit_reason
            Espresso.onView(withId(R.id.edit_reason)).perform(click());
            //Grund der Schuld wird ins Textfeld geschrieben
            Espresso.onView(withId(R.id.edit_reason)).perform(typeText("Testschuld"), closeSoftKeyboard());
            //Klick auf den btn_createNewDebt Button
            Espresso.onView(withId(R.id.btn_createNewDebt)).perform(click());
            //Prüft ob der btn_createNewDebt Button noch existiert
            //Button sollte nicht mehr gefunden werden
            Espresso.onView(withId(R.id.btn_createNewDebt)).check(doesNotExist());
    }
}
