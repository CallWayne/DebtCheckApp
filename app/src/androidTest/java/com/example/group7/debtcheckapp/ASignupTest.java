package com.example.group7.debtcheckapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ASignupTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    /**
     * Ein User wird angelegt
     * @throws Exception
     */
    @Test
    public void signupBtnClick1()throws Exception {
        try {
        Log.d("Test","signupBtnClick1");
            Espresso.onView(withId(R.id.link_signup)).perform(click());
            Espresso.onView(withId(R.id.link_signup)).check(doesNotExist());

            Espresso.onView(withId(R.id.input_name)).perform(click());
            Espresso.onView(withId(R.id.input_name)).perform(typeText("Test2"));
            Espresso.onView(withId(R.id.input_email)).perform(click());
            Espresso.onView(withId(R.id.input_email)).perform(typeText("test2@outlook.de"));
            Espresso.onView(withId(R.id.input_password)).perform(click());
            Espresso.onView(withId(R.id.input_password)).perform(typeText("123456"), closeSoftKeyboard());
            Espresso.onView(withId(R.id.btn_signup)).perform(click());
            Espresso.onView(withId(R.id.btn_signup)).check(doesNotExist());
        } catch (Exception e) {
            Log.d("androidTest", e.getMessage());
        }
    }

    /**
     * Signup wird zweimal aufgerufen um zwei User anzulegen
     * @throws Exception
     */

    public void signupBtnClick2()throws Exception {
        try {
            Log.d("Test","signupBtnClick2");
            Espresso.onView(withId(R.id.link_signup)).perform(click());
            Espresso.onView(withId(R.id.link_signup)).check(doesNotExist());

            Espresso.onView(withId(R.id.input_name)).perform(click());
            Espresso.onView(withId(R.id.input_name)).perform(typeText("Test1"));
            Espresso.onView(withId(R.id.input_email)).perform(click());
            Espresso.onView(withId(R.id.input_email)).perform(typeText("test1@outlook.de"));
            Espresso.onView(withId(R.id.input_password)).perform(click());
            Espresso.onView(withId(R.id.input_password)).perform(typeText("123456"), closeSoftKeyboard());
            Espresso.onView(withId(R.id.btn_signup)).perform(click());
            Espresso.onView(withId(R.id.btn_signup)).check(doesNotExist());
        } catch (Exception e) {
            Log.d("androidTest", e.getMessage());
        }
    }
}