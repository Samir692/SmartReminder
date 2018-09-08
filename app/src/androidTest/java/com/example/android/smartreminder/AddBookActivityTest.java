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


public class AddBookActivityTest {

    @Rule
    public ActivityTestRule<AddBook> addBookActivityActivityTestRule = new ActivityTestRule<AddBook>(AddBook.class);

    private AddBook mActivity = null;
    private DatabaseHandler dbHandler;

    @Before
    public void init() throws Exception {
        mActivity = addBookActivityActivityTestRule.getActivity();
        dbHandler = new DatabaseHandler(mActivity);

    }

    @Test
    public void testToolBar(){
        assertNotNull(mActivity.findViewById(R.id.toolbar));
    }
    @Test
    public void testAddingBook(){
        onView(withId(R.id.addBook)).perform(click());
        int actualCountBooks=dbHandler.getAllBooks().size();
        int expectedCountBooks=1;
        assertEquals(actualCountBooks, expectedCountBooks);

        int actualCountQA=dbHandler.getAllQuestionAnswer().size();
        int expectedCountQA=1;
        assertEquals(actualCountQA, expectedCountQA);
    }

    @After
    public void tearDown() throws Exception {
        mActivity.finish();
        mActivity = null;
        dbHandler=null;
    }

}