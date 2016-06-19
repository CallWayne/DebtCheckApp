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
public class ASignupTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    /**
     * Ein User wird angelegt
     * @throws Exception
     */
    @Test
    public void signupBtnClick1() throws Exception {
        //Klick auf das Textview Element link_signup
        Espresso.onView(withId(R.id.link_signup)).perform(click());
        //Prüft ob das Element link_signup noch existiert
        //Textview Element sollte nicht mehr gefunden werden
        Espresso.onView(withId(R.id.link_signup)).check(doesNotExist());

        //Klick auf das Edit Textfeld input_name
        Espresso.onView(withId(R.id.input_name)).perform(click());
        //Username wird ins Textfeld geschrieben
        Espresso.onView(withId(R.id.input_name)).perform(typeText("Test1"));
        //Klick auf das Edit Textfeld input_email
        Espresso.onView(withId(R.id.input_email)).perform(click());
        //Email wird ins Textfeld geschrieben
        Espresso.onView(withId(R.id.input_email)).perform(typeText("test1@outlook.de"));
        //Klick auf das Edit Textfeld input_password
        Espresso.onView(withId(R.id.input_password)).perform(click());
        //Passwort wird ins Textfeld geschrieben
        Espresso.onView(withId(R.id.input_password)).perform(typeText("123456"), closeSoftKeyboard());
        //Klick auf den btn_signup Button
        //Öffnet LoginActivity
        Espresso.onView(withId(R.id.btn_signup)).perform(click());
        //Prüft ob der btn_signup Button noch existiert
        //Button sollte nicht mehr gefunden werden
        Espresso.onView(withId(R.id.btn_signup)).check(doesNotExist());
    }
}