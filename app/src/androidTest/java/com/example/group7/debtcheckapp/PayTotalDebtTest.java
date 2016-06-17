package com.example.group7.debtcheckapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

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
        try {
            Log.d("Test","loginBtnClick");
            Espresso.onView(withId(R.id.input_email)).perform(click());
            Espresso.onView(withId(R.id.input_email)).perform(typeText("test2@outlook.de"));
            Espresso.onView(withId(R.id.input_password)).perform(click());
            Espresso.onView(withId(R.id.input_password)).perform(typeText("123456"), closeSoftKeyboard());
            Espresso.onView(withId(R.id.btn_login)).perform(click());
            Espresso.onView(withId(R.id.btn_login)).check(doesNotExist());
        } catch (Exception e) {
            Log.d("androidTest", e.getMessage());
        }

        try {
            Log.d("Test","debtListClick");
            Espresso.onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
            Espresso.onView(withText("Debt List")).perform(click());
            Espresso.onData(anything()).inAdapterView(withId(R.id.listview_DebtList)).atPosition(0).perform(longClick());
            Espresso.onView(withText("Pay Debt")).perform(click());
        } catch (Exception e) {
            Log.d("androidTest", e.getMessage());
        }

        try {
            Log.d("Test","payDebtBtnClick");
            Espresso.onView(withId(R.id.edit_payment)).perform(click());
            Espresso.onView(withId(R.id.edit_payment)).perform(typeText("50.00"));
            Espresso.onView(withId(R.id.btn_payDebt)).perform(click());
        }
        catch (Exception e) {
            Log.d("androidTest", e.getMessage());
        }
    }

}
