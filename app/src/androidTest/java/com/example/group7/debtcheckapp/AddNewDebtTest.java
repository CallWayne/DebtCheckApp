package com.example.group7.debtcheckapp;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * @author Niklas Schlüter, André Käthner
 * @serial Account
 * @version 1.0
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddNewDebtTest {

    @Rule
    public final ActivityTestRule<DebtActivity> testRule = new ActivityTestRule<>(DebtActivity.class);

    @Test
    public void testRegister() {
        Activity activity = testRule.getActivity();

    }

    @Test
    public void testClickButton() {
        
    }
}
