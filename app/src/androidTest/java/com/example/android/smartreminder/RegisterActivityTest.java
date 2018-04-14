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

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RegisterActivityTest {

    private DatabaseHandler databaseHelper;

    @Rule
    public ActivityTestRule<RegisterActivity> registerActivityActivityTestRule = new ActivityTestRule<RegisterActivity>(RegisterActivity.class);

    private RegisterActivity mActivity = null;

    Instrumentation.ActivityMonitor monitor = InstrumentationRegistry.getInstrumentation().
            addMonitor(LoginActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        mActivity = registerActivityActivityTestRule.getActivity();
        asserNotNull(mActivity);
        databaseHelper = new DatabaseHandler(mActivity);
        asserNotNull(databaseHelper);
    }

    @Test
    public void testFinish(){
        //assertNotNull(mActivity.findViewById(R.id.textview));
        onView(withId(R.id.appCompatButtonRegister)).perform(click());
        Activity loginActivity = InstrumentationRegistry.getInstrumentation().waitForMonitorWithTimeout(monitor, 500);
        assertNotNull(loginActivity);
        loginActivity.finish();
    }

    @Test
    public void testPostDataToSQLite(){
        //assertNotNull(mActivity.findViewById(R.id.textview));
        onView(withId(R.id.appCompatTextViewLoginLink)).perform(click());
        int expectedCount=1;
        int actualCount=databaseHelper.getAllUser().size();
        assertEquals(expectedCount, actualCount);

        if (actualCount!=0){
            for (Contacts user: getAllUser()) {
                databaseHelper.deleteUser(user);
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        mActivity.finish();
        mActivity = null;
        databaseHelper=null;
    }

}