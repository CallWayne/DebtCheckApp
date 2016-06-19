package com.example.group7.debtcheckapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

/**
 * @author Niklas Schlüter, André Käthner
 * @serial Account
 * @version 1.0
 */
@RunWith(AndroidJUnit4.class)
public class PayTotalDebtTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void payDebtBtnClick() throws Exception {

            //Klick auf das Edit Textfeld input_email
            Espresso.onView(withId(R.id.input_email)).perform(click());
            //Email wird ins Textfeld geschrieben
            Espresso.onView(withId(R.id.input_email)).perform(typeText("test2@outlook.de"));
            //Klick auf das Edit Textfeld input_password
            Espresso.onView(withId(R.id.input_password)).perform(click());
            //Passwort wird ins Textfeld geschrieben
            Espresso.onView(withId(R.id.input_password)).perform(typeText("123456"), closeSoftKeyboard());
            //Klick auf den btn_login Button
            Espresso.onView(withId(R.id.btn_login)).perform(click());
            //Prüft ob der btn_login Button noch existiert
            //Button sollte nicht mehr gefunden werden
            Espresso.onView(withId(R.id.btn_login)).check(doesNotExist());



            //Slide Bar wird aufgerufen
            Espresso.onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
            //Klick auf Debt List in Navigations Menü der Slide Bar
            //Öffnet die DebtListActivity
            Espresso.onView(withText("Debt List")).perform(click());
            //Klickt auf das erste Element (Debt) in der listview auf der DebtListActivity per longClick
            Espresso.onData(anything()).inAdapterView(withId(R.id.listview_DebtList)).atPosition(0).perform(longClick());
            //Klickt auf das View Element mit dem Text "PayDebt"
            //Öffnet die PayDebtActivity
            Espresso.onView(withText("Pay Debt")).perform(click());



            //Klick auf das Edit Textfeld edit_payment
            Espresso.onView(withId(R.id.edit_payment)).perform(click());
            //Wert der zurückgezahlt werden soll wird ins Textfeld geschrieben
            Espresso.onView(withId(R.id.edit_payment)).perform(typeText("50.00"));
            //Klick auf den btn_payDebt Button
            //Wert des edit_payment wird von der Schuld subtrahiert
            //Öffnet die MainActivity
            Espresso.onView(withId(R.id.btn_payDebt)).perform(click());
    }

}
