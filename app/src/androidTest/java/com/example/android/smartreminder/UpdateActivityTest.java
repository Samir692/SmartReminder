package com.example.android.smartreminder;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
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


public class UpdateActivityTest {

    @Rule
    public ActivityTestRule<UpdateActivity> updateActivityActivityTestRule = new ActivityTestRule<UpdateActivity>(UpdateActivity.class);

    private UpdateActivity uActivity = null;

    Instrumentation.ActivityMonitor monitor = InstrumentationRegistry.getInstrumentation().
                                              addMonitor(LoginActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        uActivity = updateActivityActivityTestRule.getActivity();
    }

    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.android.smartreminder", appContext.getPackageName());
    }

    @Test
    public void testLaunch(){
        assertNotNull(uActivity.findViewById(R.layout.activity_update));
        assertNotNull(uActivity.findViewById(R.id.update_pagedone));
        assertNotNull(uActivity.findViewById(R.id.update_submit));
        assertNotNull(uActivity.findViewById(R.id.coordLayoutUpdate));
    }

    @After
    public void tearDown() throws Exception {
        uActivity.finish();
        uActivity = null;
    }

}