package com.example.android.smartreminder;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.example.android.smartreminder.database_sql.DatabaseHandler;

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
    private DatabaseHandler databaseHandler;

    @Before
    public void setUp() throws Exception {
        databaseHandler = new DatabaseHandler(mActivity);
        mActivity = quizActivityActivityTestRule.getActivity();
        assertNotNull(databaseHandler);
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
                actualsize=databaseHandler.getAllUser().size();
                break;
            case "OBLIGER":
                actualsize=databaseHandler.getAllUser().size();
                break;
            case "REBEL":
                actualsize=databaseHandler.getAllUser().size();
                break;
            case "QUESTIONER":
                actualsize=databaseHandler.getAllUser().size();
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