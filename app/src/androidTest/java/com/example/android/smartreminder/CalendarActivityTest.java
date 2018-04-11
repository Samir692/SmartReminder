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


public class CalendarActivityTest {

    @Rule
    public ActivityTestRule<CalendarActivity> calendarActivityActivityTestRule = new ActivityTestRule<CalendarActivity>(CalendarActivity.class);

    private CalendarActivity cActivity = null;

    @Before
    public void setUp() throws Exception {
        cActivity = calendarActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchCalendar(){
        assertNotNull(cActivity.findViewById(R.id.textview));
    }

    @After
    public void tearDown() throws Exception {
        cActivity = null;
    }

}