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


public class BooklistActivityTest {

    @Rule
    public ActivityTestRule<BooksListActivity> booksActivityTestRule = new ActivityTestRule<BooksListActivity>(BooksListActivity.class);

    private BooksListActivity mActivity = null;

    Instrumentation.ActivityMonitor monitor = InstrumentationRegistry.getInstrumentation().
                                              addMonitor(LoginActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        mActivity = booksActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchBooks(){
        assertNotNull(mActivity.findViewById(R.id.textview));

        Books samplebook=new Books();
        samplebook.set_id(-1);
        samplebook.set_book_name("Sample");
        samplebook.set_book_total_pages(100);
        samplebook.set_book_done_pages(50);
        samplebook.set_book_deadline("01.01.2025");
        samplebook.set_book_created("01.01.2018");

        onView(withId(R.id.login_page)).perform(click());
        Activity loginActivity = InstrumentationRegistry.getInstrumentation().waitForMonitorWithTimeout(monitor, 500);
        assertNotNull(loginActivity);
        loginActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}