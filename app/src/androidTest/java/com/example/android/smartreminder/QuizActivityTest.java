package com.example.android.smartreminder;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.onView;


public class QuizActivityTest {

    @Rule
    public ActivityTestRule<QuizActivity> quizActivityActivityTestRule = new ActivityTestRule<QuizActivity>(QuizActivity.class);

    private QuizActivity mActivity = null;
    private DatabaseHandler databaseHandler = new DatabaseHandler(QuizActivity.this);

    @Before
    public void setUp() throws Exception {
        mActivity = quizActivityActivityTestRule.getActivity();
    }

    @Test
    public void testIfUpholderDBCreated(){
        assertNotNull(mActivity.findViewById(R.layout.activity_quiz));
        onView(withId(R.id.login_page)).perform(click());
        checkIfDBCreated("UPHOLDER");
    }

    private void checkIfDBCreated(String personality){
        int expectedsize=1;
        int actualsize=0;
        switch (personality){
            case "UPHOLDER":
                actualsize=databaseHandler.get
                break;
            case "OBLIGER":
                actualsize=databaseHandler.get
                break;
            case "REBEL":
                actualsize=databaseHandler.get
                break;
            case "QUESTIONER":
                actualsize=databaseHandler.get
                break;

        }

        assertEquals(expectedsize, actualsize);
    }

    @After
    public void tearDown() throws Exception {
        mActivity.finish();
        mActivity = null;
    }

}